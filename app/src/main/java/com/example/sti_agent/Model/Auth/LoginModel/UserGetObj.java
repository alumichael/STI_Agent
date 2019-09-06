package com.example.sti_agent.Model.Auth.LoginModel;

import com.example.sti_agent.Model.Auth.PayoutAccount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserGetObj implements Serializable
{

    @SerializedName("user")
    @Expose
    private UserGetData user;
    @SerializedName("wallet_balance")
    @Expose
    private String walletBalance;
    @SerializedName("policy_data")
    @Expose
    private PolicyData policyData;
    @SerializedName("payout_account")
    @Expose
    private PayoutAccount payoutAccount;


    public UserGetData getUser() {
        return user;
    }

    public void setUser(UserGetData user) {
        this.user = user;
    }

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

    public PolicyData getPolicyData() {
        return policyData;
    }

    public void setPolicyData(PolicyData policyData) {
        this.policyData = policyData;
    }

    public PayoutAccount getPayoutAccount() {
        return payoutAccount;
    }

    public void setPayoutAccount(PayoutAccount payoutAccount) {
        this.payoutAccount = payoutAccount;
    }

}