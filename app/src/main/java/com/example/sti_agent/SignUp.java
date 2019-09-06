package com.example.sti_agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.sti_agent.Forms.MotorInsuredForm;
import com.example.sti_agent.Import.ImportingForm;
import com.example.sti_agent.Model.Auth.Agent;
import com.example.sti_agent.Model.Auth.AgentDataHead;
import com.example.sti_agent.Model.Auth.RegisterObj;
import com.example.sti_agent.Model.Auth.User;
import com.example.sti_agent.Model.Errors.APIError;
import com.example.sti_agent.Model.Errors.ErrorUtils;
import com.example.sti_agent.Model.ServiceGenerator;
import com.example.sti_agent.retrofit_interface.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment1.isValidEmailAddress;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.user_type_spinner)
    Spinner mUserTypeSpinner;
    @BindView(R.id.inputLayoutFirstName)
    TextInputLayout mInputLayoutFirstName;
    @BindView(R.id.firstname_editxt)
    EditText mFirstNameEditxt;
    @BindView(R.id.inputLayoutLastName)
    TextInputLayout mInputLayoutLastName;
    @BindView(R.id.lastname_editxt)
    EditText mLastNameEditxt;
    @BindView(R.id.inputLayoutCompanyName)
    TextInputLayout mInputLayoutCompanyName;
    @BindView(R.id.companyname_editxt)
    EditText mCompanynameEditxt;
    @BindView(R.id.user_img)
    CircleImageView user_img;
    @BindView(R.id.inputLayoutEmail)
    TextInputLayout mInputLayoutEmail;
    @BindView(R.id.email_editxt)
    EditText mEmailEditxt;
    @BindView(R.id.inputLayoutPassword)
    TextInputLayout mInputLayoutPassword;
    @BindView(R.id.password_editxt)
    EditText mPasswordEditxt;
    @BindView(R.id.inputLayoutContactPerson)
    TextInputLayout mInputLayoutContactPerson;
    @BindView(R.id.contact_person_editxt)
    EditText mContactPersonEditxt;
    @BindView(R.id.inputLayoutOfAddr)
    TextInputLayout mInputLayoutOfAddr;
    @BindView(R.id.office_addr_editxt)
    EditText mOfficeAddrEditxt;
    @BindView(R.id.inputLayoutPhoneNum)
    TextInputLayout mInputLayoutPhoneNum;
    @BindView(R.id.phone_num_editxt)
    EditText mPhoneNumEditxt;
    @BindView(R.id.inputLayoutCompanyRegNum)
    TextInputLayout mInputLayoutCompanyRegNum;
    @BindView(R.id.company_reg_num_editxt)
    EditText mCompanyRegNumEditxt;
    @BindView(R.id.inputLayoutDateofBirth)
    TextInputLayout mInputLayoutDateofBirth;
    @BindView(R.id.birth_day_editxt)
    EditText mBirthDayEditxt;
    @BindView(R.id.inputLayoutExpdateOfLicences)
    TextInputLayout mInputLayoutExpdateOfLicences;
    @BindView(R.id.exp_date_license_editxt)
    EditText mExpDateLicenseEditxt;
    @BindView(R.id.gender_spinner)
    Spinner mGenderSpinner;
    @BindView(R.id.inputLayoutAddress)
    TextInputLayout mInputLayoutAddress;
    @BindView(R.id.address_editxt)
    EditText mAddressEditxt;
    @BindView(R.id.inputLayoutCountry)
    TextInputLayout mInputLayoutCountry;
    @BindView(R.id.country_editxt)
    EditText mCountryEditxt;
    @BindView(R.id.inputLayoutCity)
    TextInputLayout mInputLayoutCity;
    @BindView(R.id.city_editxt)
    EditText mCityEditxt;
    @BindView(R.id.inputLayoutBVN)
    TextInputLayout mInputLayoutBVN;
    @BindView(R.id.bvn_editxt)
    EditText mBvnEditxt;
    @BindView(R.id.upload_file)
    Button mUploadFile;
    @BindView(R.id.upload_id_txt)
    TextView upload_id_txt;
    @BindView(R.id.upload_file_txt)
    TextView upload_file_txt;
    @BindView(R.id.complete_reg_btn)
    Button registrationBtn;
    @BindView(R.id.layout_upload_pass)
    LinearLayout layout_upload_pass;

    @BindView(R.id.choose_country_spinner)
    LinearLayout choose_country_spinner;


    @BindView(R.id.choose_other_country)
    LinearLayout choose_other_country;

    @BindView(R.id.country_spinner)
    Spinner country_spinner;

    @BindView(R.id.state_spinner)
    Spinner state_spinner;

    @BindView(R.id.upload_id_back)
    Button upload_id_back;

    @BindView(R.id.upload_id_front)
    Button upload_id_front;


    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.layout_signUp)
    FrameLayout layout_signUp;

    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;
    DatePickerDialog datePickerDialog1,datePickerDialog2;

    int PICK_IMAGE_PASSPORT = 1;
    int CAM_IMAGE_PASSPORT=11;
    int PICK_IMAGE_FRONT = 2;
    int CAM_IMAGE_FRONT =22;
    int PICK_IMAGE_BACK = 3;
    int CAM_IMAGE_BACK=33;
    int PICK_IMAGE_FILE = 4;
    int CAM_IMAGE_FILE=44;
    private String cameraFilePath;
    private Uri passportUri,uploadBack_uri,uploadFront_uri,uploadFile_uri;
    String userTypeString,genderString,birthdayString,licenseDateString,countryString,stateString,bvnString;
    NetworkConnection networkConnection=new NetworkConnection();

    public String profile_photo_url,id_front_url,id_back_url,license_photo_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //Underline CLick to Login
        login.setPaintFlags(login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        login.setText("Click to Login");

        //set clickable


        showBirthdayPicker();
        showLicensExpDatePicker();
        userType();
        genderType();
        countryType();
        stateType();


        registrationBtn.setOnClickListener(this);
        login.setOnClickListener(this);
        layout_upload_pass.setOnClickListener(this);
        upload_id_back.setOnClickListener(this);
        upload_id_front.setOnClickListener(this);
        mUploadFile.setOnClickListener(this);
        mBirthDayEditxt.setOnClickListener(this);
        mExpDateLicenseEditxt.setOnClickListener(this);
    }

    private void userType() {

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.user_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mUserTypeSpinner.setAdapter(staticAdapter);

        mUserTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String userString = (String) parent.getItemAtPosition(position);
                if(userString.equals("Agent")){

                    mUserTypeSpinner.setVisibility(View.VISIBLE);
                    mUserTypeSpinner.setClickable(true);

                    //De-Visualizing the broker type form
                    mInputLayoutCompanyName.setVisibility(View.GONE);
                    mInputLayoutCompanyName.setClickable(false);
                    mInputLayoutCompanyRegNum.setVisibility(View.GONE);
                    mInputLayoutCompanyRegNum.setClickable(false);
                    mInputLayoutExpdateOfLicences.setVisibility(View.GONE);
                    mInputLayoutExpdateOfLicences.setClickable(false);

                    mInputLayoutContactPerson.setVisibility(View.GONE);
                    mInputLayoutContactPerson.setClickable(false);
                    mInputLayoutOfAddr.setVisibility(View.GONE);
                    mInputLayoutOfAddr.setClickable(false);
                    mUploadFile.setVisibility(View.GONE);
                    mUploadFile.setClickable(false);
                    upload_file_txt.setVisibility(View.GONE);

                    choose_other_country.setVisibility(View.GONE);
                    choose_other_country.setClickable(false);
                    mInputLayoutCountry.setClickable(false);
                    mInputLayoutCity.setClickable(false);


                    //Visualizing the agent form
                    mInputLayoutFirstName.setVisibility(View.VISIBLE);
                    mInputLayoutFirstName.setClickable(true);
                    mInputLayoutLastName.setVisibility(View.VISIBLE);
                    mInputLayoutLastName.setClickable(true);
                    mInputLayoutLastName.setVisibility(View.VISIBLE);
                    mInputLayoutLastName.setClickable(true);
                    mUserTypeSpinner.setVisibility(View.VISIBLE);
                    mUserTypeSpinner.setClickable(true);
                    layout_upload_pass.setVisibility(View.VISIBLE);
                    layout_upload_pass.setClickable(true);


                    mPhoneNumEditxt.setVisibility(View.VISIBLE);
                    mPhoneNumEditxt.setClickable(true);
                    mInputLayoutEmail.setVisibility(View.VISIBLE);
                    mInputLayoutEmail.setClickable(true);
                    mInputLayoutPassword.setVisibility(View.VISIBLE);
                    mInputLayoutPassword.setClickable(true);
                    mInputLayoutDateofBirth.setVisibility(View.VISIBLE);
                    mInputLayoutDateofBirth.setClickable(true);
                    mGenderSpinner.setVisibility(View.VISIBLE);
                    mGenderSpinner.setClickable(true);
                    mInputLayoutAddress.setVisibility(View.VISIBLE);
                    mInputLayoutAddress.setClickable(true);
                    mInputLayoutCountry.setVisibility(View.VISIBLE);
                    mInputLayoutCountry.setClickable(true);
                    mInputLayoutCity.setVisibility(View.VISIBLE);
                    mInputLayoutCity.setClickable(true);
                    mInputLayoutBVN.setVisibility(View.VISIBLE);
                    mInputLayoutBVN.setClickable(true);

                    choose_country_spinner.setVisibility(View.VISIBLE);
                    choose_country_spinner.setClickable(true);
                    country_spinner.setClickable(true);
                    state_spinner.setClickable(true);

                    upload_id_txt.setVisibility(View.VISIBLE);

                    upload_id_back.setVisibility(View.VISIBLE);
                    upload_id_back.setClickable(true);

                    upload_id_front.setVisibility(View.VISIBLE);
                    upload_id_front.setClickable(true);


                }else if(userString.equals("Broker")){

                    mUserTypeSpinner.setVisibility(View.VISIBLE);
                    mUserTypeSpinner.setClickable(true);

                    layout_upload_pass.setVisibility(View.GONE);
                    layout_upload_pass.setClickable(false);

                    //De-Visualizing the agent form
                    mInputLayoutFirstName.setVisibility(View.GONE);
                    mInputLayoutFirstName.setClickable(false);
                    mInputLayoutLastName.setVisibility(View.GONE);
                    mInputLayoutLastName.setClickable(false);


                    mInputLayoutDateofBirth.setVisibility(View.GONE);
                    mInputLayoutDateofBirth.setClickable(false);
                    mGenderSpinner.setVisibility(View.GONE);
                    mGenderSpinner.setClickable(false);
                    mInputLayoutAddress.setVisibility(View.GONE);
                    mInputLayoutAddress.setClickable(false);
                    mInputLayoutBVN.setVisibility(View.GONE);
                    mInputLayoutBVN.setClickable(false);

                    upload_id_txt.setVisibility(View.GONE);

                    upload_id_back.setVisibility(View.GONE);
                    upload_id_back.setClickable(false);

                    upload_id_front.setVisibility(View.GONE);
                    upload_id_front.setClickable(false);

                    choose_other_country.setVisibility(View.GONE);
                    choose_other_country.setClickable(false);
                    mInputLayoutCountry.setClickable(false);
                    mInputLayoutCity.setClickable(false);




                    //Visualizing the brokers form
                    mInputLayoutCompanyName.setVisibility(View.VISIBLE);
                    mInputLayoutCompanyName.setClickable(true);
                    mInputLayoutCompanyRegNum.setVisibility(View.VISIBLE);
                    mInputLayoutCompanyRegNum.setClickable(true);
                    mInputLayoutExpdateOfLicences.setVisibility(View.VISIBLE);
                    mInputLayoutExpdateOfLicences.setClickable(true);

                    mInputLayoutContactPerson.setVisibility(View.VISIBLE);
                    mInputLayoutContactPerson.setClickable(true);
                    mInputLayoutOfAddr.setVisibility(View.VISIBLE);
                    mInputLayoutOfAddr.setClickable(true);


                    choose_country_spinner.setVisibility(View.VISIBLE);
                    choose_country_spinner.setClickable(true);
                    country_spinner.setClickable(true);
                    state_spinner.setClickable(true);


                    mUploadFile.setVisibility(View.VISIBLE);
                    mUploadFile.setClickable(true);
                    upload_file_txt.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mUserTypeSpinner.getItemAtPosition(0);


                mUserTypeSpinner.setVisibility(View.VISIBLE);
                mUserTypeSpinner.setClickable(true);

                //De-Visualizing the broker type form
                mInputLayoutCompanyName.setVisibility(View.GONE);
                mInputLayoutCompanyName.setClickable(false);
                mInputLayoutCompanyRegNum.setVisibility(View.GONE);
                mInputLayoutCompanyRegNum.setClickable(false);
                mInputLayoutExpdateOfLicences.setVisibility(View.GONE);
                mInputLayoutExpdateOfLicences.setClickable(false);

                mInputLayoutContactPerson.setVisibility(View.GONE);
                mInputLayoutContactPerson.setClickable(false);
                mInputLayoutOfAddr.setVisibility(View.GONE);
                mInputLayoutOfAddr.setClickable(false);
                mUploadFile.setVisibility(View.GONE);
                mUploadFile.setClickable(false);
                upload_file_txt.setVisibility(View.GONE);


                //Visualizing the agent form
                mInputLayoutFirstName.setVisibility(View.VISIBLE);
                mInputLayoutFirstName.setClickable(true);
                mInputLayoutLastName.setVisibility(View.VISIBLE);
                mInputLayoutLastName.setClickable(true);
                mUserTypeSpinner.setVisibility(View.VISIBLE);
                mUserTypeSpinner.setClickable(true);

                layout_upload_pass.setVisibility(View.VISIBLE);
                layout_upload_pass.setClickable(true);


                mPhoneNumEditxt.setVisibility(View.VISIBLE);
                mPhoneNumEditxt.setClickable(true);
                mInputLayoutEmail.setVisibility(View.VISIBLE);
                mInputLayoutEmail.setClickable(true);
                mInputLayoutPassword.setVisibility(View.VISIBLE);
                mInputLayoutPassword.setClickable(true);
                mInputLayoutDateofBirth.setVisibility(View.VISIBLE);
                mInputLayoutDateofBirth.setClickable(true);
                mGenderSpinner.setVisibility(View.VISIBLE);
                mGenderSpinner.setClickable(true);
                mInputLayoutAddress.setVisibility(View.VISIBLE);
                mInputLayoutAddress.setClickable(true);
                mInputLayoutCountry.setVisibility(View.VISIBLE);
                mInputLayoutCountry.setClickable(true);
                mInputLayoutCity.setVisibility(View.VISIBLE);
                mInputLayoutCity.setClickable(true);
                mInputLayoutBVN.setVisibility(View.VISIBLE);
                mInputLayoutBVN.setClickable(true);

                upload_id_txt.setVisibility(View.VISIBLE);

                upload_id_back.setVisibility(View.VISIBLE);
                upload_id_back.setClickable(true);

                upload_id_front.setVisibility(View.VISIBLE);
                upload_id_front.setClickable(true);


            }
        });
    }

    private void genderType() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(staticAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String gender_String = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGenderSpinner.getItemAtPosition(0);
            }
        });

    }

    private void countryType() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.country_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        country_spinner.setAdapter(staticAdapter);
        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String country_String = (String) parent.getItemAtPosition(position);
                if (position == 2) {
                    choose_country_spinner.setVisibility(View.GONE);
                    choose_country_spinner.setClickable(false);
                    country_spinner.setClickable(false);
                    state_spinner.setClickable(false);
                    choose_other_country.setVisibility(View.VISIBLE);
                    choose_other_country.setClickable(true);
                }else if(position==1){
                    choose_country_spinner.setVisibility(View.VISIBLE);
                    choose_country_spinner.setClickable(true);
                    country_spinner.setClickable(true);
                    state_spinner.setClickable(true);
                    choose_other_country.setVisibility(View.GONE);
                    choose_other_country.setClickable(false);
                    mInputLayoutCountry.setClickable(false);
                    mInputLayoutCity.setClickable(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                country_spinner.getItemAtPosition(0);
            }
        });

    }

    private void stateType() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.state_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        state_spinner.setAdapter(staticAdapter);

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String state_String = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                state_spinner.getItemAtPosition(0);
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.complete_reg_btn:
                ValidateForm();
                break;
            case R.id.layout_upload_pass:
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose Mode of Entry");
// add a list
                String[] entry = {"Camera", "Gallery"};
                builder.setItems(entry, (dialog, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            checkPremission();
                            //choosePassport_camera();
                            dialog.dismiss();
                            break;

                        case 1: // export

                            choosePassport();
                            dialog.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.upload_id_back:
                // setup the alert builder
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Choose Mode of Entry");

// add a list
                String[] entry2 = {"Camera", "Gallery"};
                builder2.setItems(entry2, (dialog2, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdBack_camera();
                            dialog2.dismiss();
                            break;

                        case 1: // export
                            
                            chooseIdBack();
                            dialog2.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                upload_id_back.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                break;
            case R.id.upload_id_front:

                // setup the alert builder
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("Choose Mode of Entry");

// add a list
                String[] entry3 = {"Camera", "Gallery"};
                builder3.setItems(entry3, (dialog3, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdFront_camera();
                            dialog3.dismiss();
                            break;

                        case 1: // export

                            chooseIdFront();
                            dialog3.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog3 = builder3.create();
                dialog3.show();
                upload_id_front.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                break;

            case R.id.upload_file:
                // setup the alert builder
                AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
                builder4.setTitle("Choose Mode of Entry");

// add a list
                String[] entry4 = {"Camera", "Gallery"};
                builder4.setItems(entry4, (dialog4, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdLicense_camera();
                            dialog4.dismiss();
                            break;

                        case 1: // export

                            chooseLicenseFile();
                            dialog4.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog4 = builder4.create();
                dialog4.show();
                mUploadFile.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                
                break;

            case R.id.birth_day_editxt:
                datePickerDialog1.show();
                break;
            case R.id.exp_date_license_editxt:
                datePickerDialog2.show();
                break;


            case R.id.login:
                startActivity(new Intent(getApplicationContext(),SignIn.class));
                finish();
                break;

        }

    }


    private void ValidateForm() {

        if (networkConnection.isNetworkConnected(this)) {
            boolean isValid = true;
            if (mFirstNameEditxt.getText().toString().isEmpty()&&mInputLayoutFirstName.isClickable()) {
                mInputLayoutFirstName.setError("First Name is required");
                isValid = false;
            } else {
                mInputLayoutFirstName.setErrorEnabled(false);
            }
            if (mLastNameEditxt.getText().toString().isEmpty()&&mInputLayoutLastName.isClickable()) {
                mInputLayoutLastName.setError("Last Name is required");
                isValid = false;
            } else {
                mInputLayoutLastName.setErrorEnabled(false);
            }
            if (mCompanynameEditxt.getText().toString().isEmpty()&&mInputLayoutCompanyName.isClickable()) {
                mInputLayoutCompanyName.setError("Company Name is required");
                isValid = false;
            } else {
                mInputLayoutCompanyName.setErrorEnabled(false);
            }

            if (mCompanyRegNumEditxt.getText().toString().isEmpty()&&mInputLayoutCompanyRegNum.isClickable()) {
                mInputLayoutCompanyRegNum.setError("Registration Number is required");
                isValid = false;
            } else {
                mInputLayoutCompanyRegNum.setErrorEnabled(false);
            }

            if (mExpDateLicenseEditxt.getText().toString().isEmpty()&&mInputLayoutExpdateOfLicences.isClickable()) {
                mInputLayoutExpdateOfLicences.setError("License Expiry date is required");
                isValid = false;
            } else {
                mInputLayoutExpdateOfLicences.setErrorEnabled(false);
            }
            if (mContactPersonEditxt.getText().toString().isEmpty()&&mInputLayoutContactPerson.isClickable()) {
                mInputLayoutContactPerson.setError("Contact Person is required");
                isValid = false;
            } else {
                mInputLayoutContactPerson.setErrorEnabled(false);
            }

            if (mCountryEditxt.getText().toString().isEmpty()&&mInputLayoutCountry.isClickable()) {
                mInputLayoutCountry.setError("Country is required");
                isValid = false;
            } else {
                mInputLayoutContactPerson.setErrorEnabled(false);
            }

            if (mOfficeAddrEditxt.getText().toString().isEmpty()&&mInputLayoutOfAddr.isClickable()) {
                mInputLayoutOfAddr.setError("Office Address is required");
                isValid = false;
            } else {
                mInputLayoutOfAddr.setErrorEnabled(false);
            }

            if (mEmailEditxt.getText().toString().isEmpty()&&mInputLayoutEmail.isClickable()) {
                mInputLayoutEmail.setError("Email is required!");
                isValid = false;
            } else if (!isValidEmailAddress(mEmailEditxt.getText().toString())&&mInputLayoutEmail.isClickable()) {
                mInputLayoutEmail.setError("Valid Email is required!");
                isValid = false;
            } else {
                mInputLayoutEmail.setErrorEnabled(false);
            }
            if (mPasswordEditxt.getText().toString().isEmpty()&&mInputLayoutPassword.isClickable()) {
                mInputLayoutPassword.setError("Password is required!");
                isValid = false;
            } else if (mPasswordEditxt.getText().toString().trim().length()<6 && mInputLayoutPassword.isClickable()) {
                mInputLayoutPassword.setError("Your Password must not less than 6 character");
                isValid = false;
            } else {
                mInputLayoutPassword.setErrorEnabled(false);
            }
            if (mPhoneNumEditxt.getText().toString().isEmpty()&&mInputLayoutPhoneNum.isClickable()) {
                mInputLayoutPhoneNum.setError("Phone number is required");
                isValid = false;
            } else if (mPhoneNumEditxt.getText().toString().trim().length() < 11&&mInputLayoutPhoneNum.isClickable()) {
                mInputLayoutPhoneNum.setError("Your Phone number must be 11 in length");
                isValid = false;
            } else {
                mInputLayoutPhoneNum.setErrorEnabled(false);
            }
            if (mBirthDayEditxt.getText().toString().isEmpty()&&mInputLayoutDateofBirth.isClickable()) {
                mInputLayoutDateofBirth.setError("Date of birth is required");
                isValid = false;
            } else {
                mInputLayoutDateofBirth.setErrorEnabled(false);
            }
            if (mAddressEditxt.getText().toString().isEmpty()&&mInputLayoutAddress.isClickable()) {
                mInputLayoutAddress.setError("Address is required");
                isValid = false;
            } else {
                mInputLayoutAddress.setErrorEnabled(false);
            }

            if (mCityEditxt.getText().toString().isEmpty()&&mInputLayoutCity.isClickable()) {
                mInputLayoutCity.setError("City is required");
                isValid = false;
            } else {
                mInputLayoutCity.setErrorEnabled(false);
            }

            if (mBvnEditxt.getText().toString().isEmpty()&&mInputLayoutBVN.isClickable()) {
                bvnString="null";
            }

            userTypeString = mUserTypeSpinner.getSelectedItem().toString();
            if (userTypeString.equals("Select User Type")&&mUserTypeSpinner.isClickable()) {
                showMessage("Select User Type");
                isValid = false;
            }

            genderString = mGenderSpinner.getSelectedItem().toString();
            if (genderString.equals("Gender")&&mGenderSpinner.isClickable()) {
                showMessage("Select Gender");
                isValid = false;
            }

            genderString = mGenderSpinner.getSelectedItem().toString();
            if (genderString.equals("Gender")&&mGenderSpinner.isClickable()) {
                showMessage("Select Gender");
                isValid = false;
            }

            countryString = country_spinner.getSelectedItem().toString();
            if (countryString.equals("Country")&&country_spinner.isClickable()) {
                showMessage("Select Country");
                isValid = false;
            }

            stateString = state_spinner.getSelectedItem().toString();
            if (stateString.equals("State")&&state_spinner.isClickable()) {
                showMessage("Select State");
                isValid = false;
            }

            if (passportUri==null&&layout_upload_pass.isClickable()) {
                showMessage("Select Passport");
                isValid = false;
            }

            if (uploadBack_uri==null&&upload_id_back.isClickable()) {
                showMessage("Select ID Back View");
                isValid = false;
            }
            if (uploadFront_uri==null&&upload_id_front.isClickable()) {
                showMessage("Select ID Front View");
                isValid = false;
            }

            if (uploadFile_uri==null&&mUploadFile.isClickable()) {
                showMessage("Select License to upload");
                isValid = false;
            }

            if (isValid) {

                //Post Request to Api

                sendData();


            }

            //
            return;
        }
        showMessage("No Internet connection discovered!");
    }

//choose passport through gallery
    private void choosePassport() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_PASSPORT);
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }


    private static final int REQUEST_RUNTIME_PERMISSION = 1;

    public void checkPremission() {
        //select which permission you want
        final String permission = Manifest.permission.CAMERA;
        //final String permission = Manifest.permission.Storage;
        // if in fragment use getActivity()
        if (ContextCompat.checkSelfPermission(SignUp.this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SignUp.this, permission)) {

            } else {
                ActivityCompat.requestPermissions(SignUp.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_RUNTIME_PERMISSION);
            }
        } else {
            // you have permission go ahead
            choosePassport_camera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_RUNTIME_PERMISSION:
                final int numOfRequest = grantResults.length;
                final boolean isGranted = numOfRequest == 1
                        && PackageManager.PERMISSION_GRANTED == grantResults[numOfRequest - 1];
                if (isGranted) {
                    // you have permission go ahead
                    choosePassport_camera();
                }else{
                    // you dont have permission show toast
                    showMessage("Re-Click to Upload");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

//choose passport through camera mode
private void choosePassport_camera() {

    try {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
        startActivityForResult(intent, CAM_IMAGE_PASSPORT);
    } catch (IOException ex) {
        ex.printStackTrace();
        showMessage("Invalid Entry");
        Log.i("Invalid_Cam_Entry",ex.getMessage());
    }


}


    private void chooseIdBack() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_BACK);
    }

    private void chooseIdBack_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_BACK);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }


    }

    
    private void chooseIdFront() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_FRONT);
    }


    private void chooseIdFront_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_FRONT);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }


    }

    private void chooseIdLicense_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_FILE);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }


    }
    
    private void chooseLicenseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_FILE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 ) {

            showMessage("No image is selected, try again");
            return;
        }


        //showMessage(String.valueOf(requestCode));
        if (networkConnection.isNetworkConnected(this)) {
            Random random=new Random();
            String rand= String.valueOf(random.nextInt());

        if(requestCode==1) {

            passportUri = data.getData();
            Log.i("PassportUri1",passportUri.toString());
            try {
                if(passportUri!=null){
                    String name = mFirstNameEditxt.getText().toString()+rand;
                    if (name.equals("")) {
                        showMessage("Enter your first name first");

                    } else {

                        user_img.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), passportUri));

                    String requestId = MediaManager.get().upload(Uri.parse(passportUri.toString()))
                            .option("public_id", "user_registration/profile_photos/user_passport"+name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }
                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes/totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }
                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Passport Uploaded Successfully");
                                    Log.i("PassportRequestId ",requestId);
                                    Log.i("PassportUrl ", String.valueOf(resultData.get("url")));
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);
                                    profile_photo_url=String.valueOf(resultData.get("url"));


                                }
                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: "+error.toString());
                                    Log.i("Error: ",error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }
                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }})
                            .dispatch();

                }
                }

            } catch (IOException e) {
                e.printStackTrace();
                showMessage("Please Check your Image");

            }
        }else if(requestCode==11) {
            passportUri=Uri.parse(cameraFilePath);
            Log.i("PasssUri1",cameraFilePath);
            Log.i("PasssUri2",passportUri.toString());
            if(passportUri!=null){

                String name = mFirstNameEditxt.getText().toString()+rand;
                if (name.equals("")) {
                    showMessage("Enter your first name first");
                    return;
                } else {
                    Glide.with(this).load(cameraFilePath).into(user_img);
                   // user_img.setImageURI(passportUri);

                    String requestId = MediaManager.get().upload(Uri.parse(cameraFilePath))
                            .option("public_id", "user_registration/profile_photos/user_passport"+name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }
                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes/totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }
                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Passport Uploaded Successfully");
                                    Log.i("PassportRequestId ",requestId);
                                    Log.i("PassportUrl ", String.valueOf(resultData.get("url")));
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);
                                    profile_photo_url=String.valueOf(resultData.get("url"));


                                }
                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: "+error.toString());
                                    Log.i("Error: ",error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }
                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }})
                            .dispatch();

                }
            }

        }else if(requestCode==2){
            uploadFront_uri = data.getData();


            if(uploadFront_uri!=null) {
                String name = mFirstNameEditxt.getText().toString()+rand;
                if (name.equals("")) {
                    showMessage("Enter your firstname first");
                } else {

                    String frontId = MediaManager.get().upload(Uri.parse(uploadFront_uri.toString()))
                            .option("public_id", "user_registration/other_files/user_id_front" +name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes / totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Id's Front View has been Uploaded Successfully");
                                    Log.i("PassportUrl ", requestId);
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);

                                    id_front_url=String.valueOf(resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: " + error.toString());

                                    Log.i("Error: ", error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }
                            })
                            .dispatch();

                }
            }

        }else if(requestCode==22){
            uploadFront_uri=Uri.parse(cameraFilePath);
            if(uploadFront_uri!=null) {
                String name = mFirstNameEditxt.getText().toString()+rand;
                if (name.equals("")) {
                    showMessage("Enter your firstname first");
                } else {

                    String frontId = MediaManager.get().upload(uploadFront_uri)
                            .option("public_id", "user_registration/other_files/user_id_front" +name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes / totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Id's Front View has been Uploaded Successfully");
                                    Log.i("PassportUrl ", requestId);
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);

                                    id_front_url=String.valueOf(resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: " + error.toString());

                                    Log.i("Error: ", error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }
                            })
                            .dispatch();

                }
            }

        }else if(requestCode==3){
            uploadBack_uri = data.getData();
            String name = mFirstNameEditxt.getText().toString()+rand;
            if (name.equals("")) {
                showMessage("Enter your firstname first");
            } else {

                if (uploadBack_uri != null) {

                    String frontId = MediaManager.get().upload(Uri.parse(uploadBack_uri.toString()))
                            .option("public_id", "user_registration/other_files/user_id_back" + name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes / totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Id's Back View has been Uploaded Successfully");
                                    Log.i("PassportUrl ", requestId);
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);
                                    id_back_url=String.valueOf(resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: " + error.toString());

                                    Log.i("Error: ", error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }
                            })
                            .dispatch();

                }
            }
        }else if(requestCode==33){
            uploadBack_uri = Uri.parse(cameraFilePath);
            String name = mFirstNameEditxt.getText().toString()+rand;
            if (name.equals("")) {
                showMessage("Enter your firstname first");
            } else {

                if (uploadBack_uri != null) {

                    String frontId = MediaManager.get().upload(uploadBack_uri)
                            .option("public_id", "user_registration/other_files/user_id_back" + name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes / totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Id's Back View has been Uploaded Successfully");
                                    Log.i("PassportUrl ", requestId);
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);
                                    id_back_url=String.valueOf(resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: " + error.toString());

                                    Log.i("Error: ", error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }
                            })
                            .dispatch();

                }
            }
        }else if(requestCode==4){
            uploadFile_uri = data.getData();
            String name = mFirstNameEditxt.getText().toString()+rand;
            if (name.equals("")) {
                showMessage("Enter your firstname first");
            } else {
                if (uploadFile_uri != null) {

                    String licenseFile = MediaManager.get().upload(Uri.parse(uploadFile_uri.toString()))
                            .option("public_id", "user_registration/other_files/licensefile" + name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes / totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Your Licenses File has been Uploaded Successfully");
                                    Log.i("PassportUrl ", requestId);
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);
                                    license_photo_url=String.valueOf(resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: " + error.toString());

                                    Log.i("Error: ", error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }
                            })
                            .dispatch();

                }
            }
        }else if(requestCode==44){
            uploadFile_uri = Uri.parse(cameraFilePath);
            String name = mFirstNameEditxt.getText().toString()+rand;
            if (name.equals("")) {
                showMessage("Enter your firstname first");
            } else {
                if (uploadFile_uri != null) {

                    String licenseFile = MediaManager.get().upload(uploadFile_uri)
                            .option("public_id", "user_registration/other_files/licensefile" + name)
                            .unsigned("xbiscrhh").callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // your code here
                                    registrationBtn.setVisibility(View.GONE);
                                    progressbar.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // example code starts here
                                    Double progress = (double) bytes / totalBytes;
                                    // post progress to app UI (e.g. progress bar, notification)
                                    // example code ends here
                                    progressbar.setVisibility(View.VISIBLE);
                                    if(!networkConnection.isNetworkConnected(getApplicationContext())){
                                        progressbar.setVisibility(View.GONE);
                                        registrationBtn.setVisibility(View.VISIBLE);
                                        showMessage("Internet Connection Failed");
                                    }
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // your code here

                                    showMessage("Your Licenses File has been Uploaded Successfully");
                                    Log.i("PassportUrl ", requestId);
                                    progressbar.setVisibility(View.GONE);
                                    registrationBtn.setVisibility(View.VISIBLE);
                                    license_photo_url=String.valueOf(resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // your code here
                                    showMessage("Error: " + error.toString());

                                    Log.i("Error: ", error.toString());

                                    registrationBtn.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // your code here
                                }
                            })
                            .dispatch();

                }
            }
        }
            return;
        }
        showMessage("No Internet connection discovered!");

    }


    private void sendData(){
        registrationBtn.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);

        User dataPart=new User(mFirstNameEditxt.getText().toString(),mLastNameEditxt.getText().toString(),
                mEmailEditxt.getText().toString(),mPasswordEditxt.getText().toString(),mPhoneNumEditxt.getText().toString(),
                genderString,mAddressEditxt.getText().toString(),mBirthDayEditxt.getText().toString(),bvnString,
                id_front_url,id_front_url

        );
        RegisterObj regPostData=new RegisterObj(dataPart);

        sentNetworkRequest(regPostData);

    }

    private  void sentNetworkRequest(RegisterObj regPostData){
        //To create retrofit instance

        HashMap hashMap= new HashMap();
        hashMap.put("Content-Type","application/json;charset=UTF-8");

        //get client and call object for request
        ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

        Call<AgentDataHead> call=client.register(regPostData,hashMap);

        call.enqueue(new Callback<AgentDataHead>() {
            @Override
            public void onResponse(Call<AgentDataHead> call, Response<AgentDataHead> response) {
                try {
                    if (!response.isSuccessful()) {

                        try{
                            APIError apiError= ErrorUtils.parseError(response);

                            showMessage("Invalid Entry: "+apiError.getErrors());
                            Log.i("Invalid EntryK",apiError.getErrors().toString());
                            Log.i("Invalid Entry",response.errorBody().toString());

                        }catch (Exception e){
                            Log.i("InvalidEntry",e.getMessage());
                            showMessage("Failed to Register"+e.getMessage());
                            registrationBtn.setVisibility(View.VISIBLE);
                            progressbar.setVisibility(View.GONE);

                        }
                        registrationBtn.setVisibility(View.VISIBLE);
                        progressbar.setVisibility(View.GONE);
                        return;
                    }
                    String agent_email = response.body().getAgent().getEmail();

                    Log.i("response", agent_email);

                    showMessage("Agent ID: " + agent_email);
                    Log.i("Agent ID", response.body().getAgent().toString());

                    registrationBtn.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.GONE);
                    if (agent_email != null) {
                        Intent intent = new Intent(SignUp.this, SignIn.class);
                        intent.putExtra(Constant.AGENT_EMAIL, agent_email);
                        startActivity(intent);
                        SignUp.this.finish();
                    } else {
                        showMessage("Error: " + response.body());
                    }
                }catch (Exception e){
                    showMessage("Registration Error: " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<AgentDataHead> call, Throwable t) {
                showMessage("Registration Failed "+t.getMessage());
                Log.i("GEtError",t.getMessage());
            }
        });



    }

    private void showBirthdayPicker() {
        //Get current date
        Calendar calendar = Calendar.getInstance();

        //Create datePickerDialog with initial date which is current and decide what happens when a date is selected.
        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //When a date is selected, it comes here.
                //Change birthdayEdittext's text and dismiss dialog.
                if(year>calendar.get(Calendar.YEAR)){

                    showMessage("Invalid Born Date");
                    Log.i("Calendar",year+" "+calendar.get(Calendar.YEAR));
                    return;
                }
                int monthofYear=monthOfYear+1;
                birthdayString = dayOfMonth + "-" + monthofYear + "-" + year;
                mBirthDayEditxt.setText(birthdayString);
                datePickerDialog1.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void showLicensExpDatePicker() {
        //Get current date
        Calendar calendar = Calendar.getInstance();

        //Create datePickerDialog with initial date which is current and decide what happens when a date is selected.
        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //When a date is selected, it comes here.
               if(year<calendar.get(Calendar.YEAR)){
                   showMessage("Invalid Expire Date");
                   return;
               }else if(year==calendar.get(Calendar.YEAR)){
                   if(calendar.get(Calendar.MONTH)>monthOfYear+1){
                       showMessage("Invalid Expire Date");
                       return;
                   }
               }
                int monthofYear=monthOfYear+1;
                licenseDateString = dayOfMonth + "-" + monthofYear + "-" + year;
                mExpDateLicenseEditxt.setText(licenseDateString);
                datePickerDialog2.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    private void showMessage(String s) {
        Snackbar.make(layout_signUp, s, Snackbar.LENGTH_SHORT).show();
    }



}
