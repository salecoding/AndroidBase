package com.tw.example.ui.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tw.example.R
import com.tw.example.data.entity.TestEntity


class DataAdapter : BaseQuickAdapter<TestEntity, BaseViewHolder>(R.layout.item_data, null) {

    override fun convert(helper: BaseViewHolder, item: TestEntity) {
        helper.setText(R.id.tvSourceName, item.zfactoryname)
        helper.setText(R.id.tvTotal, "任务总数${item.total}")
        helper.setText(R.id.tvUntreated, "未处理：${item.untreated}")
        helper.setText(R.id.tvOverdue, "已逾期：${item.overdue}")
        helper.setText(R.id.tvProcessed, "已处理：${item.processed}")
    }
}