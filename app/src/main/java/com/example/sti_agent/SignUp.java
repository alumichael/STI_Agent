package com.example.sti_agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment1.isValidEmailAddress;

public class SignUp extends AppCompatActivity implements View.OnClickListener{


    /** ButterKnife Code **/
    @BindView(R.id.user_type_spinner)
    Spinner mUserTypeSpinner;
    @BindView(R.id.inputLayoutName)
    TextInputLayout mInputLayoutName;
    @BindView(R.id.name_editxt)
    EditText mNameEditxt;
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

    int PICK_IMAGE_PASSPORT = 1;
    int PICK_IMAGE_FRONT = 2;
    int PICK_IMAGE_BACK = 3;
    int PICK_IMAGE_FILE = 4;
    private Uri passportUri,uploadBack_uri,uploadFront_uri,uploadFile_uri;
    String userTypeString,genderString;
    NetworkConnection networkConnection=new NetworkConnection();

    public String profile_photo_url,id_front_url,id_back_url,license_photo_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        userType();
        genderType();


        registrationBtn.setOnClickListener(this);
        login.setOnClickListener(this);
        layout_upload_pass.setOnClickListener(this);
        upload_id_back.setOnClickListener(this);
        upload_id_front.setOnClickListener(this);
        mUploadFile.setOnClickListener(this);
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


                    //Visualizing the agent form
                    mInputLayoutName.setVisibility(View.VISIBLE);
                    mInputLayoutName.setClickable(true);
                    mUserTypeSpinner.setVisibility(View.VISIBLE);
                    mUserTypeSpinner.setClickable(true);

                    layout_upload_pass.setVisibility(View.VISIBLE);
                    layout_upload_pass.setClickable(true);


                    mPhoneNumEditxt.setVisibility(View.VISIBLE);
                    mPhoneNumEditxt.setClickable(true);
                    mInputLayoutEmail.setVisibility(View.VISIBLE);
                    mInputLayoutEmail.setClickable(true);
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


                }else if(userString.equals("Broker")){

                    mUserTypeSpinner.setVisibility(View.VISIBLE);
                    mUserTypeSpinner.setClickable(true);

                    layout_upload_pass.setVisibility(View.GONE);
                    layout_upload_pass.setClickable(false);

                    //De-Visualizing the agent form
                    mInputLayoutName.setVisibility(View.GONE);
                    mInputLayoutName.setClickable(false);


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
                mInputLayoutName.setVisibility(View.VISIBLE);
                mInputLayoutName.setClickable(true);
                mUserTypeSpinner.setVisibility(View.VISIBLE);
                mUserTypeSpinner.setClickable(true);

                layout_upload_pass.setVisibility(View.VISIBLE);
                layout_upload_pass.setClickable(true);


                mPhoneNumEditxt.setVisibility(View.VISIBLE);
                mPhoneNumEditxt.setClickable(true);
                mInputLayoutEmail.setVisibility(View.VISIBLE);
                mInputLayoutEmail.setClickable(true);
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
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.complete_reg_btn:
                ValidateForm();
                break;
            case R.id.layout_upload_pass:
                choosePassport();
                break;

            case R.id.upload_id_back:
                chooseIdBack();
                break;
            case R.id.upload_id_front:
                chooseIdFront();
                break;

            case R.id.upload_file:
                chooseLicenseFile();
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
            if (mNameEditxt.getText().toString().isEmpty()&&mInputLayoutName.isClickable()) {
                mInputLayoutName.setError("Full Name is required");
                isValid = false;
            } else {
                mInputLayoutName.setErrorEnabled(false);
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
                mInputLayoutBVN.setError("BVN is required");
                isValid = false;
            } else {
                mInputLayoutBVN.setErrorEnabled(false);
            }

            userTypeString = mUserTypeSpinner.getSelectedItem().toString();
            if (userTypeString.equals("User Type")&&mUserTypeSpinner.isClickable()) {
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

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }

            //


            return;
        }
        showMessage("No Internet connection discovered!");
    }



    private void choosePassport() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_PASSPORT);
    }

    private void chooseIdBack() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_BACK);
    }
    private void chooseIdFront() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_FRONT);
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
        if (resultCode == 0 || data == null || data.getData() == null) {
            showMessage("No image is selected, try again");
            return;
        }


        showMessage(String.valueOf(requestCode));
        if (networkConnection.isNetworkConnected(this)) {

        if(requestCode==1) {
            passportUri = data.getData();
            try {
                if(passportUri!=null){
                    String name = mNameEditxt.getText().toString();
                    if (name.equals("")) {
                        showMessage("Enter your name first");

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
        }else if(requestCode==2){
            uploadFront_uri = data.getData();


            if(uploadFront_uri!=null) {
                String name = mNameEditxt.getText().toString();
                if (name.equals("")) {
                    showMessage("Enter your name first");
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
            String name = mNameEditxt.getText().toString();
            if (name.equals("")) {
                showMessage("Enter your name first");
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
            String name = mNameEditxt.getText().toString();
            if (name.equals("")) {
                showMessage("Enter your name first");
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



    private void showMessage(String s) {
        Snackbar.make(layout_signUp, s, Snackbar.LENGTH_SHORT).show();
    }



}
