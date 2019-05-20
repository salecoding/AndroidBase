package com.tw.example.ui.main

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.tw.baselibs.base.BaseActivity
import com.tw.baselibs.widget.RecyclerViewDivider
import com.tw.example.R
import com.tw.example.data.entity.QueryWhere
import com.tw.example.data.entity.TestEntity
import com.tw.example.db.Album
import com.tw.example.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter, ViewDataBinding>(), MainContract.View {

    private val dataAdapter by lazy { DataAdapter() }
    private val queryWhere by lazy { QueryWhere() }

    override fun showData(testEntityList: List<TestEntity>) {
        setData(dataAdapter, testEntityList, queryWhere.isRefresh)
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun createPresenter() = MainPresenter()

    override fun initView() {
        rvDataList?.layoutManager = LinearLayoutManager(this)
        rvDataList?.addItemDecoration(RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL))
        rvDataList?.adapter = dataAdapter
    }

    override fun initData() {
        mPresenter?.requestTestData(queryWhere)

        val album = Album()
        album.name = "龙卷风"
        album.price = 10.9F
        album.saveAsync().listen { success -> if (success) ToastUtils.showShort("成功") else ToastUtils.showShort("失败") }
    }

    override fun initListener() {
        swipeRefreshLayout.setOnRefreshListener {
            queryWhere.isRefresh = true
            mPresenter?.requestTestData(queryWhere)
        }
        dataAdapter.setOnLoadMoreListener({
            queryWhere.isRefresh = false
            mPresenter?.requestTestData(queryWhere)
        }, rvDataList)
        dataAdapter.setOnItemClickListener { adapter, view, position ->
            var bundle = Bundle()
            bundle.putParcelable("dataDetails", dataAdapter.getItem(position))
            startActivity(DetailsActivity::class.java, bundle)
        }
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }
}
