package com.example.sti_agent.operation_fragment.Etic;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment2;
import com.example.sti_agent.operation_fragment.Etic.EticFragment1;
import com.example.sti_agent.operation_fragment.Etic.EticFragment2;
import com.google.android.material.snackbar.Snackbar;


import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EticFragment1 extends Fragment implements View.OnClickListener{
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
    @BindView(R.id.prefix_spinner_e1)
    Spinner mPrefixSpinnerE1;
    @BindView(R.id.inputLayoutFirstName_e1)
    TextInputLayout mInputLayoutFirstNameE1;
    @BindView(R.id.firstname_editxt_e1)
    EditText mFirstnameEditxtE1;
    @BindView(R.id.inputLayoutLastName_e1)
    TextInputLayout mInputLayoutLastNameE1;
    @BindView(R.id.lastname_editxt_e1)
    EditText mLastnameEditxtE1;
    @BindView(R.id.gender_spinner_e1)
    Spinner mGenderSpinnerE1;
    @BindView(R.id.inputLayoutResAddr_e1)
    TextInputLayout mInputLayoutResAddrE1;
    @BindView(R.id.residents_addr_editxt_e1)
    EditText mResidentsAddrEditxtE1;
    @BindView(R.id.inputLayoutNextKin_e1)
    TextInputLayout mInputLayoutNextKinE1;
    @BindView(R.id.next_kin_editxt_e1)
    EditText mNextKinEditxtE1;
    @BindView(R.id.inputLayoutPhone_e1)
    TextInputLayout mInputLayoutPhoneE1;
    @BindView(R.id.phone_no_editxt_e1)
    EditText mPhoneNoEditxtE1;
    @BindView(R.id.inputLayoutEmail_e1)
    TextInputLayout mInputLayoutEmailE1;
    @BindView(R.id.email_editxt_e1)
    EditText mEmailEditxtE1;
    @BindView(R.id.inputLayoutMailingAddr_e1)
    TextInputLayout mInputLayoutMailingAddrE1;
    @BindView(R.id.mail_addr_editxt_e1)
    EditText mMailAddrEditxtE1;
    @BindView(R.id.upload_img_btn_e1)
    Button mUploadImgBtnE1;
    @BindView(R.id.next_btn1_e1)
    Button mNextBtn1E1;
    @BindView(R.id.progressbar1_e1)
    AVLoadingIndicatorView mProgressbar1E1;


    String genderString,prifixString;
    private int currentStep = 0;


    public EticFragment1() {
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
    public static EticFragment1 newInstance(String param1, String param2) {
        EticFragment1 fragment = new EticFragment1();
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
        View view=inflater.inflate(R.layout.fragment_etic1, container, false);
        ButterKnife.bind(this,view);

        mStepView.go(currentStep, true);



        init();
        prefixSpinner();
        genderSpinner();
        setViewActions();

        return  view;
    }

    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation



        mFirstnameEditxtE1.setText(userPreferences.getEticIFirstName());

        mLastnameEditxtE1.setText(userPreferences.getEticILastName());

        mResidentsAddrEditxtE1.setText(userPreferences.getEticIResAdrr());
        mNextKinEditxtE1.setText(userPreferences.getEticINextKin());
        mPhoneNoEditxtE1.setText(userPreferences.getEticIPhoneNum());
        mEmailEditxtE1.setText(userPreferences.getEticIEmail());

        mMailAddrEditxtE1.setText(userPreferences.getEticIMailingAddr());

    }

       private void prefixSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.prefix_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mPrefixSpinnerE1.setAdapter(staticAdapter);

           mPrefixSpinnerE1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPrefixSpinnerE1.getItemAtPosition(0);


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
        mGenderSpinnerE1.setAdapter(staticAdapter);

        mGenderSpinnerE1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGenderSpinnerE1.getItemAtPosition(0);


            }
        });

    }

    //seting onclicks listeners
    private void setViewActions() {
        mUploadImgBtnE1.setOnClickListener(this);
        mNextBtn1E1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_img_btn_e1:

                break;
            case R.id.next_btn1_e1:
//                validate user input

                if (currentStep < mStepView.getStepCount() - 1) {
                    currentStep++;
                    mStepView.go(currentStep, true);
                    validateUserInputs();
                } else {
                    mStepView.done(true);
                }

                break;
        }
    }

    private void validateUserInputs() {

        boolean isValid = true;

        if (mFirstnameEditxtE1.getText().toString().isEmpty()) {
            mInputLayoutFirstNameE1.setError("Your FirstName is required!");
            isValid = false;
        } else if (mLastnameEditxtE1.getText().toString().isEmpty()) {
            mInputLayoutLastNameE1.setError("Your LastName is required!");
            isValid = false;
        } else if (mNextKinEditxtE1.getText().toString().isEmpty()) {
            mInputLayoutNextKinE1.setError("Next of Kin is required!");
            isValid = false;
        }else{
            mInputLayoutFirstNameE1.setErrorEnabled(false);
            mInputLayoutLastNameE1.setErrorEnabled(false);
            mInputLayoutNextKinE1.setErrorEnabled(false);

        }

            if (mEmailEditxtE1.getText().toString().isEmpty()) {
                mInputLayoutEmailE1.setError("Email is required!");
                isValid = false;
            } else if (!isValidEmailAddress(mEmailEditxtE1.getText().toString())) {
                mInputLayoutEmailE1.setError("Valid Email is required!");
                isValid = false;
            } else {
                mInputLayoutEmailE1.setErrorEnabled(false);
            }


        if (mPhoneNoEditxtE1.getText().toString().isEmpty()) {
            mInputLayoutPhoneE1.setError("Phone number is required");
            isValid = false;
        } else if (mPhoneNoEditxtE1.getText().toString().trim().length() < 11 ) {
            mInputLayoutPhoneE1.setError("Your Phone number must be 11 in length");
            isValid = false;
        } else {
            mInputLayoutPhoneE1.setErrorEnabled(false);
        }

        if (mResidentsAddrEditxtE1.getText().toString().isEmpty()) {
            mInputLayoutResAddrE1.setError("Resident Address is required");
            isValid = false;
        } else {
            mInputLayoutResAddrE1.setErrorEnabled(false);
        }

       
        //Prefix Spinner
        prifixString = mPrefixSpinnerE1.getSelectedItem().toString();
        if (prifixString.equals("Prefix")) {
            showMessage("Select your Prefix e.g Mr.");
            isValid = false;
        }

        genderString = mGenderSpinnerE1.getSelectedItem().toString();
        if (genderString.equals("Gender")) {
            showMessage("Don't forget to Select Gender");
            isValid = false;
        }

       
        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




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
    
    private void initFragment() {
        mNextBtn1E1.setVisibility(View.GONE);
        mProgressbar1E1.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation
            
            userPreferences.setEticIPrefix(prifixString);
            userPreferences.setEticIFirstName(mFirstnameEditxtE1.getText().toString());
            userPreferences.setEticILastName(mLastnameEditxtE1.getText().toString());
            userPreferences.setEticIGender(genderString);
            userPreferences.setEticIResAdrr(mResidentsAddrEditxtE1.getText().toString());
            userPreferences.setEticINextKin(mNextKinEditxtE1.getText().toString());
            userPreferences.setEticIPhoneNum(mPhoneNoEditxtE1.getText().toString());
            userPreferences.setEticIEmail(mEmailEditxtE1.getText().toString());
            userPreferences.setEticIMailingAddr(mMailAddrEditxtE1.getText().toString());
            userPreferences.setEticIPhoneNum(mPhoneNoEditxtE1.getText().toString());

            
            
            Fragment eticFragment2 = new EticFragment2();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_etic_form_container, eticFragment2);
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1E1.setVisibility(View.GONE);
            mNextBtn1E1.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout1, s, Snackbar.LENGTH_SHORT).show();
    }


    public  boolean isNetworkConnected() {
        Context context = getContext();
        final ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }

        return false;
    }


}
