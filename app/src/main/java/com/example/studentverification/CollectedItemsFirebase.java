package com.example.studentverification;

public class CollectedItemsFirebase {


    String itemName, itemColour, itemDescription;

    public CollectedItemsFirebase() {
    }

    public CollectedItemsFirebase(String itemName, String itemColour, String itemDescription) {
        this.itemName = itemName;
        this.itemColour = itemColour;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemColour() {
        return itemColour;
    }

    public void setItemColour(String itemColour) {
        this.itemColour = itemColour;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}