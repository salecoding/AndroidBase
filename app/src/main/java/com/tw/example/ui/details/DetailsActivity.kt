package com.tw.example.ui.details

import com.tw.baselibs.base.BaseActivity
import com.tw.example.R
import com.tw.example.data.entity.TestEntity
import com.tw.example.databinding.ActivityDetailsBinding
import com.tw.example.ui.main.MainActivity.Companion.DATA_DETAILS
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity<DetailsPresenter, ActivityDetailsBinding>() {
    override fun getLayoutId() = R.layout.activity_details

    override fun createPresenter() = DetailsPresenter()

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        var testEntity = intent.getParcelableExtra<TestEntity>(DATA_DETAILS)
        mViewDataBinding.testEntity = testEntity
    }

    override fun initListener() {
    }

    override fun isViewDataBinding() = true
}