package com.example.sti_agent.Model.Auth.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserPostObj implements Serializable
{

    @SerializedName("user")
    @Expose
    private UserPostData user;

    public UserPostObj(UserPostData user) {
        super();
        this.user = user;
    }

    public UserPostData getUser() {
        return user;
    }

    public void setUser(UserPostData user) {
        this.user = user;
    }

}