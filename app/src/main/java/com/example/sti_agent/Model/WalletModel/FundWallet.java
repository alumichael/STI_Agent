package com.example.sti_agent.Model.WalletModel;

public class FundWallet {

    String amount;
    String description;
    String transaction_type;

    public FundWallet(String amount, String description, String transaction_type) {
        this.amount = amount;
        this.description = description;
        this.transaction_type = transaction_type;
    }


}
