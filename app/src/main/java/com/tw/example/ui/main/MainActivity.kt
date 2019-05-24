package com.tw.example.ui.main

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.amitshekhar.DebugDB
import com.blankj.utilcode.util.ToastUtils
import com.tw.baselibs.base.BaseActivity
import com.tw.baselibs.widget.RecyclerViewDivider
import com.tw.example.R
import com.tw.example.data.entity.QueryWhere
import com.tw.example.data.entity.TestEntity
import com.tw.example.db.Album
import com.tw.example.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class MainActivity : BaseActivity<MainPresenter, ViewDataBinding>(), MainContract.View {
    companion object {
        const val DATA_DETAILS = "dataDetails"
        const val IS_INIT_DATA = "isInitData"
    }

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
        var allAlbums = LitePal.findAll<Album>()
        ToastUtils.showShort("${allAlbums?.size}")

        ToastUtils.showShort(DebugDB.getAddressLog())
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
        dataAdapter.setOnItemClickListener { _, _, position ->
            val bundle = Bundle()
            bundle.putParcelable(DATA_DETAILS, dataAdapter.getItem(position))
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
