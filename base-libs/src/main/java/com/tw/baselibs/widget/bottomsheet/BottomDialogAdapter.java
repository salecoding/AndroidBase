package com.tw.baselibs.widget.bottomsheet;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tw.baselibs.R;

public class BottomDialogAdapter extends BaseQuickAdapter<ItemModel, BaseViewHolder> {
    public BottomDialogAdapter() {
        super(R.layout.item_bottom_dialog, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemModel item) {
        helper.setText(R.id.tvItemName, item.getItemName());
    }
}
