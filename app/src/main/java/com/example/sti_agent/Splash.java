package com.example.sti_agent;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

    @BindView(R.id.img_logo)
    ImageView imgLogo;

    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.txt_version)
    TextView txtVersion;

    @BindView(R.id.app_tag_name_below)
    LinearLayout app_tag_name_below;

    // Animation
    Animation slide_front_left, blink,slide_front_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

//      Create instance of Preference class
        UserPreferences userPreferences = new UserPreferences(this);

//        Display app version on the Screen
        try{
            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = "Version: " + packageInfo.versionName;
            txtVersion.setText(version);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

//        check for first time launch
        if (!userPreferences.isFirstTimeLaunch()) {
            // load the animation
            slide_front_left = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_from_left);
            slide_front_right= AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_from_right);
            blink = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.blink);

            //start animation
            imgLogo.startAnimation(slide_front_left);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            txtDesc.startAnimation(blink);
            txtVersion.startAnimation(blink);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            app_tag_name_below.startAnimation(slide_front_right);

            Thread myThread = new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        startActivity(new Intent(getApplicationContext(),SignUp.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
        }else {
            userPreferences.setFirstTimeLaunch(false);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

}
