package com.example.sti_agent.Model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agent implements Serializable
{

    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("number")
    @Expose
    public Long number;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("date_of_birth")
    @Expose
    public String dateOfBirth;
    @SerializedName("bvn")
    @Expose
    public String bvn;
    @SerializedName("identification")
    @Expose
    public String identification;
    @SerializedName("document")
    @Expose
    public String document;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public Integer id;

    public String getFirstName() {
        return firstName;
    }


    public Long getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBvn() {
        return bvn;
    }

    public String getIdentification() {
        return identification;
    }

    public String getDocument() {
        return document;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getId() {
        return id;
    }


}