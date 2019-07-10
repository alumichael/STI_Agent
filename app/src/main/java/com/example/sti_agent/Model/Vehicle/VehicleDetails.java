package com.example.sti_agent.Model.Vehicle;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static java.util.UUID.randomUUID;

public class VehicleDetails extends RealmObject {

    public  String period;
    String startDate;
    String policy_type;
    String enhanced_third_party;

    String private_policy;
    //I will notify kazeem for changes
    String commercial_policy;
    String motor_cycle_policy;
    String vehicle_make;
    String vehicle_type;
    String body_type;
    String year;
    String registration_number;
    String chasis_number;
    String engine_number;
    String vehicle_value;

    String motorcylce_value;

    public VehicleDetails(){

    }

    RealmList<VehiclePictures> vehiclePictures;

    @PrimaryKey
    public String id=randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPolicy_type() {
        return policy_type;
    }

    public void setPolicy_type(String policy_type) {
        this.policy_type = policy_type;
    }

    public String getEnhanced_third_party() {
        return enhanced_third_party;
    }

    public void setEnhanced_third_party(String enhanced_third_party) {
        this.enhanced_third_party = enhanced_third_party;
    }

    public String getPrivate_policy() {
        return private_policy;
    }

    public void setPrivate_policy(String private_policy) {
        this.private_policy = private_policy;
    }

    public String getCommercial_policy() {
        return commercial_policy;
    }

    public void setCommercial_policy(String commercial_policy) {
        this.commercial_policy = commercial_policy;
    }


    public String getMotor_cycle_policy() {
        return motor_cycle_policy;
    }

    public void setMotor_cycle_policy(String motor_cycle_policy) {
        this.motor_cycle_policy = motor_cycle_policy;
    }

    public String getVehicle_make() {
        return vehicle_make;
    }

    public void setVehicle_make(String vehicle_make) {
        this.vehicle_make = vehicle_make;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getChasis_number() {
        return chasis_number;
    }

    public void setChasis_number(String chasis_number) {
        this.chasis_number = chasis_number;
    }

    public String getEngine_number() {
        return engine_number;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public String getMotorcylce_value() {
        return motorcylce_value;
    }

    public void setMotorcylce_value(String motorcylce_value) {
        this.motorcylce_value = motorcylce_value;
    }

    public String getVehicle_value() {
        return vehicle_value;
    }

    public void setVehicle_value(String vehicle_value) {
        this.vehicle_value = vehicle_value;
    }

    public RealmList<VehiclePictures> getVehiclePictures() {
        return vehiclePictures;
    }

    public void setVehiclePictures(RealmList<VehiclePictures> vehiclePictures) {
        this.vehiclePictures = vehiclePictures;
    }
}
