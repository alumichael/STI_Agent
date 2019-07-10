package com.example.sti_agent.Model.Swiss;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Personal_Detail_swiss extends RealmObject {


    String first_name;
    String last_name;
    String email;
    String gender;
    String phone;
    String resident_address;
    String marital_status;
    String date_of_birth;

    String next_of_kin;
    String next_of_kin_phone;
    String next_of_kin_address;
    String prefix;
    String disability;
    String benefit_category;
    RealmList<AdditionInsured> additionInsureds;


    public  Personal_Detail_swiss(){

    }



    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResident_address() {
        return resident_address;
    }

    public void setResident_address(String resident_address) {
        this.resident_address = resident_address;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNext_of_kin() {
        return next_of_kin;
    }

    public void setNext_of_kin(String next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public String getNext_of_kin_phone() {
        return next_of_kin_phone;
    }

    public void setNext_of_kin_phone(String next_of_kin_phone) {
        this.next_of_kin_phone = next_of_kin_phone;
    }

    public String getNext_of_kin_address() {
        return next_of_kin_address;
    }

    public void setNext_of_kin_address(String next_of_kin_address) {
        this.next_of_kin_address = next_of_kin_address;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getBenefit_category() {
        return benefit_category;
    }

    public void setBenefit_category(String benefit_category) {
        this.benefit_category = benefit_category;
    }

    public RealmList<AdditionInsured> getAdditionInsureds() {
        return additionInsureds;
    }

    public void setAdditionInsureds(RealmList<AdditionInsured> additionInsureds) {
        this.additionInsureds = additionInsureds;
    }




}
