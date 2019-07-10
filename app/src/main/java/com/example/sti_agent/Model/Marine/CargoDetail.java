package com.example.sti_agent.Model.Marine;

import io.realm.RealmObject;

public class CargoDetail extends RealmObject {


    String pfi_number;
    String pfi_date;
    String quantity;
    String currency;
    String interest;
    String desc_goods;
    String conversion_rate;
    String loading_port;
    String discharge_port;
    String conveyance_mode;
    String value;
    String picture;

    public CargoDetail(){

    }

    public String getPfi_number() {
        return pfi_number;
    }

    public void setPfi_number(String pfi_number) {
        this.pfi_number = pfi_number;
    }

    public String getPfi_date() {
        return pfi_date;
    }

    public void setPfi_date(String pfi_date) {
        this.pfi_date = pfi_date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getDesc_goods() {
        return desc_goods;
    }

    public void setDesc_goods(String desc_goods) {
        this.desc_goods = desc_goods;
    }


    public String getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(String conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public String getLoading_port() {
        return loading_port;
    }

    public void setLoading_port(String loading_port) {
        this.loading_port = loading_port;
    }

    public String getDischarge_port() {
        return discharge_port;
    }

    public void setDischarge_port(String discharge_port) {
        this.discharge_port = discharge_port;
    }

    public String getConveyance_mode() {
        return conveyance_mode;
    }

    public void setConveyance_mode(String conveyance_mode) {
        this.conveyance_mode = conveyance_mode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }





}
