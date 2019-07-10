package com.example.sti_agent.operation_fragment.Marine;

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


public class MarineFragment1 extends Fragment implements View.OnClickListener{
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
    @BindView(R.id.type_spinner_m1)
    Spinner mTypeSpinnerM1;
    @BindView(R.id.prefix_spinner_m1)
    Spinner mPrefixSpinnerM1;
    @BindView(R.id.inputLayoutCompanyName_m1)
    TextInputLayout mInputLayoutCompanyNameM1;
    @BindView(R.id.companyname_editxt_m1)
    EditText mCompanynameEditxtM1;
    @BindView(R.id.inputLayoutTinNum_m1)
    TextInputLayout mInputLayoutTinNumM1;
    @BindView(R.id.tin_num_editxt_m1)
    EditText mTinNumEditxtM1;
    @BindView(R.id.inputLayoutOfficeAddr_m1)
    TextInputLayout mInputLayoutOfficeAddrM1;
    @BindView(R.id.office_addr_editxt_m1)
    EditText mOfficeAddrEditxtM1;
    @BindView(R.id.inputLayoutTrade_m1)
    TextInputLayout mInputLayoutTradeM1;
    @BindView(R.id.trade_editxt_m1)
    EditText mTradeEditxtM1;
    @BindView(R.id.inputLayoutContactPerson_m1)
    TextInputLayout mInputLayoutContactPersonM1;
    @BindView(R.id.contact_person_editxt_m1)
    EditText mContactPersonEditxtM1;
    @BindView(R.id.inputLayoutFirstName_m1)
    TextInputLayout mInputLayoutFirstNameM1;
    @BindView(R.id.firstname_editxt_m1)
    EditText mFirstnameEditxtM1;
    @BindView(R.id.inputLayoutLastName_m1)
    TextInputLayout mInputLayoutLastNameM1;
    @BindView(R.id.lastname_editxt_m1)
    EditText mLastnameEditxtM1;
    @BindView(R.id.gender_spinner_m1)
    Spinner mGenderSpinnerM1;
    @BindView(R.id.marital_spinner_m1)
    Spinner mMaritalSpinnerM1;
    @BindView(R.id.inputLayoutResAddr_m1)
    TextInputLayout mInputLayoutResAddrM1;
    @BindView(R.id.residents_addr_editxt_m1)
    EditText mResidentsAddrEditxtM1;
    @BindView(R.id.inputLayoutPhone_m1)
    TextInputLayout mInputLayoutPhoneM1;
    @BindView(R.id.phone_no_editxt_m1)
    EditText mPhoneNoEditxtM1;
    @BindView(R.id.inputLayoutEmail_m1)
    TextInputLayout mInputLayoutEmailM1;
    @BindView(R.id.email_editxt_m1)
    EditText mEmailEditxtM1;
    @BindView(R.id.inputLayoutMailingAddr_m1)
    TextInputLayout mInputLayoutMailingAddrM1;
    @BindView(R.id.mail_addr_editxt_m1)
    EditText mMailAddrEditxtM1;
    @BindView(R.id.next_btn1_m1)
    Button mNextBtn1M1;
    @BindView(R.id.progressbar1_m1)
    AVLoadingIndicatorView mProgressbar1M1;


    String typeString,genderString,prifixString,maritalString;
    private int currentStep = 0;
    


    public MarineFragment1() {
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
    public static MarineFragment1 newInstance(String param1, String param2) {
        MarineFragment1 fragment = new MarineFragment1();
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
        View view=inflater.inflate(R.layout.fragment_marine1, container, false);
        ButterKnife.bind(this,view);

        mStepView.go(currentStep, true);



        init();

        mTypeSpinnerM1();
        mPrefixSpinnerM1();
        mGenderSpinnerM1();
        maritalmTypeSpinnerM1();
        setViewActions();

        return  view;
    }

    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mCompanynameEditxtM1.setText(userPreferences.getMarineICompanyName());

        mTinNumEditxtM1.setText(userPreferences.getMarineITinNumber());

        mOfficeAddrEditxtM1.setText(userPreferences.getMarineIOff_addr());

        mTradeEditxtM1.setText(userPreferences.getMarineITrade());

        mContactPersonEditxtM1.setText(userPreferences.getMarineIContPerson());

        mFirstnameEditxtM1.setText(userPreferences.getMarineIFirstName());

        mLastnameEditxtM1.setText(userPreferences.getMarineILastName());

        mResidentsAddrEditxtM1.setText(userPreferences.getMarineIResAdrr());
        mPhoneNoEditxtM1.setText(userPreferences.getMarineIPhoneNum());
        mEmailEditxtM1.setText(userPreferences.getMarineIEmail());
        mMailAddrEditxtM1.setText(userPreferences.getMarineIMailingAddr());

    }


    private void maritalmTypeSpinnerM1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.marital_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mMaritalSpinnerM1.setAdapter(staticAdapter);

        mMaritalSpinnerM1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mMaritalSpinnerM1.getItemAtPosition(0);


            }
        });

    }

    private void mTypeSpinnerM1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mTypeSpinnerM1.setAdapter(staticAdapter);

        mTypeSpinnerM1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                if(stringText.equals("Individual")){

                    mTypeSpinnerM1.setVisibility(View.VISIBLE);
                    mTypeSpinnerM1.setClickable(true);


                    //De-Visualizing the corporate form
                    mInputLayoutCompanyNameM1.setVisibility(View.GONE);
                    mCompanynameEditxtM1.setClickable(false);
                    mInputLayoutTinNumM1.setVisibility(View.GONE);
                    mTinNumEditxtM1.setClickable(false);
                    mInputLayoutOfficeAddrM1.setVisibility(View.GONE);
                    mOfficeAddrEditxtM1.setClickable(false);
                    mInputLayoutTradeM1.setVisibility(View.GONE);
                    mTradeEditxtM1.setClickable(false);
                    mInputLayoutContactPersonM1.setVisibility(View.GONE);
                    mContactPersonEditxtM1.setClickable(false);

                    //Visualizing the individual form

                    mPrefixSpinnerM1.setVisibility(View.VISIBLE);
                    mPrefixSpinnerM1.setClickable(true);
                    mInputLayoutFirstNameM1.setVisibility(View.VISIBLE);
                    mFirstnameEditxtM1.setClickable(true);
                    mInputLayoutLastNameM1.setVisibility(View.VISIBLE);
                    mLastnameEditxtM1.setClickable(true);
                    mGenderSpinnerM1.setVisibility(View.VISIBLE);
                    mGenderSpinnerM1.setClickable(true);
                    mInputLayoutResAddrM1.setVisibility(View.VISIBLE);
                    mResidentsAddrEditxtM1.setClickable(true);
                   

                }else if(stringText.equals("Corporate")){

                    //De-Visualizing the individual form
                    mPrefixSpinnerM1.setVisibility(View.GONE);
                    mPrefixSpinnerM1.setClickable(false);
                    mFirstnameEditxtM1.setVisibility(View.GONE);
                    mFirstnameEditxtM1.setClickable(false);
                    mInputLayoutLastNameM1.setVisibility(View.GONE);
                    mLastnameEditxtM1.setClickable(false);
                    mGenderSpinnerM1.setVisibility(View.GONE);
                    mGenderSpinnerM1.setClickable(false);
                    mInputLayoutResAddrM1.setVisibility(View.GONE);
                    mResidentsAddrEditxtM1.setClickable(false);
               


                    //Visualizing the individual form
                    mTypeSpinnerM1.setVisibility(View.VISIBLE);
                    mTypeSpinnerM1.setClickable(true);
                    mInputLayoutCompanyNameM1.setVisibility(View.VISIBLE);
                    mCompanynameEditxtM1.setClickable(true);
                    mInputLayoutTinNumM1.setVisibility(View.VISIBLE);
                    mTinNumEditxtM1.setClickable(true);
                    mInputLayoutOfficeAddrM1.setVisibility(View.VISIBLE);
                    mOfficeAddrEditxtM1.setClickable(true);
                    mInputLayoutContactPersonM1.setVisibility(View.VISIBLE);
                    mContactPersonEditxtM1.setClickable(true);

                }else {

                    //De-Visualizing the individual form
                    mPrefixSpinnerM1.setVisibility(View.GONE);
                    mFirstnameEditxtM1.setVisibility(View.GONE);
                    mInputLayoutLastNameM1.setVisibility(View.GONE);
                    mGenderSpinnerM1.setVisibility(View.GONE);
                    mInputLayoutResAddrM1.setVisibility(View.GONE);
                    mInputLayoutCompanyNameM1.setVisibility(View.GONE);
                    mInputLayoutTinNumM1.setVisibility(View.GONE);
                    mInputLayoutOfficeAddrM1.setVisibility(View.GONE);
                    mInputLayoutContactPersonM1.setVisibility(View.GONE);




                    //Visualizing the individual form
                    mTypeSpinnerM1.setVisibility(View.VISIBLE);
                    mInputLayoutPhoneM1.setVisibility(View.VISIBLE);
                    mInputLayoutEmailM1.setVisibility(View.VISIBLE);
                    mInputLayoutMailingAddrM1.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mTypeSpinnerM1.getItemAtPosition(0);
                mPrefixSpinnerM1.setVisibility(View.GONE);
                mFirstnameEditxtM1.setVisibility(View.GONE);
                mInputLayoutLastNameM1.setVisibility(View.GONE);
                mGenderSpinnerM1.setVisibility(View.GONE);
                mInputLayoutResAddrM1.setVisibility(View.GONE);
                mInputLayoutCompanyNameM1.setVisibility(View.GONE);
                mInputLayoutTinNumM1.setVisibility(View.GONE);
                mInputLayoutOfficeAddrM1.setVisibility(View.GONE);
                mInputLayoutContactPersonM1.setVisibility(View.GONE);




                //Visualizing the individual form
                mTypeSpinnerM1.setVisibility(View.VISIBLE);
                mInputLayoutPhoneM1.setVisibility(View.VISIBLE);
                mInputLayoutEmailM1.setVisibility(View.VISIBLE);
                mInputLayoutMailingAddrM1.setVisibility(View.VISIBLE);


            }
        });

    }

    private void mPrefixSpinnerM1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.prefix_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mPrefixSpinnerM1.setAdapter(staticAdapter);

        mPrefixSpinnerM1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPrefixSpinnerM1.getItemAtPosition(0);


            }
        });

    }
    private void mGenderSpinnerM1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mGenderSpinnerM1.setAdapter(staticAdapter);

        mGenderSpinnerM1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mGenderSpinnerM1.getItemAtPosition(0);

            }
        });

    }

    //setting onclicks listeners
    private void setViewActions() {

        mNextBtn1M1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.next_btn1_m1:
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

            if (mFirstnameEditxtM1.getText().toString().isEmpty()&&mFirstnameEditxtM1.isClickable()) {
                mFirstnameEditxtM1.setError("Your FirstName is required!");
                isValid = false;
            } else if (mLastnameEditxtM1.getText().toString().isEmpty()&&mInputLayoutLastNameM1.isClickable()) {
                mInputLayoutLastNameM1.setError("Your LastName is required!");
                isValid = false;
            } else if (mCompanynameEditxtM1.getText().toString().isEmpty()&&mInputLayoutCompanyNameM1.isClickable()) {
                mInputLayoutCompanyNameM1.setError("Your Company Name is required!");
                isValid = false;
            } else if (mTinNumEditxtM1.getText().toString().isEmpty()&&mInputLayoutTinNumM1.isClickable()) {
                mInputLayoutTinNumM1.setError("Your TIN Number is required!");
                isValid = false;
            } else if (mOfficeAddrEditxtM1.getText().toString().isEmpty()&&mInputLayoutOfficeAddrM1.isClickable()) {
                mInputLayoutOfficeAddrM1.setError("Office Address is required!");
                isValid = false;
            }else if (mTradeEditxtM1.getText().toString().isEmpty()&&mInputLayoutTradeM1.isClickable()) {
                mInputLayoutTradeM1.setError("Office Address is required!");
                isValid = false;
            }else {
                mInputLayoutFirstNameM1.setErrorEnabled(false);
                mInputLayoutLastNameM1.setErrorEnabled(false);
                mInputLayoutCompanyNameM1.setErrorEnabled(false);
                mInputLayoutTinNumM1.setErrorEnabled(false);
                mInputLayoutOfficeAddrM1.setErrorEnabled(false);
            }

            if (mEmailEditxtM1.getText().toString().isEmpty()&&mInputLayoutEmailM1.isClickable()) {
                mInputLayoutEmailM1.setError("Email is required!");
                isValid = false;
            } else if (!isValidEmailAddress(mEmailEditxtM1.getText().toString())) {
                mInputLayoutEmailM1.setError("Valid Email is required!");
                isValid = false;
            } else {
                mInputLayoutEmailM1.setErrorEnabled(false);
            }

            if (mMailAddrEditxtM1.getText().toString().isEmpty()&& mInputLayoutMailingAddrM1.isClickable()) {
                mInputLayoutMailingAddrM1.setError("Mailing Address is required!");
                isValid = false;
            } else if (!isValidEmailAddress(mMailAddrEditxtM1.getText().toString())&&mInputLayoutMailingAddrM1.isClickable()) {
                mInputLayoutMailingAddrM1.setError("Valid Mailing Address is required!");
                isValid = false;
            } else {
                mInputLayoutMailingAddrM1.setErrorEnabled(false);
            }


            if (mPhoneNoEditxtM1.getText().toString().isEmpty()&&mInputLayoutPhoneM1.isClickable()) {
                mInputLayoutPhoneM1.setError("Phone number is required");
                isValid = false;
            } else if (mPhoneNoEditxtM1.getText().toString().trim().length() < 11 && mInputLayoutPhoneM1.isClickable()) {
                mInputLayoutPhoneM1.setError("Your Phone number must be 11 in length");
                isValid = false;
            } else {
                mInputLayoutPhoneM1.setErrorEnabled(false);
            }

            if (mContactPersonEditxtM1.getText().toString().isEmpty()&&mInputLayoutContactPersonM1.isClickable()) {
                mInputLayoutContactPersonM1.setError("Contact Person is required");
                isValid = false;
            } else {
                mInputLayoutContactPersonM1.setErrorEnabled(false);
            }
            if (mResidentsAddrEditxtM1.getText().toString().isEmpty()&&mInputLayoutResAddrM1.isClickable()) {
                mInputLayoutResAddrM1.setError("Resident Address is required");
                isValid = false;
            } else {
                mInputLayoutResAddrM1.setErrorEnabled(false);
            }


            //Tyepe Spinner
            typeString = mTypeSpinnerM1.getSelectedItem().toString();
            if (typeString.equals("Type")&&mTypeSpinnerM1.isClickable()) {

                showMessage("Select Product Type");
                isValid = false;
            }
            //Prefix Spinner
            prifixString = mPrefixSpinnerM1.getSelectedItem().toString();
            if (prifixString.equals("Prefix")&&mPrefixSpinnerM1.isClickable()) {
                showMessage("Select your Prefix e.g Mr.");
                isValid = false;
            }

            genderString = mGenderSpinnerM1.getSelectedItem().toString();
            if (genderString.equals("Gender")&&mGenderSpinnerM1.isClickable()) {
                showMessage("Don't forget to Select Gender");
                isValid = false;
            }
            maritalString = mMaritalSpinnerM1.getSelectedItem().toString();
            if (maritalString.equals("Marital Status")&&mMaritalSpinnerM1.isClickable()) {
                showMessage("Don't forget to Select Marital Status");
                isValid = false;
            }

            if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
                initFragment();
            }




    }

    private void initFragment() {
        mNextBtn1M1.setVisibility(View.GONE);
        mProgressbar1M1.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setMarinePtype(typeString);
            userPreferences.setMarineIPrefix(prifixString);
            userPreferences.setMarineICompanyName(mCompanynameEditxtM1.getText().toString());
            userPreferences.setMarineITinNumber(mTinNumEditxtM1.getText().toString());
            userPreferences.setMarineIOff_addr(mOfficeAddrEditxtM1.getText().toString());
            userPreferences.setMarineITrade(mTradeEditxtM1.getText().toString());
            userPreferences.setMarineIContPerson(mContactPersonEditxtM1.getText().toString());
            userPreferences.setMarineIFirstName(mFirstnameEditxtM1.getText().toString());
            userPreferences.setMarineILastName(mLastnameEditxtM1.getText().toString());
            userPreferences.setMarineIGender(genderString);
            userPreferences.setMarineIMaritalStatus(maritalString);
            userPreferences.setMarineIResAdrr(mResidentsAddrEditxtM1.getText().toString());
            userPreferences.setMarineIPhoneNum(mPhoneNoEditxtM1.getText().toString());
            userPreferences.setMarineIEmail(mEmailEditxtM1.getText().toString());
            userPreferences.setMarineIMailingAddr(mMailAddrEditxtM1.getText().toString());

            Fragment marineFragment2 = new MarineFragment2();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_marine_form_container, marineFragment2);
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1M1.setVisibility(View.GONE);
            mNextBtn1M1.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout1, s, Snackbar.LENGTH_SHORT).show();
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
