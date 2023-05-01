package com.example.studentverification;

public class SearchItem {

    String itemName, itemColor, description, regNumber;

    public SearchItem() {
    }

    public SearchItem(String itemName, String itemColor, String description, String regNumber) {
        this.itemName = itemName;
        this.itemColor = itemColor;
        this.description = description;
        this.regNumber = regNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
}
