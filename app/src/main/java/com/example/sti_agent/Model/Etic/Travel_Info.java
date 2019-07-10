package com.example.sti_agent.Model.Etic;

import io.realm.RealmObject;

public class Travel_Info extends RealmObject {
    String trip_duration;


    String start_date;
    String travel_mode;
    String disability;
    String disability_details;
    String place_departure;
    String place_arrival;
    String address_country_of_visit;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }


    public String getTrip_duration() {
        return trip_duration;
    }

    public void setTrip_duration(String trip_duration) {
        this.trip_duration = trip_duration;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDisability_details() {
        return disability_details;
    }

    public void setDisability_details(String disability_details) {
        this.disability_details = disability_details;
    }

    public String getPlace_departure() {
        return place_departure;
    }

    public void setPlace_departure(String place_departure) {
        this.place_departure = place_departure;
    }

    public String getPlace_arrival() {
        return place_arrival;
    }

    public void setPlace_arrival(String place_arrival) {
        this.place_arrival = place_arrival;
    }

    public String getAddress_country_of_visit() {
        return address_country_of_visit;
    }

    public void setAddress_country_of_visit(String address_country_of_visit) {
        this.address_country_of_visit = address_country_of_visit;
    }




}
