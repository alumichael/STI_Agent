package com.example.sti_agent.operation_fragment.OtherInsurance;

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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OtherInsureFragment1 extends Fragment implements View.OnClickListener{
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
    @BindView(R.id.type_spinner_o1)
    Spinner mTypeSpinnerO1;
    @BindView(R.id.prefix_spinner_o1)
    Spinner mPrefixSpinnerO1;
    @BindView(R.id.inputLayoutCompanyName_o1)
    TextInputLayout mInputLayoutCompanyNameO1;
    @BindView(R.id.companyname_editxt_o1)
    EditText mCompanynameEditxtO1;
    @BindView(R.id.inputLayoutTinNum_o1)
    TextInputLayout mInputLayoutTinNumO1;
    @BindView(R.id.tin_num_editxt_o1)
    EditText mTinNumEditxtO1;
    @BindView(R.id.inputLayoutOfficeAddr_o1)
    TextInputLayout mInputLayoutOfficeAddrO1;
    @BindView(R.id.office_addr_editxt_o1)
    EditText mOfficeAddrEditxtO1;
    @BindView(R.id.inputLayoutContactPerson_o1)
    TextInputLayout mInputLayoutContactPersonO1;
    @BindView(R.id.contact_person_editxt_o1)
    EditText mContactPersonEditxtO1;
    @BindView(R.id.inputLayoutFirstName_o1)
    TextInputLayout mInputLayoutFirstNameO1;
    @BindView(R.id.firstname_editxt_o1)
    EditText mFirstnameEditxtO1;
    @BindView(R.id.inputLayoutLastName_o1)
    TextInputLayout mInputLayoutLastNameO1;
    @BindView(R.id.lastname_editxt_o1)
    EditText mLastnameEditxtO1;
    @BindView(R.id.gender_spinner_o1)
    Spinner mGenderSpinnerO1;
    @BindView(R.id.inputLayoutResAddr_o1)
    TextInputLayout mInputLayoutResAddrO1;
    @BindView(R.id.residents_addr_editxt_o1)
    EditText mResidentsAddrEditxtO1;
    @BindView(R.id.inputLayoutNextKin_o1)
    TextInputLayout mInputLayoutNextKinO1;
    @BindView(R.id.next_kin_editxt_o1)
    EditText mNextKinEditxtO1;
    @BindView(R.id.inputLayoutPhone_o1)
    TextInputLayout mInputLayoutPhoneO1;
    @BindView(R.id.phone_no_editxt_o1)
    EditText mPhoneNoEditxtO1;
    @BindView(R.id.inputLayoutEmail_o1)
    TextInputLayout mInputLayoutEmailO1;
    @BindView(R.id.email_editxt_o1)
    EditText mEmailEditxtO1;
    @BindView(R.id.inputLayoutMailingAddr_o1)
    TextInputLayout mInputLayoutMailingAddrO1;
    @BindView(R.id.mail_addr_editxt_o1)
    EditText mMailAddrEditxtO1;
    @BindView(R.id.upload_img_btn_o1)
    Button mUploadImgBtnO1;
    @BindView(R.id.next_btn1_o1)
    Button mNextBtn1O1;
    @BindView(R.id.progressbar1_o1)
    AVLoadingIndicatorView mProgressbar1O1;
    

    String typeString,genderString,prifixString,cameraFilePath;
    private int currentStep = 0;

    int PICK_IMAGE_PASSPORT = 1;
    int CAM_IMAGE_PASSPORT = 2;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri personal_info_img_uri;
    String personal_img_url;


    public OtherInsureFragment1() {
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
    public static OtherInsureFragment1 newInstance(String param1, String param2) {
        OtherInsureFragment1 fragment = new OtherInsureFragment1();
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
        View view=inflater.inflate(R.layout.fragment_other1, container, false);
        ButterKnife.bind(this,view);

        mStepView.go(currentStep, true);



        init();

        mTypeSpinnerO1();
        mPrefixSpinnerO1();
        mGenderSpinnerO1();
        setViewActions();

        return  view;
    }

    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mCompanynameEditxtO1.setText(userPreferences.getOtherICompanyName());

        mTinNumEditxtO1.setText(userPreferences.getOtherITinNumber());

        mOfficeAddrEditxtO1.setText(userPreferences.getOtherIOff_addr());


        mContactPersonEditxtO1.setText(userPreferences.getOtherIContPerson());

        mNextKinEditxtO1.setText(userPreferences.getOtherINextKin());

        mFirstnameEditxtO1.setText(userPreferences.getOtherIFirstName());

        mLastnameEditxtO1.setText(userPreferences.getOtherILastName());

        mResidentsAddrEditxtO1.setText(userPreferences.getOtherIResAdrr());
        mPhoneNoEditxtO1.setText(userPreferences.getOtherIPhoneNum());
        mEmailEditxtO1.setText(userPreferences.getOtherIEmail());
        mMailAddrEditxtO1.setText(userPreferences.getOtherIMailingAddr());

    }


    private void mTypeSpinnerO1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mTypeSpinnerO1.setAdapter(staticAdapter);

        mTypeSpinnerO1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                if(stringText.equals("Individual")){

                    mTypeSpinnerO1.setVisibility(View.VISIBLE);
                    mTypeSpinnerO1.setClickable(true);


                    //De-Visualizing the corporate form
                    mInputLayoutCompanyNameO1.setVisibility(View.GONE);
                    mCompanynameEditxtO1.setClickable(false);
                    mInputLayoutTinNumO1.setVisibility(View.GONE);
                    mTinNumEditxtO1.setClickable(false);
                    mInputLayoutOfficeAddrO1.setVisibility(View.GONE);
                    mOfficeAddrEditxtO1.setClickable(false);
                    mInputLayoutContactPersonO1.setVisibility(View.GONE);
                    mContactPersonEditxtO1.setClickable(false);

                    //Visualizing the individual form

                    mPrefixSpinnerO1.setVisibility(View.VISIBLE);
                    mPrefixSpinnerO1.setClickable(true);
                    mInputLayoutFirstNameO1.setVisibility(View.VISIBLE);
                    mFirstnameEditxtO1.setClickable(true);
                    mInputLayoutLastNameO1.setVisibility(View.VISIBLE);
                    mLastnameEditxtO1.setClickable(true);
                    mGenderSpinnerO1.setVisibility(View.VISIBLE);
                    mGenderSpinnerO1.setClickable(true);
                    mInputLayoutResAddrO1.setVisibility(View.VISIBLE);
                    mResidentsAddrEditxtO1.setClickable(true);
                    mInputLayoutNextKinO1.setVisibility(View.VISIBLE);
                    mNextKinEditxtO1.setClickable(true);


                }else if(stringText.equals("Corporate")){

                    //De-Visualizing the individual form
                    mPrefixSpinnerO1.setVisibility(View.GONE);
                    mPrefixSpinnerO1.setClickable(false);
                    mFirstnameEditxtO1.setVisibility(View.GONE);
                    mFirstnameEditxtO1.setClickable(false);
                    mInputLayoutLastNameO1.setVisibility(View.GONE);
                    mLastnameEditxtO1.setClickable(false);
                    mInputLayoutNextKinO1.setVisibility(View.GONE);
                    mNextKinEditxtO1.setClickable(false);
                    mGenderSpinnerO1.setVisibility(View.GONE);
                    mGenderSpinnerO1.setClickable(false);
                    mInputLayoutResAddrO1.setVisibility(View.GONE);
                    mResidentsAddrEditxtO1.setClickable(false);



                    //Visualizing the individual form
                    mTypeSpinnerO1.setVisibility(View.VISIBLE);
                    mTypeSpinnerO1.setClickable(true);
                    mInputLayoutCompanyNameO1.setVisibility(View.VISIBLE);
                    mCompanynameEditxtO1.setClickable(true);
                    mInputLayoutTinNumO1.setVisibility(View.VISIBLE);
                    mTinNumEditxtO1.setClickable(true);
                    mInputLayoutOfficeAddrO1.setVisibility(View.VISIBLE);
                    mOfficeAddrEditxtO1.setClickable(true);
                    mInputLayoutContactPersonO1.setVisibility(View.VISIBLE);
                    mContactPersonEditxtO1.setClickable(true);

                }else {

                    //De-Visualizing the individual form
                    mPrefixSpinnerO1.setVisibility(View.GONE);
                    mFirstnameEditxtO1.setVisibility(View.GONE);
                    mInputLayoutLastNameO1.setVisibility(View.GONE);
                    mGenderSpinnerO1.setVisibility(View.GONE);
                    mInputLayoutResAddrO1.setVisibility(View.GONE);
                    mInputLayoutCompanyNameO1.setVisibility(View.GONE);
                    mInputLayoutNextKinO1.setVisibility(View.GONE);
                    mInputLayoutTinNumO1.setVisibility(View.GONE);
                    mInputLayoutOfficeAddrO1.setVisibility(View.GONE);
                    mInputLayoutContactPersonO1.setVisibility(View.GONE);




                    //Visualizing the individual form
                    mTypeSpinnerO1.setVisibility(View.VISIBLE);
                    mInputLayoutPhoneO1.setVisibility(View.VISIBLE);
                    mInputLayoutEmailO1.setVisibility(View.VISIBLE);
                    mInputLayoutMailingAddrO1.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mTypeSpinnerO1.getItemAtPosition(0);
                mPrefixSpinnerO1.setVisibility(View.GONE);
                mFirstnameEditxtO1.setVisibility(View.GONE);
                mInputLayoutLastNameO1.setVisibility(View.GONE);
                mGenderSpinnerO1.setVisibility(View.GONE);
                mInputLayoutResAddrO1.setVisibility(View.GONE);
                mInputLayoutNextKinO1.setVisibility(View.GONE);
                mInputLayoutCompanyNameO1.setVisibility(View.GONE);
                mInputLayoutTinNumO1.setVisibility(View.GONE);
                mInputLayoutOfficeAddrO1.setVisibility(View.GONE);
                mInputLayoutContactPersonO1.setVisibility(View.GONE);




                //Visualizing the individual form
                mTypeSpinnerO1.setVisibility(View.VISIBLE);
                mInputLayoutPhoneO1.setVisibility(View.VISIBLE);
                mInputLayoutEmailO1.setVisibility(View.VISIBLE);
                mInputLayoutMailingAddrO1.setVisibility(View.VISIBLE);


            }
        });

    }

    private void mPrefixSpinnerO1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.prefix_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mPrefixSpinnerO1.setAdapter(staticAdapter);

        mPrefixSpinnerO1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPrefixSpinnerO1.getItemAtPosition(0);


            }
        });

    }
    private void mGenderSpinnerO1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mGenderSpinnerO1.setAdapter(staticAdapter);

        mGenderSpinnerO1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mGenderSpinnerO1.getItemAtPosition(0);

            }
        });

    }

    //setting onclicks listeners
    private void setViewActions() {

        mNextBtn1O1.setOnClickListener(this);
        mUploadImgBtnO1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.next_btn1_o1:
//                validate user input
                validateUserInputs();


                break;
                
            case R.id.upload_img_btn_o1:
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
                mUploadImgBtnO1.setBackgroundColor(getResources().getColor(R.color.colorAccentEnds));

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
            Random random=new Random();
            String rand= String.valueOf(random.nextInt());
            if (requestCode == 1) {
                personal_info_img_uri = data.getData();

                try {
                    if (personal_info_img_uri != null) {
                        String name = mEmailEditxtO1.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your email address first");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(personal_info_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mNextBtn1O1.setVisibility(View.GONE);
                                            mProgressbar1O1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar1O1.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar1O1.setVisibility(View.GONE);
                                            mNextBtn1O1.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mNextBtn1O1.setVisibility(View.VISIBLE);
                                            mProgressbar1O1.setVisibility(View.GONE);
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
                        String name = mEmailEditxtO1.getText().toString()+rand;
                        if (name.equals("")) {
                            showMessage("Enter your email address first");

                        } else {

                            String imageId = MediaManager.get().upload(personal_info_img_uri)
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mNextBtn1O1.setVisibility(View.GONE);
                                            mProgressbar1O1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mProgressbar1O1.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mProgressbar1O1.setVisibility(View.GONE);
                                            mNextBtn1O1.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mNextBtn1O1.setVisibility(View.VISIBLE);
                                            mProgressbar1O1.setVisibility(View.GONE);
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

        if (mFirstnameEditxtO1.getText().toString().isEmpty()&&mFirstnameEditxtO1.isClickable()) {
            mFirstnameEditxtO1.setError("Your FirstName is required!");
            isValid = false;
        } else if (mLastnameEditxtO1.getText().toString().isEmpty()&&mInputLayoutLastNameO1.isClickable()) {
            mInputLayoutLastNameO1.setError("Your LastName is required!");
            isValid = false;
        } else if (mCompanynameEditxtO1.getText().toString().isEmpty()&&mInputLayoutCompanyNameO1.isClickable()) {
            mInputLayoutCompanyNameO1.setError("Your Company Name is required!");
            isValid = false;
        } else if (mTinNumEditxtO1.getText().toString().isEmpty()&&mInputLayoutTinNumO1.isClickable()) {
            mInputLayoutTinNumO1.setError("Your TIN Number is required!");
            isValid = false;
        } else if (mOfficeAddrEditxtO1.getText().toString().isEmpty()&&mInputLayoutOfficeAddrO1.isClickable()) {
            mInputLayoutOfficeAddrO1.setError("Office Address is required!");
            isValid = false;
        }else if (mNextKinEditxtO1.getText().toString().isEmpty()&&mInputLayoutNextKinO1.isClickable()) {
            mInputLayoutNextKinO1.setError("Next of Kin is required!");
            isValid = false;
        }else {
            mInputLayoutFirstNameO1.setErrorEnabled(false);
            mInputLayoutLastNameO1.setErrorEnabled(false);
            mInputLayoutCompanyNameO1.setErrorEnabled(false);
            mInputLayoutTinNumO1.setErrorEnabled(false);
            mInputLayoutNextKinO1.setErrorEnabled(false);
            mInputLayoutOfficeAddrO1.setErrorEnabled(false);
        }

        if (mEmailEditxtO1.getText().toString().isEmpty()&&mInputLayoutEmailO1.isClickable()) {
            mInputLayoutEmailO1.setError("Email is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mEmailEditxtO1.getText().toString())) {
            mInputLayoutEmailO1.setError("Valid Email is required!");
            isValid = false;
        } else {
            mInputLayoutEmailO1.setErrorEnabled(false);
        }

        if (mMailAddrEditxtO1.getText().toString().isEmpty()&& mInputLayoutMailingAddrO1.isClickable()) {
            mInputLayoutMailingAddrO1.setError("Mailing Address is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mMailAddrEditxtO1.getText().toString())&&mInputLayoutMailingAddrO1.isClickable()) {
            mInputLayoutMailingAddrO1.setError("Valid Mailing Address is required!");
            isValid = false;
        } else {
            mInputLayoutMailingAddrO1.setErrorEnabled(false);
        }


        if (mPhoneNoEditxtO1.getText().toString().isEmpty()&&mInputLayoutPhoneO1.isClickable()) {
            mInputLayoutPhoneO1.setError("Phone number is required");
            isValid = false;
        } else if (mPhoneNoEditxtO1.getText().toString().trim().length() < 11 && mInputLayoutPhoneO1.isClickable()) {
            mInputLayoutPhoneO1.setError("Your Phone number must be 11 in length");
            isValid = false;
        } else {
            mInputLayoutPhoneO1.setErrorEnabled(false);
        }

        if (mContactPersonEditxtO1.getText().toString().isEmpty()&&mInputLayoutContactPersonO1.isClickable()) {
            mInputLayoutContactPersonO1.setError("Contact Person is required");
            isValid = false;
        } else {
            mInputLayoutContactPersonO1.setErrorEnabled(false);
        }
        if (mResidentsAddrEditxtO1.getText().toString().isEmpty()&&mInputLayoutResAddrO1.isClickable()) {
            mInputLayoutResAddrO1.setError("Resident Address is required");
            isValid = false;
        } else {
            mInputLayoutResAddrO1.setErrorEnabled(false);
        }


        //Tyepe Spinner
        typeString = mTypeSpinnerO1.getSelectedItem().toString();
        if (typeString.equals("Select Type")&&mTypeSpinnerO1.isClickable()) {

            showMessage("Select Product Type");
            isValid = false;
        }
        //Prefix Spinner
        prifixString = mPrefixSpinnerO1.getSelectedItem().toString();
        if (prifixString.equals("Select Prefix")&&mPrefixSpinnerO1.isClickable()) {
            showMessage("Select your Prefix e.g Mr.");
            isValid = false;
        }

        genderString = mGenderSpinnerO1.getSelectedItem().toString();
        if (genderString.equals("Gender")&&mGenderSpinnerO1.isClickable()) {
            showMessage("Don't forget to Select Gender");
            isValid = false;
        }

        if (personal_img_url==null) {
            showMessage("Please upload an image: passport,company license..etc");
            isValid = false;
        }


        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




    }

    private void initFragment() {
        mNextBtn1O1.setVisibility(View.GONE);
        mProgressbar1O1.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setOtherPtype(typeString);
            userPreferences.setOtherIPrefix(prifixString);
            userPreferences.setOtherICompanyName(mCompanynameEditxtO1.getText().toString());
            userPreferences.setOtherITinNumber(mTinNumEditxtO1.getText().toString());
            userPreferences.setOtherIOff_addr(mOfficeAddrEditxtO1.getText().toString());
            userPreferences.setOtherINextKin(mNextKinEditxtO1.getText().toString());
            userPreferences.setOtherIContPerson(mContactPersonEditxtO1.getText().toString());
            userPreferences.setOtherIFirstName(mFirstnameEditxtO1.getText().toString());
            userPreferences.setOtherILastName(mLastnameEditxtO1.getText().toString());
            userPreferences.setOtherIGender(genderString);
            userPreferences.setOtherIResAdrr(mResidentsAddrEditxtO1.getText().toString());
            userPreferences.setOtherIPhoneNum(mPhoneNoEditxtO1.getText().toString());
            userPreferences.setOtherIEmail(mEmailEditxtO1.getText().toString());
            userPreferences.setOtherIMailingAddr(mMailAddrEditxtO1.getText().toString());
            userPreferences.setOtherIPersonal_image(personal_img_url);
            if (currentStep < mStepView.getStepCount() - 1) {
                currentStep++;
                Fragment otherInsureFragment2 = new OtherInsureFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_other_form_container, otherInsureFragment2);
                ft.commit();
                mStepView.go(currentStep, true);

            } else {
                mStepView.done(true);
            }


        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1O1.setVisibility(View.GONE);
            mNextBtn1O1.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout1, s, Snackbar.LENGTH_LONG).show();
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
