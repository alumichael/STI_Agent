package com.example.sti_agent.operation_fragment.MotorInsurance;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.sti_agent.BuildConfig;
import com.example.sti_agent.NetworkConnection;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MotorInsureFragment1 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.step_view)
    StepView stepView;

    //EditText
    @BindView(R.id.firstname_editxt)
    EditText firstname_editxt;
    @BindView(R.id.lastname_editxt)
    EditText lastname_editxt;
    @BindView(R.id.residents_addr_editxt)
    EditText residents_addr_editxt;
    @BindView(R.id.next_kin_editxt)
    EditText next_kin_editxt;
    @BindView(R.id.phone_no_editxt)
    EditText phone_no_editxt;
    @BindView(R.id.email_editxt)
    EditText email_editxt;
    @BindView(R.id.mail_addr_editxt)
    EditText mail_addr_editxt;
    @BindView(R.id.companyname_editxt)
    EditText companyname_editxt;
    @BindView(R.id.tin_num_editxt)
    EditText tin_num_editxt;
    @BindView(R.id.office_addr_editxt)
    EditText office_addr_editxt;
    @BindView(R.id.contact_person_editxt)
    EditText contact_person_editxt;


    @BindView(R.id.qb_form_layout1)
    FrameLayout qb_form_layout1;


    //TextInput
    @BindView(R.id.inputLayoutFirstName)
    TextInputLayout inputLayoutFirstName;
    @BindView(R.id.inputLayoutLastName)
    TextInputLayout inputLayoutLastName;
    @BindView(R.id.inputLayoutResAddr)
    TextInputLayout inputLayoutResAddr;
    @BindView(R.id.inputLayoutNextKin)
    TextInputLayout inputLayoutNextKin;
    @BindView(R.id.inputLayoutPhone)
    TextInputLayout inputLayoutPhone;
    @BindView(R.id.inputLayoutEmail)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.inputLayoutMailingAddr)
    TextInputLayout inputLayoutMailingAddr;
    @BindView(R.id.inputLayoutCompanyName)
    TextInputLayout inputLayoutCompanyName;
    @BindView(R.id.inputLayoutTinNum)
    TextInputLayout inputLayoutTinNum;
    @BindView(R.id.inputLayoutOfficeAddr)
    TextInputLayout inputLayoutOfficeAddr;
    @BindView(R.id.inputLayoutContactPerson)
    TextInputLayout inputLayoutContactPerson;


    //Spinners
    @BindView(R.id.type_spinner)
    Spinner typeSpinner;
    @BindView(R.id.prefix_spinner)
    Spinner prefixSpinner;
    @BindView(R.id.gender_spinner)
    Spinner genderSpinner;

    //Buttons
    @BindView(R.id.upload_img_btn)
    Button upload_img_btn;
    @BindView(R.id.next_btn0)
    Button nextBtn;


    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;

    String typeString,genderString,prifixString,cameraFilePath;
    private int currentStep = 0;

    int PICK_IMAGE_PASSPORT = 1;
    int CAM_IMAGE_PASSPORT = 2;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri personal_info_img_uri;
    String personal_img_url;


    public MotorInsureFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static MotorInsureFragment1 newInstance(String param1, String param2) {
        MotorInsureFragment1 fragment = new MotorInsureFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_motor_insured1, container, false);
        ButterKnife.bind(this,view);

        stepView.go(currentStep, true);



        init();

        typeSpinner();
        prefixSpinner();
        genderSpinner();
        setViewActions();

        return  view;
    }

    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        companyname_editxt.setText(userPreferences.getMotorICompanyName());

        tin_num_editxt.setText(userPreferences.getMotorITinNumber());

        office_addr_editxt.setText(userPreferences.getMotorIOff_addr());

        contact_person_editxt.setText(userPreferences.getMotorIContPerson());

        firstname_editxt.setText(userPreferences.getMotorIFirstName());

        lastname_editxt.setText(userPreferences.getMotorILastName());

        residents_addr_editxt.setText(userPreferences.getMotorIResAdrr());
        next_kin_editxt.setText(userPreferences.getMotorINextKin());
        phone_no_editxt.setText(userPreferences.getMotorIPhoneNum());
        email_editxt.setText(userPreferences.getMotorIEmail());
        mail_addr_editxt.setText(userPreferences.getMotorIMailingAddr());

    }




    private void typeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        typeSpinner.setAdapter(staticAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                if(stringText.equals("Individual")){

                    typeSpinner.setVisibility(View.VISIBLE);
                    typeSpinner.setClickable(true);


                    //De-Visualizing the corporate form
                    inputLayoutCompanyName.setVisibility(View.GONE);
                    companyname_editxt.setClickable(false);
                    inputLayoutTinNum.setVisibility(View.GONE);
                    tin_num_editxt.setClickable(false);
                    inputLayoutOfficeAddr.setVisibility(View.GONE);
                    office_addr_editxt.setClickable(false);
                    inputLayoutContactPerson.setVisibility(View.GONE);
                    contact_person_editxt.setClickable(false);

                    //Visualizing the individual form

                    prefixSpinner.setVisibility(View.VISIBLE);
                    prefixSpinner.setClickable(true);
                    inputLayoutFirstName.setVisibility(View.VISIBLE);
                    firstname_editxt.setClickable(true);
                    inputLayoutLastName.setVisibility(View.VISIBLE);
                    lastname_editxt.setClickable(true);
                    genderSpinner.setVisibility(View.VISIBLE);
                    genderSpinner.setClickable(true);
                    inputLayoutResAddr.setVisibility(View.VISIBLE);
                    residents_addr_editxt.setClickable(true);
                    inputLayoutNextKin.setVisibility(View.VISIBLE);
                    next_kin_editxt.setClickable(true);


                }else if(stringText.equals("Corporate")){

                    //De-Visualizing the individual form
                    prefixSpinner.setVisibility(View.GONE);
                    prefixSpinner.setClickable(false);
                    inputLayoutFirstName.setVisibility(View.GONE);
                    firstname_editxt.setClickable(false);
                    inputLayoutLastName.setVisibility(View.GONE);
                    lastname_editxt.setClickable(false);
                    genderSpinner.setVisibility(View.GONE);
                    genderSpinner.setClickable(false);
                    inputLayoutResAddr.setVisibility(View.GONE);
                    residents_addr_editxt.setClickable(false);
                    inputLayoutNextKin.setVisibility(View.GONE);
                    next_kin_editxt.setClickable(false);


                    //Visualizing the individual form
                    typeSpinner.setVisibility(View.VISIBLE);
                    typeSpinner.setClickable(true);
                    inputLayoutCompanyName.setVisibility(View.VISIBLE);
                    companyname_editxt.setClickable(true);
                    inputLayoutTinNum.setVisibility(View.VISIBLE);
                    tin_num_editxt.setClickable(true);
                    inputLayoutOfficeAddr.setVisibility(View.VISIBLE);
                    office_addr_editxt.setClickable(true);
                    inputLayoutContactPerson.setVisibility(View.VISIBLE);
                    contact_person_editxt.setClickable(true);

                }else {

                    //De-Visualizing the individual form
                    prefixSpinner.setVisibility(View.GONE);
                    inputLayoutFirstName.setVisibility(View.GONE);
                    inputLayoutLastName.setVisibility(View.GONE);
                    genderSpinner.setVisibility(View.GONE);
                    inputLayoutResAddr.setVisibility(View.GONE);
                    inputLayoutNextKin.setVisibility(View.GONE);
                    inputLayoutCompanyName.setVisibility(View.GONE);
                    inputLayoutTinNum.setVisibility(View.GONE);
                    inputLayoutOfficeAddr.setVisibility(View.GONE);
                    inputLayoutContactPerson.setVisibility(View.GONE);




                    //Visualizing the individual form
                    typeSpinner.setVisibility(View.VISIBLE);
                    inputLayoutPhone.setVisibility(View.VISIBLE);
                    inputLayoutEmail.setVisibility(View.VISIBLE);
                    inputLayoutMailingAddr.setVisibility(View.VISIBLE);
                    upload_img_btn.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                typeSpinner.getItemAtPosition(0);
                //De-Visualizing the individual form
                prefixSpinner.setVisibility(View.GONE);
                inputLayoutFirstName.setVisibility(View.GONE);
                inputLayoutLastName.setVisibility(View.GONE);
                genderSpinner.setVisibility(View.GONE);
                inputLayoutResAddr.setVisibility(View.GONE);
                inputLayoutNextKin.setVisibility(View.GONE);
                inputLayoutCompanyName.setVisibility(View.GONE);
                inputLayoutTinNum.setVisibility(View.GONE);
                inputLayoutOfficeAddr.setVisibility(View.GONE);
                inputLayoutContactPerson.setVisibility(View.GONE);




                //Visualizing the individual form
                typeSpinner.setVisibility(View.VISIBLE);
                inputLayoutPhone.setVisibility(View.VISIBLE);
                inputLayoutEmail.setVisibility(View.VISIBLE);
                inputLayoutMailingAddr.setVisibility(View.VISIBLE);
                upload_img_btn.setVisibility(View.VISIBLE);

            }
        });

    }

    private void prefixSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.prefix_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        prefixSpinner.setAdapter(staticAdapter);

        prefixSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                prefixSpinner.getItemAtPosition(0);

            }
        });

    }
    private void genderSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        genderSpinner.setAdapter(staticAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderSpinner.getItemAtPosition(0);


            }
        });

    }

    //seting onclicks listeners
    private void setViewActions() {
        upload_img_btn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_img_btn:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose Mode of Entry");
// add a list
                String[] entry = {"Camera", "Gallery"};
                builder.setItems(entry, (dialog, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdImage_camera();
                            dialog.dismiss();
                            break;

                        case 1: // export

                            chooseImageFile();
                            dialog.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                upload_img_btn.setBackgroundColor(getResources().getColor(R.color.colorAccentEnds));

                break;
            case R.id.next_btn0:
//                validate user input
                validateUserInputs();

                break;
        }
    }


    private void chooseImageFile() {
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



    private void chooseIdImage_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_PASSPORT);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 ) {
            showMessage("No image is selected, try again");
            return;
        }


        showMessage("Uploading...");
        if (networkConnection.isNetworkConnected(getContext())) {

            if (requestCode == 1) {
                personal_info_img_uri = data.getData();

                try {
                    if (personal_info_img_uri != null) {
                        String name = email_editxt.getText().toString();
                        if (name.equals("")) {
                            showMessage("Enter your email address first");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(personal_info_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            nextBtn.setVisibility(View.GONE);
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

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            nextBtn.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            nextBtn.setVisibility(View.VISIBLE);
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


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if(requestCode==2){
                personal_info_img_uri = Uri.parse(cameraFilePath);

                try {
                    if (personal_info_img_uri != null) {
                        String name = email_editxt.getText().toString();
                        if (name.equals("")) {
                            showMessage("Enter your email address first");

                        } else {

                            String imageId = MediaManager.get().upload(personal_info_img_uri)
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            nextBtn.setVisibility(View.GONE);
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

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            nextBtn.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));

                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            nextBtn.setVisibility(View.VISIBLE);
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


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }
            }
            return;
        }
        showMessage("No Internet connection discovered!");
    }

    private void validateUserInputs() {


            boolean isValid = true;

            if (firstname_editxt.getText().toString().isEmpty()&&inputLayoutFirstName.isClickable()) {
                inputLayoutFirstName.setError("Your FirstName is required!");
                isValid = false;
            } else if (lastname_editxt.getText().toString().isEmpty()&&inputLayoutLastName.isClickable()) {
                inputLayoutLastName.setError("Your LastName is required!");
                isValid = false;
            } else if (companyname_editxt.getText().toString().isEmpty()&&inputLayoutCompanyName.isClickable()) {
                inputLayoutCompanyName.setError("Your Company Name is required!");
                isValid = false;
            } else if (tin_num_editxt.getText().toString().isEmpty()&&inputLayoutTinNum.isClickable()) {
                inputLayoutTinNum.setError("Your TIN Number is required!");
                isValid = false;
            } else if (office_addr_editxt.getText().toString().isEmpty()&&inputLayoutOfficeAddr.isClickable()) {
                inputLayoutOfficeAddr.setError("Office Address is required!");
                isValid = false;
            } else {
                inputLayoutFirstName.setErrorEnabled(false);
                inputLayoutLastName.setErrorEnabled(false);
                inputLayoutCompanyName.setErrorEnabled(false);
                inputLayoutTinNum.setErrorEnabled(false);
                inputLayoutOfficeAddr.setErrorEnabled(false);
            }

            if (email_editxt.getText().toString().isEmpty()&&inputLayoutEmail.isClickable()) {
                inputLayoutEmail.setError("Email is required!");
                isValid = false;
            } else if (!isValidEmailAddress(email_editxt.getText().toString())) {
                inputLayoutEmail.setError("Valid Email is required!");
                isValid = false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }

            if (mail_addr_editxt.getText().toString().isEmpty()&& inputLayoutMailingAddr.isClickable()) {
                inputLayoutMailingAddr.setError("Mailing Address is required!");
                isValid = false;
            } else if (!isValidEmailAddress(mail_addr_editxt.getText().toString())&&inputLayoutMailingAddr.isClickable()) {
                inputLayoutMailingAddr.setError("Valid Mailing Address is required!");
                isValid = false;
            } else {
                inputLayoutMailingAddr.setErrorEnabled(false);
            }


            if (phone_no_editxt.getText().toString().isEmpty()&&inputLayoutPhone.isClickable()) {
                inputLayoutPhone.setError("Phone number is required");
                isValid = false;
            } else if (phone_no_editxt.getText().toString().trim().length() < 11 && inputLayoutPhone.isClickable()) {
                inputLayoutPhone.setError("Your Phone number must be 11 in length");
                isValid = false;
            } else {
                inputLayoutPhone.setErrorEnabled(false);
            }

            if (contact_person_editxt.getText().toString().isEmpty()&&inputLayoutContactPerson.isClickable()) {
                inputLayoutContactPerson.setError("Contact Person is required");
                isValid = false;
            } else {
                inputLayoutContactPerson.setErrorEnabled(false);
            }
            if (residents_addr_editxt.getText().toString().isEmpty()&&inputLayoutResAddr.isClickable()) {
                inputLayoutResAddr.setError("Resident Address is required");
                isValid = false;
            } else {
                inputLayoutResAddr.setErrorEnabled(false);
            }

            if (next_kin_editxt.getText().toString().isEmpty()&&inputLayoutNextKin.isClickable()) {
                inputLayoutNextKin.setError("Next of Kin Name is required");
                isValid = false;
            } else {
                inputLayoutNextKin.setErrorEnabled(false);
            }

            if (personal_img_url==null) {
                showMessage("Please upload an image: passport,company license..etc");
                isValid = false;
            }

            //Tyepe Spinner
            typeString = typeSpinner.getSelectedItem().toString();
            if (typeString.equals("Select Type")&&typeSpinner.isClickable()) {

                showMessage("Select Product Type");
                isValid = false;
            }
            //Prefix Spinner
            prifixString = prefixSpinner.getSelectedItem().toString();
            if (prifixString.equals("Select Prefix")&&prefixSpinner.isClickable()) {
                showMessage("Select your Prefix e.g Mr.");
                isValid = false;
            }

            genderString = genderSpinner.getSelectedItem().toString();
            if (genderString.equals("Gender")&&genderSpinner.isClickable()) {
                showMessage("Don't forget to Select Gender");
                isValid = false;
            }

            if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
                initFragment();
            }




    }

    private void initFragment() {
        nextBtn.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setMotorPtype(typeString);
            userPreferences.setMotorIPrefix(prifixString);
            userPreferences.setMotorICompanyName(companyname_editxt.getText().toString());
            userPreferences.setMotorITinNumber(tin_num_editxt.getText().toString());
            userPreferences.setMotorIOff_addr(office_addr_editxt.getText().toString());
            userPreferences.setMotorIContPerson(contact_person_editxt.getText().toString());
            userPreferences.setMotorIFirstName(firstname_editxt.getText().toString());
            userPreferences.setMotorILastName(lastname_editxt.getText().toString());
            userPreferences.setMotorIGender(genderString);
            userPreferences.setMotorIResAdrr(residents_addr_editxt.getText().toString());
            userPreferences.setMotorINextKin(next_kin_editxt.getText().toString());
            userPreferences.setMotorIPhoneNum(phone_no_editxt.getText().toString());
            userPreferences.setMotorIEmail(email_editxt.getText().toString());
            userPreferences.setMotorIMailingAddr(mail_addr_editxt.getText().toString());
            userPreferences.setMotorIPersonal_image(personal_img_url);

            if (currentStep < stepView.getStepCount() - 1) {
                currentStep++;

                Fragment motorInsureFragment2 = new MotorInsureFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_motor_form_container, motorInsureFragment2);
                ft.commit();
                stepView.go(currentStep, true);
            } else {
                stepView.done(true);
            }



        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            progressbar.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(qb_form_layout1, s, Snackbar.LENGTH_LONG).show();
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        if (null != email) {
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                result = false;
            }
        }

        return result;
    }




}
