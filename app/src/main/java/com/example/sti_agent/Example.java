package com.example.sti_agent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment1.isValidEmailAddress;

public class Example extends AppCompatActivity {



    @BindView(R.id.num1)
    EditText num1;
    @BindView(R.id.num2)
    EditText num2;
    @BindView(R.id.num3)
    EditText num3;
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.resulttotal)
    Button result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);
        ButterKnife.bind(this);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TextView result_txt1=findViewById(R.id.result_txt1);
                    int number1 = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                    int number3 = Integer.parseInt(num3.getText().toString());

                    int total = number1 * number2 * number3;
                    result_txt1.setText(total);




                }catch (Exception e){
                    Snackbar.make(layout,e.getMessage(),Snackbar.LENGTH_LONG).show();

                }

            }
        });




    }



}
