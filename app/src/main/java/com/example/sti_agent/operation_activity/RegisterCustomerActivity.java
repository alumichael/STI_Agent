package com.example.sti_agent.operation_activity;

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

import com.example.sti_agent.MainActivity;
import com.example.sti_agent.R;
import com.example.sti_agent.SignUp;
import com.example.sti_agent.UserPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterCustomerActivity extends AppCompatActivity {

    @BindView(R.id.img_logo)
    ImageView imgLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_customer);
        ButterKnife.bind(this);


    }

}
