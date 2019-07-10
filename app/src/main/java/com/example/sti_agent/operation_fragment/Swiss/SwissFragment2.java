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
import android.widget.LinearLayout;
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




  /*  ArrayList<String> defaultType = new ArrayList<String>(
            Collections.singletonList("Vehicle Type"));

    ArrayList<String> acura = new ArrayList<String>(
            Arrays.asList("Vehicle Type","Buenos Aires", "Córdoba", "La Plata"));

    ArrayList<String> audi = new ArrayList<String>(
            Arrays.asList("Vehicle Type","MDX", "RDX", "TL"));*/



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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2_s2:
//                validate user input
                validateUserInputs();
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
        }
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
            mInputLayoutDisableS2.setError("If No, Enter No");

            isValid = false;
        }else {
            mInputLayoutDisableS2.setErrorEnabled(false);
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
        if (benefitString.equals("Benefit Category")) {
            showMessage("Don't forget to Select Benefit Category");
            isValid = false;
        }

        maritalString = mMaritalSpinnerS2.getSelectedItem().toString();
        if (maritalString.equals("Marital Status")) {

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
        Snackbar.make(mQbFormLayout2, s, Snackbar.LENGTH_SHORT).show();
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
