package com.example.sti_agent.Model.Marine;

import com.example.sti_agent.Model.Vehicle.Personal_detail;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static java.util.UUID.randomUUID;

public class MarinePolicy extends RealmObject {



    String quote_price;
    String  pin;
    String payment_source;
    String agent_id;
    RealmList<Personal_Detail_marine> personal_detail_marines;

    public  MarinePolicy(){

    }


    @PrimaryKey
    private String id=randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote_price() {
        return quote_price;
    }

    public void setQuote_price(String quote_price) {
        this.quote_price = quote_price;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPayment_source() {
        return payment_source;
    }

    public void setPayment_source(String payment_source) {
        this.payment_source = payment_source;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public RealmList<Personal_Detail_marine> getPersonal_detail_marines() {
        return personal_detail_marines;
    }

    public void setPersonal_detail_marines(RealmList<Personal_Detail_marine> personal_detail_marines) {
        this.personal_detail_marines = personal_detail_marines;
    }
}
