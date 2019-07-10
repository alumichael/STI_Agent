package com.example.sti_agent.Model.Other;

import com.example.sti_agent.Model.AllRisk.ItemDetail;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Personal_Detail_other extends RealmObject {


    String policy_type;

    String first_name;
    String last_name;
    String email;
    String mailing_addr;
    String gender;
    String phone;
    String resident_address;
    String company_name;
    String tin_num;

    String next_of_kin;

    String prefix;
    String contact_person;
    String office_address;
    String picture;



    RealmList<insureProduct> insureProducts;

    public  Personal_Detail_other(){

    }

    public String getPolicy_type() {
        return policy_type;
    }

    public void setPolicy_type(String policy_type) {
        this.policy_type = policy_type;
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

    public String getMailing_addr() {
        return mailing_addr;
    }

    public void setMailing_addr(String mailing_addr) {
        this.mailing_addr = mailing_addr;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTin_num() {
        return tin_num;
    }

    public void setTin_num(String tin_num) {
        this.tin_num = tin_num;
    }

    public String getNext_of_kin() {
        return next_of_kin;
    }

    public void setNext_of_kin(String next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public RealmList<insureProduct> getInsureProducts() {
        return insureProducts;
    }

    public void setInsureProducts(RealmList<insureProduct> insureProducts) {
        this.insureProducts = insureProducts;
    }






}
