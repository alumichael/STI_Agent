package com.example.sti_agent.Model.WalletModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetWalletFunded implements Serializable
{

    @SerializedName("wallet")
    @Expose
    private Wallet wallet;
   /* @SerializedName("wallet_history")
    @Expose
    private List<WalletHistory> walletHistory = null;*/

    public Wallet getWallet() {
        return wallet;
    }


    /*public List<WalletHistory> getWalletHistory() {
        return walletHistory;
    }

    public void setWalletHistory(List<WalletHistory> walletHistory) {
        this.walletHistory = walletHistory;
    }*/

}