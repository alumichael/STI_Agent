package com.example.sti_agent.Model.Other;

import io.realm.RealmObject;

public class insureProduct extends RealmObject {


    String product;
    String description;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
