package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PayoutAccount implements Serializable
{

    @SerializedName("bank")
    @Expose
    private Object bank;
    @SerializedName("account_number")
    @Expose
    private Object accountNumber;
    @SerializedName("account_name")
    @Expose
    private Object accountName;

    public Object getBank() {
        return bank;
    }


    public Object getAccountNumber() {
        return accountNumber;
    }


    public Object getAccountName() {
        return accountName;
    }


}