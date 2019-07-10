package com.example.sti_agent.adapter;

import com.example.sti_agent.Model.Vehicle.VehiclePolicy;

import io.realm.Realm;

public class RealmHelper  {



    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }
    public void save_motor_insurance(final VehiclePolicy monthly){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                VehiclePolicy vehiclePolicy=realm.copyToRealm(monthly);


            }
        });

    }



   /* public ArrayList<String> retrieve(){
        ArrayList<String> records=new ArrayList<>();

        RealmResults<Monthly> monthly = realm.where(Monthly.class).findAll();

        String income="  ";
        for(Monthly month: monthly){
            //records.add(month.getId());
            records.add(String.valueOf(month.getTotal_income()));
            records.add(String.valueOf(month.getSaving_goal()));
            records.add(String.valueOf(month.getBalance()));
            records.add(String.valueOf(month.getDate()));

            income+=String.valueOf(month.getTotal_income());
        }
        Log.v("Database", income);

        return records;
    }*/



}

