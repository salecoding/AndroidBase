package com.tw.baselibs.widget.bottomsheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tw.baselibs.R;
import com.tw.baselibs.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("AppCompatCustomView")
public class DropDownBoxView extends TextView implements View.OnClickListener, BottomDialogAdapter.OnItemClickListener {
    private List<ItemModel> itemModelList;
    private BottomDialogAdapter mBottomDialogAdapter;
    private View dropDownBox;
    private RecyclerView rvDataList;
    private BottomSheetDialog bottomSheetDialog;
    private ItemModel selectedItem;

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        if (itemModelList == null) itemModelList = new ArrayList<>();
        itemModelList.add(0, new ItemModel(0, "请选择"));
        this.itemModelList = itemModelList;
        mBottomDialogAdapter.setNewData(itemModelList);
    }

    public ItemModel getSelectedItem() {
        return selectedItem;
    }

    public int getSelectedItemId() {
        return selectedItem != null ? selectedItem.getItemId() : 0;
    }

    public String getSelectedItemName() {
        return selectedItem != null ? selectedItem.getItemName() : "请选择";
    }

    public void setSelectedItem(ItemModel selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void onChanged() {
        selectedItem = null;
        this.setText(getSelectedItemName());
    }

    public DropDownBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        init();
    }

    private void initView(Context context) {
        dropDownBox = LayoutInflater.from(context).inflate(R.layout.layout_drop_down_box, null);
        rvDataList = dropDownBox.findViewById(R.id.rvDataList);
        rvDataList.setItemAnimator(new DefaultItemAnimator());
        rvDataList.setLayoutManager(new LinearLayoutManager(context));
        rvDataList.addItemDecoration(new RecyclerViewDivider(context, LinearLayoutManager.HORIZONTAL));
        mBottomDialogAdapter = new BottomDialogAdapter();
        mBottomDialogAdapter.setOnItemClickListener(this);
        rvDataList.setAdapter(mBottomDialogAdapter);

        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(dropDownBox);

        this.setText(getSelectedItemName());
    }

    private void init() {
        this.setFocusable(false);
        this.setFocusableInTouchMode(false);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        bottomSheetDialog.show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        bottomSheetDialog.dismiss();
        ItemModel itemModel = mBottomDialogAdapter.getItem(position);
        this.setText(itemModel.getItemName());
        this.setSelectedItem(itemModel);
    }

}
