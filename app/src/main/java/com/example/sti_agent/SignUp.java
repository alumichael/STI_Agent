package com.example.sti_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.complete_reg_btn)
    Button registrationBtn;

    @BindView(R.id.login)
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        registrationBtn.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.complete_reg_btn:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
           /* case R.id.image_linear_layout:
                chooseImage();
                break;*/
            case R.id.login:
                startActivity(new Intent(getApplicationContext(),SignIn.class));
                finish();
                break;

        }

    }
}
