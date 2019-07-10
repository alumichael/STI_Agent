package com.example.sti_agent.Model.Vehicle;


import io.realm.RealmList;
import io.realm.RealmObject;

public class Personal_detail extends RealmObject {
    String first_name;
    String last_name;
    String email;
    String gender;
    String phone;
    String resident_address;
    String marital_status;

    String next_of_kin;
    String next_of_kin_phone;
    String next_of_kin_address;
    String prefix;

    String customer_type;
    String company_name;
    String mailing_address;
    String tin_number;
    String office_address;
    String contact_person;

    RealmList<VehicleDetails> vehicle_info;



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

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public void setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
    }

    public String getTin_number() {
        return tin_number;
    }

    public void setTin_number(String tin_number) {
        this.tin_number = tin_number;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public RealmList<VehicleDetails> getVehicle_info() {
        return vehicle_info;
    }

    public void setVehicle_info(RealmList<VehicleDetails> vehicle_info) {
        this.vehicle_info = vehicle_info;
    }

}
