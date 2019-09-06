package com.example.sti_agent.Model.Auth.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PolicyData implements Serializable
{

    @SerializedName("vehicle")
    @Expose
    private List<Object> vehicle = null;
    @SerializedName("swiss")
    @Expose
    private List<Object> swiss = null;
    @SerializedName("travel")
    @Expose
    private List<Object> travel = null;
    @SerializedName("all_risk")
    @Expose
    private List<Object> allRisk = null;

    public PolicyData(List<Object> vehicle, List<Object> swiss, List<Object> travel, List<Object> allRisk) {
        super();
        this.vehicle = vehicle;
        this.swiss = swiss;
        this.travel = travel;
        this.allRisk = allRisk;
    }

    public List<Object> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Object> vehicle) {
        this.vehicle = vehicle;
    }

    public List<Object> getSwiss() {
        return swiss;
    }

    public void setSwiss(List<Object> swiss) {
        this.swiss = swiss;
    }

    public List<Object> getTravel() {
        return travel;
    }

    public void setTravel(List<Object> travel) {
        this.travel = travel;
    }

    public List<Object> getAllRisk() {
        return allRisk;
    }

    public void setAllRisk(List<Object> allRisk) {
        this.allRisk = allRisk;
    }

}