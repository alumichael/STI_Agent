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


public class SwissFragment1 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.qb_form_layout1)
    FrameLayout mQbFormLayout1;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.prefix_spinner_s)
    Spinner mPrefixSpinnerS;

    @BindView(R.id.benefit_spinner_s1)
    Spinner mBenefitSpinnerS;

    @BindView(R.id.inputLayoutFirstName_s1)
    TextInputLayout mInputLayoutFirstNameS1;
    @BindView(R.id.firstname_editxt_s1)
    EditText mFirstnameEditxtS1;
    @BindView(R.id.inputLayoutLastName_s1)
    TextInputLayout mInputLayoutLastNameS1;
    @BindView(R.id.lastname_editxt_s1)
    EditText mLastnameEditxtS1;
    @BindView(R.id.gender_spinner_s1)
    Spinner mGenderSpinnerS1;
    @BindView(R.id.inputLayoutResAddr_s1)
    TextInputLayout mInputLayoutResAddrS1;
    @BindView(R.id.residents_addr_editxt_s1)
    EditText mResidentsAddrEditxtS1;
    @BindView(R.id.marital_spinner_s1)
    Spinner mMaritalSpinnerS1;
    @BindView(R.id.inputLayoutPhone_s1)
    TextInputLayout mInputLayoutPhoneS1;
    @BindView(R.id.phone_no_editxt_s1)
    EditText mPhoneNoEditxtS1;
    @BindView(R.id.inputLayoutNextKin_s1)
    TextInputLayout mInputLayoutNextKinS1;
    @BindView(R.id.next_kin_editxt_s1)
    EditText mNextKinEditxtS1;
    @BindView(R.id.inputLayoutNextKinAddr_s1)
    TextInputLayout mInputLayoutNextKinAddrS1;
    @BindView(R.id.next_kin_editxt_addr_s1)
    EditText mNextKinEditxtAddrS1;
    @BindView(R.id.inputLayoutPhoneNextKin_s1)
    TextInputLayout mInputLayoutPhoneNextKinS1;
    @BindView(R.id.phone_Nextkin_editxt_s1)
    EditText mPhoneNextkinEditxtS1;
    @BindView(R.id.inputLayoutDateofBirth_s1)
    TextInputLayout mInputLayoutDateofBirthS1;
    @BindView(R.id.dob_editxt_s)
    EditText mDobEditxtS;
    @BindView(R.id.inputLayoutDisability_s1)
    TextInputLayout mInputLayoutDisabilityS1;
    @BindView(R.id.disabilty_editxt_s1)
    EditText mDisabiltyEditxtS1;
    @BindView(R.id.upload_passport_btn_s1)
    Button mUploadPassportBtnS1;
    @BindView(R.id.next_btn1_s1)
    Button mNextBtn1S1;
    @BindView(R.id.progressbar1_s1)
    AVLoadingIndicatorView mProgressbar1S1;
   

    String maritalString,genderString,prifixString,benefitString,DobString,cameraFilePath;
    DatePickerDialog datePickerDialog1;
    private int currentStep = 0;

    int PICK_IMAGE_PASSPORT = 1;
    int CAM_IMAGE_PASSPORT = 2;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri personal_info_img_uri;
    String personal_img_url;


    public SwissFragment1() {
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
    public static SwissFragment1 newInstance(String param1, String param2) {
        SwissFragment1 fragment = new SwissFragment1();
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
        View view=inflater.inflate(R.layout.fragment_swiss1, container, false);
        ButterKnife.bind(this,view);

        mStepView.go(currentStep, true);



        init();

        maritaltypeSpinner();
        prefixSpinner();
        genderSpinner();
        benfitSpinner();
        setViewActions();
        showDatePicker();

        return  view;
    }



    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation

        

        mFirstnameEditxtS1.setText(userPreferences.getSwissIFirstName());

        mLastnameEditxtS1.setText(userPreferences.getSwissILastName());

        mResidentsAddrEditxtS1.setText(userPreferences.getSwissIResAdrr());
        mNextKinEditxtS1.setText(userPreferences.getSwissINextKin());
        mNextKinEditxtAddrS1.setText(userPreferences.getSwissINextKinAddr());
        mPhoneNextkinEditxtS1.setText(userPreferences.getSwissINextKinPhoneNum());
        mPhoneNoEditxtS1.setText(userPreferences.getSwissIPhoneNum());
        mDobEditxtS.setText(userPreferences.getSwissIDob());

        mDisabiltyEditxtS1.setText(userPreferences.getSwissIDisable());

    }

    private void benfitSpinner(){

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.benefit_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mBenefitSpinnerS.setAdapter(staticAdapter);

        mBenefitSpinnerS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mBenefitSpinnerS.getItemAtPosition(0);


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
        mMaritalSpinnerS1.setAdapter(staticAdapter);

        mMaritalSpinnerS1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mMaritalSpinnerS1.getItemAtPosition(0);


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
        mPrefixSpinnerS.setAdapter(staticAdapter);

        mPrefixSpinnerS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPrefixSpinnerS.getItemAtPosition(0);


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
        mGenderSpinnerS1.setAdapter(staticAdapter);

        mGenderSpinnerS1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGenderSpinnerS1.getItemAtPosition(0);


            }
        });

    }

    //seting onclicks listeners
    private void setViewActions() {
        mUploadPassportBtnS1.setOnClickListener(this);
        mDobEditxtS.setOnClickListener(this);
        mNextBtn1S1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_passport_btn_s1:
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
                mUploadPassportBtnS1.setBackgroundColor(getResources().getColor(R.color.colorAccentEnds));

                break;
            case R.id.dob_editxt_s:
                datePickerDialog1.show();
                break;

            case R.id.next_btn1_s1:
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
        if (resultCode == 0) {
            showMessage("No image is selected, try again");
            return;
        }


        showMessage("Uploading...");
        if (networkConnection.isNetworkConnected(getContext())) {
            Random random=new Random();
            String rand= String.valueOf(random.nextInt());
            if (requestCode == 1) {
                personal_info_img_uri = data.getData();

                try {
                    if (personal_info_img_uri != null) {
                        String name = mFirstnameEditxtS1.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your your first name first");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(personal_info_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mNextBtn1S1.setVisibility(View.GONE);
                                            mProgressbar1S1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar1S1.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar1S1.setVisibility(View.GONE);
                                            mNextBtn1S1.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mNextBtn1S1.setVisibility(View.VISIBLE);
                                            mProgressbar1S1.setVisibility(View.GONE);
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

            }else if (requestCode == 2) {
                personal_info_img_uri = Uri.parse(cameraFilePath);

                try {
                    if (personal_info_img_uri != null) {
                        String name = mFirstnameEditxtS1.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your your first name first");

                        } else {

                            String imageId = MediaManager.get().upload(personal_info_img_uri)
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mNextBtn1S1.setVisibility(View.GONE);
                                            mProgressbar1S1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar1S1.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar1S1.setVisibility(View.GONE);
                                            mNextBtn1S1.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mNextBtn1S1.setVisibility(View.VISIBLE);
                                            mProgressbar1S1.setVisibility(View.GONE);
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

            if (mFirstnameEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutFirstNameS1.setError("Your FirstName is required!");
                isValid = false;
            } else if (mLastnameEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutLastNameS1.setError("Your LastName is required!");
                isValid = false;
            } else{
                mInputLayoutFirstNameS1.setErrorEnabled(false);
                mInputLayoutLastNameS1.setErrorEnabled(false);

            }

            
            if (mPhoneNoEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutPhoneS1.setError("Phone number is required");
                isValid = false;
            } else if (mPhoneNoEditxtS1.getText().toString().trim().length() < 11 ) {
                mInputLayoutPhoneS1.setError("Your Phone number must be 11 in length");
                isValid = false;
            } else {
                mInputLayoutPhoneS1.setErrorEnabled(false);
            }

            if (mResidentsAddrEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutResAddrS1.setError("Resident Address is required");
                isValid = false;
            } else {
                mInputLayoutResAddrS1.setErrorEnabled(false);
            }

            if (mNextKinEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutNextKinS1.setError("Next of Kin Name is required");
                isValid = false;
            } else {
                mInputLayoutNextKinS1.setErrorEnabled(false);
            }

            if (mNextKinEditxtAddrS1.getText().toString().isEmpty()) {
                mInputLayoutNextKinAddrS1.setError("Next of Kin Address is required");
                isValid = false;
            } else {
                mInputLayoutNextKinAddrS1.setErrorEnabled(false);
            }
            if (mPhoneNextkinEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutPhoneNextKinS1.setError("Next of Kin Phone number is required");
                isValid = false;
            } else if (mPhoneNextkinEditxtS1.getText().toString().trim().length() < 11 ) {
                mInputLayoutPhoneNextKinS1.setError("Next of Kin Phone number must be 11 in length");
                isValid = false;
            } else {
                mInputLayoutPhoneNextKinS1.setErrorEnabled(false);
            }

            if (mDobEditxtS.getText().toString().isEmpty()) {
                mInputLayoutDateofBirthS1.setError("Date of Birth is required");
                isValid = false;
            } else {
                mInputLayoutDateofBirthS1.setErrorEnabled(false);
            }
            if (mDisabiltyEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutDisabilityS1.setError("If No, Enter No");
                isValid = false;
            } else {
                mInputLayoutDisabilityS1.setErrorEnabled(false);
            }

            if (personal_img_url==null) {
                showMessage("Please upload an image: passport,company license..etc");
                isValid = false;
            }

            //Type Spinner
            maritalString = mMaritalSpinnerS1.getSelectedItem().toString();
            if (maritalString.equals("Select Marital Status")) {

                showMessage("Select Marital Status");
                isValid = false;
            }
            //Prefix Spinner
            prifixString = mPrefixSpinnerS.getSelectedItem().toString();
            if (prifixString.equals("Select Prefix")) {
                showMessage("Select your Prefix e.g Mr.");
                isValid = false;
            }

            genderString = mGenderSpinnerS1.getSelectedItem().toString();
            if (genderString.equals("Gender")) {
                showMessage("Don't forget to Select Gender");
                isValid = false;
            }

            benefitString = mBenefitSpinnerS.getSelectedItem().toString();
            if (benefitString.equals("Select Benefit Category")) {
                showMessage("Don't forget to Select Benefit Category");
                isValid = false;
            }

            if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
                initFragment();
            }




    }

    private void initFragment() {
        mNextBtn1S1.setVisibility(View.GONE);
        mProgressbar1S1.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());
            //Temporal save and go to next Operation
            userPreferences.setSwissIMaritalStatus(maritalString);
            userPreferences.setSwissIBenefit(benefitString);
            userPreferences.setSwissIPrefix(prifixString);
            userPreferences.setSwissIFirstName(mFirstnameEditxtS1.getText().toString());
            userPreferences.setSwissILastName(mLastnameEditxtS1.getText().toString());
            userPreferences.setSwissIGender(genderString);
            userPreferences.setSwissIResAdrr(mResidentsAddrEditxtS1.getText().toString());
            userPreferences.setSwissINextKin(mNextKinEditxtS1.getText().toString());
            userPreferences.setSwissINextKinAddr(mNextKinEditxtAddrS1.getText().toString());
            userPreferences.setSwissINextKinPhoneNum(mPhoneNextkinEditxtS1.getText().toString());
            userPreferences.setSwissIDob(mDobEditxtS.getText().toString());
            userPreferences.setSwissIDisable(mDisabiltyEditxtS1.getText().toString());
            userPreferences.setSwissIPhoneNum(mPhoneNoEditxtS1.getText().toString());
            userPreferences.setSwissIPersonal_image(personal_img_url);

            if (currentStep < mStepView.getStepCount() - 1) {
                currentStep++;
                Fragment swissFragment2 = new SwissFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_swiss_form_container, swissFragment2);
                ft.commit();
                mStepView.go(currentStep, true);
            } else {
                mStepView.done(true);
            }


        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1S1.setVisibility(View.GONE);
            mNextBtn1S1.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout1, s, Snackbar.LENGTH_LONG).show();
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
                mDobEditxtS.setText(DobString);
                datePickerDialog1.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }




}
