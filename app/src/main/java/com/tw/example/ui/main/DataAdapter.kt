package com.tw.example.ui.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tw.example.R
import com.tw.example.data.entity.TestEntity


class DataAdapter : BaseQuickAdapter<TestEntity, BaseViewHolder>(R.layout.item_data, null) {

    override fun convert(helper: BaseViewHolder, item: TestEntity) {
        helper.setText(R.id.tvSourceName, item.zfactoryname)
        helper.setText(R.id.tvTotal, String.format("任务总数：%s", item.total))
        helper.setText(R.id.tvUntreated, String.format("未处理：%s", item.untreated))
        helper.setText(R.id.tvOverdue, String.format("已逾期：%s", item.overdue))
        helper.setText(R.id.tvProcessed, String.format("已处理：%s", item.processed))
    }
}