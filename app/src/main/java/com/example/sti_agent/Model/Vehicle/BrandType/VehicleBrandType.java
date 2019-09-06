package com.example.sti_agent.Model.Vehicle.BrandType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VehicleBrandType implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<VehicleTypeData> data = null;
    @SerializedName("record_count")
    @Expose
    private Integer recordCount;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<VehicleTypeData> getData() {
        return data;
    }

    public void setData(List<VehicleTypeData> data) {
        this.data = data;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

}