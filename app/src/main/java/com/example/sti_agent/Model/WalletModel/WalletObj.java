package com.example.sti_agent.Model.WalletModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WalletObj implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("history")
    @Expose
    private List<Wallet_History> history = null;
    @SerializedName("message")
    @Expose
    private String message;
    

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Wallet_History> getWallet_History() {
        return history;
    }

    public void setWallet_History(List<Wallet_History> history) {
        this.history = history;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}