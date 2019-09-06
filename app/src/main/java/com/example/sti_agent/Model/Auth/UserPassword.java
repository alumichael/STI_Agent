package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserPassword implements Serializable {

    @SerializedName("old_password")
    @Expose
    private String oldPassword;
    @SerializedName("new_password")
    @Expose
    private String newPassword;

    public UserPassword(String oldPassword, String newPassword) {
        super();
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}