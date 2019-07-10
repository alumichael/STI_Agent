package com.example.sti_agent.operation_fragment.AllRisk;

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


public class AllriskFragment1 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAA1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /** ButterKnife Code **/
    @BindView(R.id.qb_form_layout1)
    FrameLayout mQbFormLayout1;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.type_spinner_a1)
    Spinner mTypeSpinnerA1;
    @BindView(R.id.prefix_spinner_a1)
    Spinner mPrefixSpinnerA1;
    @BindView(R.id.inputLayoutCompanyName_a1)
    TextInputLayout mInputLayoutCompanyNameA1;
    @BindView(R.id.companyname_editxt_a1)
    EditText mCompanynameEditxtA1;
    @BindView(R.id.inputLayoutTinNum_a1)
    TextInputLayout mInputLayoutTinNumA1;
    @BindView(R.id.tin_num_editxt_a1)
    EditText mTinNumEditxtA1;
    @BindView(R.id.inputLayoutOfficeAddr_a1)
    TextInputLayout mInputLayoutOfficeAddrA1;
    @BindView(R.id.office_addr_editxt_a1)
    EditText mOfficeAddrEditxtA1;
    @BindView(R.id.inputLayoutContactPerson_a1)
    TextInputLayout mInputLayoutContactPersonA1;
    @BindView(R.id.contact_person_editxt_a1)
    EditText mContactPersonEditxtA1;
    @BindView(R.id.inputLayoutFirstName_a1)
    TextInputLayout mInputLayoutFirstNameA1;
    @BindView(R.id.firstname_editxt_a1)
    EditText mFirstnameEditxtA1;
    @BindView(R.id.inputLayoutLastName_a1)
    TextInputLayout mInputLayoutLastNameA1;
    @BindView(R.id.lastname_editxt_a1)
    EditText mLastnameEditxtA1;
    @BindView(R.id.gender_spinner_a1)
    Spinner mGenderSpinnerA1;
    @BindView(R.id.inputLayoutResAddr_a1)
    TextInputLayout mInputLayoutResAddrA1;
    @BindView(R.id.residents_addr_editxt_a1)
    EditText mResidentsAddrEditxtA1;
    @BindView(R.id.inputLayoutNextKin_a1)
    TextInputLayout mInputLayoutNextKinA1;
    @BindView(R.id.next_kin_editxt_a1)
    EditText mNextKinEditxtA1;
    @BindView(R.id.inputLayoutPhone_a1)
    TextInputLayout mInputLayoutPhoneA1;
    @BindView(R.id.phone_no_editxt_a1)
    EditText mPhoneNoEditxtA1;
    @BindView(R.id.inputLayoutEmail_a1)
    TextInputLayout mInputLayoutEmailA1;
    @BindView(R.id.email_editxt_a1)
    EditText mEmailEditxtA1;
    @BindView(R.id.inputLayoutMailingAddr_a1)
    TextInputLayout mInputLayoutMailingAddrA1;
    @BindView(R.id.mail_addr_editxt_a1)
    EditText mMailAddrEditxtA1;
    @BindView(R.id.upload_img_btn1_a1)
    Button mUploadImgBtn1A1;
    @BindView(R.id.next_btn1_a1)
    Button mNextBtn1A1;
    @BindView(R.id.progressbar1_a1)
    AVLoadingIndicatorView mProgressbar1A1;


    String typeString,genderString,prifixString;
    private int currentStep = 0;



    public AllriskFragment1() {
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
    public static AllriskFragment1 newInstance(String param1, String param2) {
        AllriskFragment1 fragment = new AllriskFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAA1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAA1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_allrisk1, container, false);
        ButterKnife.bind(this,view);

        mStepView.go(currentStep, true);



        init();

        mTypeSpinnerA1();
        mPrefixSpinnerA1();
        mGenderSpinnerA1();
        setViewActions();

        return  view;
    }

    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mCompanynameEditxtA1.setText(userPreferences.getAllRiskICompanyName());

        mTinNumEditxtA1.setText(userPreferences.getAllRiskITinNumber());

        mOfficeAddrEditxtA1.setText(userPreferences.getAllRiskIOff_addr());


        mContactPersonEditxtA1.setText(userPreferences.getAllRiskIContPerson());

        mNextKinEditxtA1.setText(userPreferences.getAllRiskINextKin());

        mFirstnameEditxtA1.setText(userPreferences.getAllRiskIFirstName());

        mLastnameEditxtA1.setText(userPreferences.getAllRiskILastName());

        mResidentsAddrEditxtA1.setText(userPreferences.getAllRiskIResAdrr());
        mPhoneNoEditxtA1.setText(userPreferences.getAllRiskIPhoneNum());
        mEmailEditxtA1.setText(userPreferences.getAllRiskIEmail());
        mMailAddrEditxtA1.setText(userPreferences.getAllRiskIMailingAddr());

    }


    private void mTypeSpinnerA1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mTypeSpinnerA1.setAdapter(staticAdapter);

        mTypeSpinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                if(stringText.equals("Individual")){

                    mTypeSpinnerA1.setVisibility(View.VISIBLE);
                    mTypeSpinnerA1.setClickable(true);


                    //De-Visualizing the corporate form
                    mInputLayoutCompanyNameA1.setVisibility(View.GONE);
                    mCompanynameEditxtA1.setClickable(false);
                    mInputLayoutTinNumA1.setVisibility(View.GONE);
                    mTinNumEditxtA1.setClickable(false);
                    mInputLayoutOfficeAddrA1.setVisibility(View.GONE);
                    mOfficeAddrEditxtA1.setClickable(false);
                    mInputLayoutContactPersonA1.setVisibility(View.GONE);
                    mContactPersonEditxtA1.setClickable(false);

                    //Visualizing the individual form

                    mPrefixSpinnerA1.setVisibility(View.VISIBLE);
                    mPrefixSpinnerA1.setClickable(true);
                    mInputLayoutFirstNameA1.setVisibility(View.VISIBLE);
                    mFirstnameEditxtA1.setClickable(true);
                    mInputLayoutLastNameA1.setVisibility(View.VISIBLE);
                    mLastnameEditxtA1.setClickable(true);
                    mGenderSpinnerA1.setVisibility(View.VISIBLE);
                    mGenderSpinnerA1.setClickable(true);
                    mInputLayoutResAddrA1.setVisibility(View.VISIBLE);
                    mResidentsAddrEditxtA1.setClickable(true);
                    mInputLayoutNextKinA1.setVisibility(View.VISIBLE);
                    mNextKinEditxtA1.setClickable(true);


                }else if(stringText.equals("Corporate")){

                    //De-Visualizing the individual form
                    mPrefixSpinnerA1.setVisibility(View.GONE);
                    mPrefixSpinnerA1.setClickable(false);
                    mFirstnameEditxtA1.setVisibility(View.GONE);
                    mFirstnameEditxtA1.setClickable(false);
                    mInputLayoutLastNameA1.setVisibility(View.GONE);
                    mLastnameEditxtA1.setClickable(false);
                    mInputLayoutNextKinA1.setVisibility(View.GONE);
                    mNextKinEditxtA1.setClickable(false);
                    mGenderSpinnerA1.setVisibility(View.GONE);
                    mGenderSpinnerA1.setClickable(false);
                    mInputLayoutResAddrA1.setVisibility(View.GONE);
                    mResidentsAddrEditxtA1.setClickable(false);



                    //Visualizing the individual form
                    mTypeSpinnerA1.setVisibility(View.VISIBLE);
                    mTypeSpinnerA1.setClickable(true);
                    mInputLayoutCompanyNameA1.setVisibility(View.VISIBLE);
                    mCompanynameEditxtA1.setClickable(true);
                    mInputLayoutTinNumA1.setVisibility(View.VISIBLE);
                    mTinNumEditxtA1.setClickable(true);
                    mInputLayoutOfficeAddrA1.setVisibility(View.VISIBLE);
                    mOfficeAddrEditxtA1.setClickable(true);
                    mInputLayoutContactPersonA1.setVisibility(View.VISIBLE);
                    mContactPersonEditxtA1.setClickable(true);

                }else {

                    //De-Visualizing the individual form
                    mPrefixSpinnerA1.setVisibility(View.GONE);
                    mFirstnameEditxtA1.setVisibility(View.GONE);
                    mInputLayoutLastNameA1.setVisibility(View.GONE);
                    mGenderSpinnerA1.setVisibility(View.GONE);
                    mInputLayoutResAddrA1.setVisibility(View.GONE);
                    mInputLayoutCompanyNameA1.setVisibility(View.GONE);
                    mInputLayoutNextKinA1.setVisibility(View.GONE);
                    mInputLayoutTinNumA1.setVisibility(View.GONE);
                    mInputLayoutOfficeAddrA1.setVisibility(View.GONE);
                    mInputLayoutContactPersonA1.setVisibility(View.GONE);




                    //Visualizing the individual form
                    mTypeSpinnerA1.setVisibility(View.VISIBLE);
                    mInputLayoutPhoneA1.setVisibility(View.VISIBLE);
                    mInputLayoutEmailA1.setVisibility(View.VISIBLE);
                    mInputLayoutMailingAddrA1.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mTypeSpinnerA1.getItemAtPosition(0);
                mPrefixSpinnerA1.setVisibility(View.GONE);
                mFirstnameEditxtA1.setVisibility(View.GONE);
                mInputLayoutLastNameA1.setVisibility(View.GONE);
                mGenderSpinnerA1.setVisibility(View.GONE);
                mInputLayoutResAddrA1.setVisibility(View.GONE);
                mInputLayoutNextKinA1.setVisibility(View.GONE);
                mInputLayoutCompanyNameA1.setVisibility(View.GONE);
                mInputLayoutTinNumA1.setVisibility(View.GONE);
                mInputLayoutOfficeAddrA1.setVisibility(View.GONE);
                mInputLayoutContactPersonA1.setVisibility(View.GONE);




                //Visualizing the individual form
                mTypeSpinnerA1.setVisibility(View.VISIBLE);
                mInputLayoutPhoneA1.setVisibility(View.VISIBLE);
                mInputLayoutEmailA1.setVisibility(View.VISIBLE);
                mInputLayoutMailingAddrA1.setVisibility(View.VISIBLE);


            }
        });

    }

    private void mPrefixSpinnerA1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.prefix_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mPrefixSpinnerA1.setAdapter(staticAdapter);

        mPrefixSpinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String prefixText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPrefixSpinnerA1.getItemAtPosition(0);


            }
        });

    }
    private void mGenderSpinnerA1() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.gender_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mGenderSpinnerA1.setAdapter(staticAdapter);

        mGenderSpinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String genderText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mGenderSpinnerA1.getItemAtPosition(0);

            }
        });

    }

    //setting onclicks listeners
    private void setViewActions() {

        mNextBtn1A1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.next_btn1_a1:
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

        if (mFirstnameEditxtA1.getText().toString().isEmpty()&&mFirstnameEditxtA1.isClickable()) {
            mFirstnameEditxtA1.setError("Your FirstName is required!");
            isValid = false;
        } else if (mLastnameEditxtA1.getText().toString().isEmpty()&&mInputLayoutLastNameA1.isClickable()) {
            mInputLayoutLastNameA1.setError("Your LastName is required!");
            isValid = false;
        } else if (mCompanynameEditxtA1.getText().toString().isEmpty()&&mInputLayoutCompanyNameA1.isClickable()) {
            mInputLayoutCompanyNameA1.setError("Your Company Name is required!");
            isValid = false;
        } else if (mTinNumEditxtA1.getText().toString().isEmpty()&&mInputLayoutTinNumA1.isClickable()) {
            mInputLayoutTinNumA1.setError("Your TIN Number is required!");
            isValid = false;
        } else if (mOfficeAddrEditxtA1.getText().toString().isEmpty()&&mInputLayoutOfficeAddrA1.isClickable()) {
            mInputLayoutOfficeAddrA1.setError("Office Address is required!");
            isValid = false;
        }else if (mNextKinEditxtA1.getText().toString().isEmpty()&&mInputLayoutNextKinA1.isClickable()) {
            mInputLayoutNextKinA1.setError("Next of Kin is required!");
            isValid = false;
        }else {
            mInputLayoutFirstNameA1.setErrorEnabled(false);
            mInputLayoutLastNameA1.setErrorEnabled(false);
            mInputLayoutCompanyNameA1.setErrorEnabled(false);
            mInputLayoutTinNumA1.setErrorEnabled(false);
            mInputLayoutNextKinA1.setErrorEnabled(false);
            mInputLayoutOfficeAddrA1.setErrorEnabled(false);
        }

        if (mEmailEditxtA1.getText().toString().isEmpty()&&mInputLayoutEmailA1.isClickable()) {
            mInputLayoutEmailA1.setError("Email is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mEmailEditxtA1.getText().toString())) {
            mInputLayoutEmailA1.setError("Valid Email is required!");
            isValid = false;
        } else {
            mInputLayoutEmailA1.setErrorEnabled(false);
        }

        if (mMailAddrEditxtA1.getText().toString().isEmpty()&& mInputLayoutMailingAddrA1.isClickable()) {
            mInputLayoutMailingAddrA1.setError("Mailing Address is required!");
            isValid = false;
        } else if (!isValidEmailAddress(mMailAddrEditxtA1.getText().toString())&&mInputLayoutMailingAddrA1.isClickable()) {
            mInputLayoutMailingAddrA1.setError("Valid Mailing Address is required!");
            isValid = false;
        } else {
            mInputLayoutMailingAddrA1.setErrorEnabled(false);
        }


        if (mPhoneNoEditxtA1.getText().toString().isEmpty()&&mInputLayoutPhoneA1.isClickable()) {
            mInputLayoutPhoneA1.setError("Phone number is required");
            isValid = false;
        } else if (mPhoneNoEditxtA1.getText().toString().trim().length() < 11 && mInputLayoutPhoneA1.isClickable()) {
            mInputLayoutPhoneA1.setError("Your Phone number must be 11 in length");
            isValid = false;
        } else {
            mInputLayoutPhoneA1.setErrorEnabled(false);
        }

        if (mContactPersonEditxtA1.getText().toString().isEmpty()&&mInputLayoutContactPersonA1.isClickable()) {
            mInputLayoutContactPersonA1.setError("Contact Person is required");
            isValid = false;
        } else {
            mInputLayoutContactPersonA1.setErrorEnabled(false);
        }
        if (mResidentsAddrEditxtA1.getText().toString().isEmpty()&&mInputLayoutResAddrA1.isClickable()) {
            mInputLayoutResAddrA1.setError("Resident Address is required");
            isValid = false;
        } else {
            mInputLayoutResAddrA1.setErrorEnabled(false);
        }


        //Tyepe Spinner
        typeString = mTypeSpinnerA1.getSelectedItem().toString();
        if (typeString.equals("Type")&&mTypeSpinnerA1.isClickable()) {

            showMessage("Select Product Type");
            isValid = false;
        }
        //Prefix Spinner
        prifixString = mPrefixSpinnerA1.getSelectedItem().toString();
        if (prifixString.equals("Prefix")&&mPrefixSpinnerA1.isClickable()) {
            showMessage("Select your Prefix e.g Mr.");
            isValid = false;
        }

        genderString = mGenderSpinnerA1.getSelectedItem().toString();
        if (genderString.equals("Gender")&&mGenderSpinnerA1.isClickable()) {
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
        mNextBtn1A1.setVisibility(View.GONE);
        mProgressbar1A1.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setAllRiskPtype(typeString);
            userPreferences.setAllRiskIPrefix(prifixString);
            userPreferences.setAllRiskICompanyName(mCompanynameEditxtA1.getText().toString());
            userPreferences.setAllRiskITinNumber(mTinNumEditxtA1.getText().toString());
            userPreferences.setAllRiskIOff_addr(mOfficeAddrEditxtA1.getText().toString());
            userPreferences.setAllRiskINextKin(mNextKinEditxtA1.getText().toString());
            userPreferences.setAllRiskIContPerson(mContactPersonEditxtA1.getText().toString());
            userPreferences.setAllRiskIFirstName(mFirstnameEditxtA1.getText().toString());
            userPreferences.setAllRiskILastName(mLastnameEditxtA1.getText().toString());
            userPreferences.setAllRiskIGender(genderString);
            userPreferences.setAllRiskIResAdrr(mResidentsAddrEditxtA1.getText().toString());
            userPreferences.setAllRiskIPhoneNum(mPhoneNoEditxtA1.getText().toString());
            userPreferences.setAllRiskIEmail(mEmailEditxtA1.getText().toString());
            userPreferences.setAllRiskIMailingAddr(mMailAddrEditxtA1.getText().toString());

            Fragment allriskFragment2 = new AllriskFragment2();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_allrisk_form_container, allriskFragment2);
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1A1.setVisibility(View.GONE);
            mNextBtn1A1.setVisibility(View.VISIBLE);
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
