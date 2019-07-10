package com.example.sti_agent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignIn extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.sign_in_btn)
    Button sign_in_btn;

    @BindView(R.id.register)
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        sign_in_btn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sign_in_btn:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;
           /* case R.id.image_linear_layout:
                chooseImage();
                break;*/
            case R.id.register:
                startActivity(new Intent(getApplicationContext(),SignUp.class));
                finish();
                break;

        }

    }
}
