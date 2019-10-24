package com.example.sti_agent.Model.Vehicle;

import io.realm.RealmObject;

public class VehiclePictures extends RealmObject {
    String front_view;
    String back_view;
    String left_view;
    String right_view;


    public VehiclePictures(){

    }

    public String getFront_view() {
        return front_view;
    }

    public void setFront_view(String front_view) {
        this.front_view = front_view;
    }

    public String getBack_view() {
        return back_view;
    }

    public void setBack_view(String back_view) {
        this.back_view = back_view;
    }

    public String getLeft_view() {
        return left_view;
    }

    public void setLeft_view(String left_view) {
        this.left_view = left_view;
    }

    public String getRight_view() {
        return right_view;
    }

    public void setRight_view(String right_view) {
        this.right_view = right_view;
    }

}
