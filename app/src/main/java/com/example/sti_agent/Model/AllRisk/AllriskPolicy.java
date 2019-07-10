package com.example.sti_agent.Model.AllRisk;

import com.example.sti_agent.Model.Marine.Personal_Detail_marine;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static java.util.UUID.randomUUID;

public class AllriskPolicy extends RealmObject {



    String quote_price;
    String  pin;
    String payment_source;
    String agent_id;
    RealmList<Personal_Detail_allrisk> personal_detail_allrisks;

    public  AllriskPolicy(){

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

    public RealmList<Personal_Detail_allrisk> getPersonal_detail_allrisks() {
        return personal_detail_allrisks;
    }

    public void setPersonal_detail_allrisks(RealmList<Personal_Detail_allrisk> personal_detail_allrisks) {
        this.personal_detail_allrisks = personal_detail_allrisks;
    }





}
