package com.example.sti_agent.Model.Vehicle.VehicleBrand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Vehicles_Brand implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<VehicleData> data = null;
    @SerializedName("record_count")
    @Expose
    private Integer recordCount;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<VehicleData> getVehicleData() {
        return data;
    }

    public void setVehicleData(List<VehicleData> data) {
        this.data = data;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

}