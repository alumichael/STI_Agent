package com.example.sti_agent.operation_fragment.Etic;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.operation_fragment.Etic.EticFragment1;
import com.example.sti_agent.operation_fragment.Etic.EticFragment2;
import com.example.sti_agent.operation_fragment.Etic.EticFragment3;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment1;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment3;
import com.google.android.material.snackbar.Snackbar;


import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EticFragment2 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAE2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.qb_form_layout2)
    FrameLayout mQbFormLayout2;
    @BindView(R.id.step_view)
    com.shuhart.stepview.StepView mStepView;
    @BindView(R.id.inputLayoutTripDuratn_e2)
    TextInputLayout mInputLayoutTripDuratnE2;
    @BindView(R.id.trip_duration_e2)
    EditText mTripDurationE2;
    @BindView(R.id.inputLayoutStartDate_e2)
    TextInputLayout mInputLayoutStartDateE2;
    @BindView(R.id.start_date_e2)
    EditText mStartDateE2;
    @BindView(R.id.inputLayoutTravelMode_e2)
    TextInputLayout mInputLayoutTravelModeE2;
    @BindView(R.id.travel_mode_e2)
    EditText mTravelModeE2;
    @BindView(R.id.disability_spinner_e2)
    Spinner mDisabilitySpinnerE2;
    @BindView(R.id.inputLayoutDisabilityDetail_e2)
    TextInputLayout mInputLayoutDisabilityDetailE2;
    @BindView(R.id.disable_detail_e2)
    EditText mDisableDetailE2;
    @BindView(R.id.inputLayoutDeptPlace_e2)
    TextInputLayout mInputLayoutDeptPlaceE2;
    @BindView(R.id.dept_place_txt_e2)
    EditText mDeptPlaceTxtE2;
    @BindView(R.id.inputLayoutArivalPlace_e2)
    TextInputLayout mInputLayoutArivalPlaceE2;
    @BindView(R.id.arrival_place_txt_e2)
    EditText mArrivalPlaceTxtE2;

    @BindView(R.id.inputLayoutCountryVisitAddr_e2)
    TextInputLayout mInputLayoutCountryVisitAddrE2;
    @BindView(R.id.countryVisit_addr_txt_e2)
    EditText mCountryVisitAddrE2;
    
    
    @BindView(R.id.btn_layout2_e2)
    LinearLayout mBtnLayout2E2;
    @BindView(R.id.v_back_btn2_e2)
    Button mVBackBtn2E2;
    @BindView(R.id.v_next_btn2_e2)
    Button mVNextBtn2E2;
    @BindView(R.id.progressbar2_e2)
    AVLoadingIndicatorView mProgressbar2E2;
    

    private int currentStep = 1;

    String disabilityString,startDateStrg;
    DatePickerDialog datePickerDialog1;




    

    public EticFragment2() {
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
    public static EticFragment2 newInstance(String param1, String param2) {
        EticFragment2 fragment = new EticFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAE2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAE2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_etic2, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step

        mStepView.go(currentStep, true);

        init();

        disabilitytypeSpinner();
        setViewActions();
        showDatePicker();

        return  view;
    }


    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mTripDurationE2.setText(userPreferences.getEticITripDuration());

        mStartDateE2.setText(userPreferences.getEticStartDate());

        mTravelModeE2.setText(userPreferences.getEticITravelMode());

        mDisableDetailE2.setText(userPreferences.getEticIDisabilityDetail());

        mDeptPlaceTxtE2.setText(userPreferences.getEticIDeparturePlc());

        mArrivalPlaceTxtE2.setText(userPreferences.getEticIArrivalPlc());

        mCountryVisitAddrE2.setText(userPreferences.getEticICountryOfVisit());
        
    }
    
    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn2E2.setOnClickListener(this);
        mVBackBtn2E2.setOnClickListener(this);
        mStartDateE2.setOnClickListener(this);

    }


    private void disabilitytypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.disable_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mDisabilitySpinnerE2.setAdapter(staticAdapter);

        mDisabilitySpinnerE2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mDisabilitySpinnerE2.getItemAtPosition(0);

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2_e2:
//                validate user input
                validateUserInputs();
                break;

            case R.id.start_date_e2:
//                validate user input
                datePickerDialog1.show();
                break;

            case R.id.v_back_btn2_e2:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment eticFragment1 = new EticFragment1();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_etic_form_container, eticFragment1);
                ft.commit();

                break;
        }
    }

    private void validateUserInputs() {


        boolean isValid = true;

        if (mTripDurationE2.getText().toString().isEmpty()) {
            mInputLayoutTripDuratnE2.setError("Trip Duration is required!");
            isValid = false;
        } else if (mStartDateE2.getText().toString().isEmpty()) {
            mInputLayoutStartDateE2.setError("Start Date is required!");
            isValid = false;
        } else if (mTravelModeE2.getText().toString().isEmpty()) {
            mInputLayoutTravelModeE2.setError("Travel Mode is required!");

            isValid = false;
        } else if (mDisableDetailE2.getText().toString().isEmpty()) {
            mInputLayoutDisabilityDetailE2.setError("Diasability Detail is required!");
            isValid = false;
        } else if (mDeptPlaceTxtE2.getText().toString().isEmpty()) {
            mInputLayoutDeptPlaceE2.setError("Departure Place is required!");

            isValid = false;
        }else if (mCountryVisitAddrE2.getText().toString().isEmpty()) {
            mInputLayoutCountryVisitAddrE2.setError("Country of Visit is required!");
            isValid = false;
        }else {
            mInputLayoutCountryVisitAddrE2.setErrorEnabled(false);
            mInputLayoutDeptPlaceE2.setErrorEnabled(false);
            mInputLayoutDisabilityDetailE2.setErrorEnabled(false);
            mInputLayoutStartDateE2.setErrorEnabled(false);
            mInputLayoutTravelModeE2.setErrorEnabled(false);
            mInputLayoutTripDuratnE2.setErrorEnabled(false);
        }

        if (mArrivalPlaceTxtE2.getText().toString().isEmpty()) {
            mInputLayoutArivalPlaceE2.setError("Arrival Place is required!");

            isValid = false;
        }else {
            mInputLayoutArivalPlaceE2.setErrorEnabled(false);
        }
                // Spinner Validations

        disabilityString = mDisabilitySpinnerE2.getSelectedItem().toString();
        if (disabilityString.equals("Select Disability")) {
            showMessage("Select Yes or No for disability");
            isValid = false;
        }

        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




    }

    private void initFragment() {
        mBtnLayout2E2.setVisibility(View.GONE);
        mProgressbar2E2.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setEticITripDuration(mTripDurationE2.getText().toString());
            userPreferences.setEticIDisability(disabilityString);
            userPreferences.setEticStartDate(mStartDateE2.getText().toString());
            userPreferences.setEticITravelMode(mTravelModeE2.getText().toString());
            userPreferences.setEticIDisabilityDetail(mDisableDetailE2.getText().toString());
            userPreferences.setEticIDeparturePlc(mDeptPlaceTxtE2.getText().toString());
            userPreferences.setEticIArrivalPlc(mArrivalPlaceTxtE2.getText().toString());
            userPreferences.setEticICountryOfVisit(mCountryVisitAddrE2.getText().toString());


            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_etic_form_container, EticFragment3.newInstance("Premium","8000"), EticFragment3.class.getSimpleName());
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar2E2.setVisibility(View.GONE);
            mVNextBtn2E2.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }
    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout2, s, Snackbar.LENGTH_SHORT).show();
    }



    private void showDatePicker() {
        //Get current date
        Calendar calendar = Calendar.getInstance();

        //Create datePickerDialog with initial date which is current and decide what happens when a date is selected.
        datePickerDialog1 = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //When a date is selected, it comes here.
                if(year<calendar.get(Calendar.YEAR)){

                    showMessage("Invalid Start Date");
                    Log.i("Calendar",year+" "+calendar.get(Calendar.YEAR));
                    return;
                }
                int monthofYear=monthOfYear+1;
                startDateStrg = dayOfMonth + "-" + monthofYear + "-" + year;
                mStartDateE2.setText(startDateStrg);
                datePickerDialog1.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


}
