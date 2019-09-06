package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AgentDataHead implements Serializable
{

    @SerializedName("user")
    @Expose
    public AgentAsUser user;
    @SerializedName("agent")
    @Expose
    public Agent agent;
    @SerializedName("wallet_balance")
    @Expose
    public String walletBalance;
    @SerializedName("payout_account")
    @Expose
    public PayoutAccount payoutAccount;

    public AgentAsUser getUser() {
        return user;
    }


    public Agent getAgent() {
        return agent;
    }


    public String getWalletBalance() {
        return walletBalance;
    }


    public PayoutAccount getPayoutAccount() {
        return payoutAccount;
    }


}
