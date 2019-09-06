package com.example.sti_agent.operation_fragment.Swiss;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SwissFragment2 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.qb_form_layout2)
    FrameLayout mQbFormLayout2;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.inputLayoutFirstName_s2)
    TextInputLayout mInputLayoutFirstNameS2;
    @BindView(R.id.firstname_editxt)
    EditText mFirstnameEditxt;
    @BindView(R.id.inputLayoutLastName_s2)
    TextInputLayout mInputLayoutLastNameS2;
    @BindView(R.id.lastname_editxt_s2)
    EditText mLastnameEditxtS2;
    @BindView(R.id.inputLayoutDateofBirth_s2)
    TextInputLayout mInputLayoutDateofBirthS2;
    @BindView(R.id.dob_editxt_s2)
    EditText mDobEditxtS2;
    @BindView(R.id.gender_spinner_s2)
    Spinner mGenderSpinnerS2;
    @BindView(R.id.inputLayoutPhone_s2)
    TextInputLayout mInputLayoutPhoneS2;
    @BindView(R.id.phone_no_editxt_s2)
    EditText mPhoneNoEditxtS2;
    @BindView(R.id.inputLayoutEmail_s2)
    TextInputLayout mInputLayoutEmailS2;
    @BindView(R.id.email_editxt_s2)
    EditText mEmailEditxtS2;
    @BindView(R.id.inputLayoutDisable_s2)
    TextInputLayout mInputLayoutDisableS2;
    @BindView(R.id.disable_editxt_s2)
    EditText mDisableEditxtS2;
    @BindView(R.id.benefit_spinner_s2)
    Spinner mBenefitSpinnerS2;
    @BindView(R.id.marital_spinner_s2)
    Spinner mMaritalSpinnerS2;
    @BindView(R.id.upload_passport_btn_s2)
    Button mUploadPassportBtnS2;
    @BindView(R.id.btn_layout2_s2)
    LinearLayout mBtnLayout2S2;
    @BindView(R.id.v_back_btn2_s2)
    Button mVBackBtn2S2;
    @BindView(R.id.v_next_btn2_s2)
    Button mVNextBtn2S2;
    @BindView(R.id.progressbar2_s2)
    AVLoadingIndicatorView mProgressbar2S2;
  

    private int currentStep = 1;

    String cameraFilePath;
    int PICK_IMAGE_PASSPORT = 1;
    int CAM_IMAGE_PASSPORT = 2;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri addaddinsure_person_img_uri;
    String addpersonal_img_url,DobString;

    DatePickerDialog datePickerDialog1;

    String maritalString,genderString,benefitString;

    public SwissFragment2() {
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
    public static SwissFragment2 newInstance(String param1, String param2) {
        SwissFragment2 fragment = new SwissFragment2();
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
        View view=inflater.inflate(R.layout.fragment_swiss2, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step
        

        mStepView.go(currentStep, true);

        init();

        gendertypeSpinner();

        maritaltypeSpinner();

        benefittypeSpinner();
        setViewActions();
        showDatePicker();



        return  view;
    }


    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mFirstnameEditxt.setText(userPreferences.getSwissIAddFirstName());

        mLastnameEditxtS2.setText(userPreferences.getSwissIAddLastName());

        mDobEditxtS2.setText(userPreferences.getSwissIAddDOB());

        mPhoneNoEditxtS2.setText(userPreferences.getSwissIAddPhoneNum());

        mEmailEditxtS2.setText(userPreferences.getSwissIAddEmail());

        mDisableEditxtS2.setText(userPreferences.getSwissIAddDisability());

    }


    private void gendertypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mGenderSpinnerS2.setAdapter(staticAdapter);

        mGenderSpinnerS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mGenderSpinnerS2.getItemAtPosition(0);

            }
        });

    }

    private void maritaltypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.marital_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mMaritalSpinnerS2.setAdapter(staticAdapter);

        mMaritalSpinnerS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String martalTypeString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mMaritalSpinnerS2.getItemAtPosition(0);
            }
        });

    }


    private void benefittypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.benefit_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mBenefitSpinnerS2.setAdapter(staticAdapter);

        mBenefitSpinnerS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String benefitTypeString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBenefitSpinnerS2.getItemAtPosition(0);
            }
        });

    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn2S2.setOnClickListener(this);
        mVBackBtn2S2.setOnClickListener(this);
        mUploadPassportBtnS2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2_s2:
//                validate user input
                validateUserInputs();
                break;

            case R.id.dob_editxt_s2:
                datePickerDialog1.show();
                break;

            case R.id.v_back_btn2_s2:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment quoteBuyFragment1 = new SwissFragment1();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_swiss_form_container, quoteBuyFragment1);
                ft.commit();

                break;
                
            case R.id.upload_passport_btn_s2:
                // setup the alert builder
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
                mUploadPassportBtnS2.setBackgroundColor(getResources().getColor(R.color.colorAccentEnds));

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
        if (resultCode == 0 || data == null || data.getData() == null) {
            showMessage("No image is selected, try again");
            return;
        }


        showMessage("Uploading...");
        if (networkConnection.isNetworkConnected(getContext())) {
            Random random=new Random();
            String rand= String.valueOf(random.nextInt());
            if (requestCode == 1) {
                addaddinsure_person_img_uri = data.getData();

                try {
                    if (addaddinsure_person_img_uri != null) {
                        String name = mFirstnameEditxt.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your the first name first");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(addaddinsure_person_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mVNextBtn2S2.setVisibility(View.GONE);
                                            mProgressbar2S2.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar2S2.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar2S2.setVisibility(View.GONE);
                                            mVNextBtn2S2.setVisibility(View.VISIBLE);
                                            addpersonal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mVNextBtn2S2.setVisibility(View.VISIBLE);
                                            mProgressbar2S2.setVisibility(View.GONE);
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

            }else  if (requestCode == 2) {
                addaddinsure_person_img_uri = Uri.parse(cameraFilePath);

                try {
                    if (addaddinsure_person_img_uri != null) {
                        String name = mFirstnameEditxt.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your the first name first");

                        } else {

                            String imageId = MediaManager.get().upload(addaddinsure_person_img_uri)
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mVNextBtn2S2.setVisibility(View.GONE);
                                            mProgressbar2S2.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar2S2.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar2S2.setVisibility(View.GONE);
                                            mVNextBtn2S2.setVisibility(View.VISIBLE);
                                            addpersonal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mVNextBtn2S2.setVisibility(View.VISIBLE);
                                            mProgressbar2S2.setVisibility(View.GONE);
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

        if (mFirstnameEditxt.getText().toString().isEmpty()) {
            mInputLayoutFirstNameS2.setError("Your FirstName is required!");
            isValid = false;
        } else if (mLastnameEditxtS2.getText().toString().isEmpty()) {
            mInputLayoutLastNameS2.setError("Your LastName is required!");
            isValid = false;
        } else{
            mInputLayoutFirstNameS2.setErrorEnabled(false);
            mInputLayoutLastNameS2.setErrorEnabled(false);

        }

        if (mDobEditxtS2.getText().toString().isEmpty()) {
            mInputLayoutDateofBirthS2.setError("Swiss Cycle Value Number is required!");

            isValid = false;
        }else {
            mInputLayoutDateofBirthS2.setErrorEnabled(false);
        }

        if (mPhoneNoEditxtS2.getText().toString().isEmpty()) {
            mInputLayoutPhoneS2.setError("Phone Number is required!");

            isValid = false;
        }else {
            mInputLayoutPhoneS2.setErrorEnabled(false);
        }

        if (mEmailEditxtS2.getText().toString().isEmpty()) {
            mInputLayoutEmailS2.setError("Email is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mEmailEditxtS2.getText().toString())) {
            mInputLayoutEmailS2.setError("Valid Email is required!");
            isValid = false;
        } else {
            mInputLayoutEmailS2.setErrorEnabled(false);
        }
        if (mDisableEditxtS2.getText().toString().isEmpty()) {
            mInputLayoutDisableS2.setError("If No, please enter No");

            isValid = false;
        }else {
            mInputLayoutDisableS2.setErrorEnabled(false);
        }

        if (addpersonal_img_url==null) {
            showMessage("Please upload an image: passport,company license..etc");
            isValid = false;
        }
        // Spinner Validations
        //policyType validation
        genderString = mGenderSpinnerS2.getSelectedItem().toString();
        if (genderString.equals("Gender")) {
            showMessage("Select Gender Type");
            isValid = false;
        }
        //Private Spinner
        benefitString = mBenefitSpinnerS2.getSelectedItem().toString();
        if (benefitString.equals("Select Benefit Category")) {
            showMessage("Don't forget to Select Benefit Category");
            isValid = false;
        }

        maritalString = mMaritalSpinnerS2.getSelectedItem().toString();
        if (maritalString.equals("Select Marital Status")) {

            showMessage("Select Marital Status");
            isValid = false;
        }

        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




    }

    private void initFragment() {
        mBtnLayout2S2.setVisibility(View.GONE);
        mProgressbar2S2.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setSwissIAddFirstName(mFirstnameEditxt.getText().toString());
            userPreferences.setSwissIAddLastName(mLastnameEditxtS2.getText().toString());
            userPreferences.setSwissIAddGender(genderString);
            userPreferences.setSwissIAddMaritalStatus(maritalString);
            userPreferences.setSwissIAddBenefitCat(benefitString);
            userPreferences.setSwissIAddPhoneNum(mPhoneNoEditxtS2.getText().toString());
            userPreferences.setSwissIAddEmail(mEmailEditxtS2.getText().toString());
            userPreferences.setSwissIAddDisability(mDisableEditxtS2.getText().toString());

           // Fragment quoteBuyFragment3 = new SwissInsureFragment3();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_swiss_form_container, SwissFragment3.newInstance("Eligible","8000"), SwissFragment3.class.getSimpleName());
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar2S2.setVisibility(View.GONE);
            mVNextBtn2S2.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }
    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout2, s, Snackbar.LENGTH_LONG).show();
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



    private void showDatePicker() {
        //Get current date
        Calendar calendar = Calendar.getInstance();

        //Create datePickerDialog with initial date which is current and decide what happens when a date is selected.
        datePickerDialog1 = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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
                DobString = dayOfMonth + "-" + monthofYear + "-" + year;
                mDobEditxtS2.setText(DobString);
                datePickerDialog1.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }



}
