package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangePassPost implements Serializable
{

    @SerializedName("user")
    @Expose
    private UserPassword user;

    public ChangePassPost(UserPassword user) {
        super();
        this.user = user;
    }

    public UserPassword getUser() {
        return user;
    }

    public void setUser(UserPassword user) {
        this.user = user;
    }
}
