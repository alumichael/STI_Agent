package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterObj implements Serializable
{

    @SerializedName("user")
    @Expose
    public User user;

    public RegisterObj(User user) {
        super();
        this.user = user;
    }




}