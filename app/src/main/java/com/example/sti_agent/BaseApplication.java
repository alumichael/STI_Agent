package com.example.sti_agent;

import android.app.Application;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

//import co.paystack.android.PaystackSdk;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);


        //PaystackSdk.initialize(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        Map configCloudinary = new HashMap();
        configCloudinary.put("cloud_name", "aaua");
        configCloudinary.put("api_key", "942621547178879");
        configCloudinary.put("api_secret", "3a6roBjSSgD0ulmK41kBczTu_VE");
        configCloudinary.put("upload_preset", "xbiscrhh");
        MediaManager.init(this, configCloudinary);


    }
}
