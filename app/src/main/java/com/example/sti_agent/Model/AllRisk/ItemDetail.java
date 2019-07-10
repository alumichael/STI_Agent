package com.example.sti_agent.Model.AllRisk;

import io.realm.RealmObject;

public class ItemDetail extends RealmObject {

    String item;
    String value;
    String desc_item;
    String startDate;
    String serial;
    String receipt;


    public  ItemDetail(){

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc_item() {
        return desc_item;
    }

    public void setDesc_item(String desc_item) {
        this.desc_item = desc_item;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }




}
