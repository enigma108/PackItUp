package com.android.packitup;

public class Item {

    private String itemName;
    private boolean isSelected;

    public Item(String itemName) {
        this.itemName = itemName;
        this.isSelected = false;
    }

    public Item(String itemName, boolean isSelected) {
        this.itemName = itemName;
        this.isSelected = isSelected;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}

