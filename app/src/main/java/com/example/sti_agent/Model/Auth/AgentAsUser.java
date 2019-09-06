package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AgentAsUser implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
   

    public Integer getId() {
        return id;
    }
    

    public String getEmail() {
        return email;
    }
    

    public String getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }
    

    public Object getUsername() {
        return username;
    }
    

    public Object getBio() {
        return bio;
    }
    

    public String getImage() {
        return image;
    }
    

    public String getToken() {
        return token;
    }
    

    public String getPhone() {
        return phone;
    }
    

    public String getPin() {
        return pin;
    }

 

    public String getFirstName() {
        return firstName;
    }
    

    public String getLastName() {
        return lastName;
    }
    

}
