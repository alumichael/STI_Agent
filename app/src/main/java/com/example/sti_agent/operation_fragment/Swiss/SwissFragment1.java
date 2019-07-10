package com.example.sti_agent.operation_fragment.Swiss;

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
import com.google.android.material.snackbar.Snackbar;

import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

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
   

    String maritalString,genderString,prifixString,benefitString;
    private int currentStep = 0;


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
        mNextBtn1S1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_passport_btn_s1:

                break;
            case R.id.next_btn1_s1:
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

            /*if (email_editxt.getText().toString().isEmpty()&&inputLayoutEmail.isClickable()) {
                inputLayoutEmail.setError("Email is required!");
                isValid = false;
            } else if (!isValidEmailAddress(email_editxt.getText().toString())) {
                inputLayoutEmail.setError("Valid Email is required!");
                isValid = false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }*/
            
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

            //Type Spinner
            maritalString = mMaritalSpinnerS1.getSelectedItem().toString();
            if (maritalString.equals("Marital Status")) {

                showMessage("Select Marital Status");
                isValid = false;
            }
            //Prefix Spinner
            prifixString = mPrefixSpinnerS.getSelectedItem().toString();
            if (prifixString.equals("Prefix")) {
                showMessage("Select your Prefix e.g Mr.");
                isValid = false;
            }

            genderString = mGenderSpinnerS1.getSelectedItem().toString();
            if (genderString.equals("Gender")) {
                showMessage("Don't forget to Select Gender");
                isValid = false;
            }

            benefitString = mBenefitSpinnerS.getSelectedItem().toString();
            if (benefitString.equals("Benefit Category")) {
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

            Fragment swissFragment2 = new SwissFragment2();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_swiss_form_container, swissFragment2);
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1S1.setVisibility(View.GONE);
            mNextBtn1S1.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout1, s, Snackbar.LENGTH_SHORT).show();
    }

   /* public static boolean isValidEmailAddress(String email) {
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
    }*/


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
