package com.tw.baselibs.widget.bottomsheet;

public class ItemModel {
    private int itemId;
    private String itemName;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ItemModel(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }
}
