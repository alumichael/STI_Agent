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
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment1;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment3;
import com.google.android.material.snackbar.Snackbar;

import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllriskFragment2 extends Fragment implements View.OnClickListener{
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
    com.shuhart.stepview.StepView mStepView;
    @BindView(R.id.item_type_spinner_a2)
    Spinner mItemTypeSpinnerA2;
    @BindView(R.id.inputLayoutItemDescriptn_a2)
    TextInputLayout mInputLayoutItemDescriptnA2;
    @BindView(R.id.item_desc_a2)
    EditText mItemDescA2;
    @BindView(R.id.inputLayoutStartDate_a2)
    TextInputLayout mInputLayoutStartDateA2;
    @BindView(R.id.start_date_a2)
    EditText mStartDateA2;
    @BindView(R.id.inputLayoutSerialNo_a2)
    TextInputLayout mInputLayoutSerialNoA2;
    @BindView(R.id.serial_num_a2)
    EditText mSerialNumA2;
    @BindView(R.id.inputLayoutItemValue_a2)
    TextInputLayout mInputLayoutItemValueA2;
    @BindView(R.id.item_value_a2)
    EditText mItemValueA2;
    @BindView(R.id.btn_layout2_a2)
    LinearLayout mBtnLayout2A2;
    @BindView(R.id.v_back_btn2_a2)
    Button mVBackBtn2A2;
    @BindView(R.id.v_next_btn2_a2)
    Button mVNextBtn2A2;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView mProgressbar;


    private int currentStep = 1;

 

    String itemTypeString;

    public AllriskFragment2() {
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
    public static AllriskFragment2 newInstance(String param1, String param2) {
        AllriskFragment2 fragment = new AllriskFragment2();
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
        View view=inflater.inflate(R.layout.fragment_allrisk2, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step
        

        mStepView.go(currentStep, true);

        init();

        itemtypeSpinner();


        setViewActions();

        return  view;
    }


    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mItemDescA2.setText(userPreferences.getAllRiskItemDesc());

        mStartDateA2.setText(userPreferences.getAllRiskStartDate());

        mSerialNumA2.setText(userPreferences.getAllRiskSerialNo());

        mItemValueA2.setText(userPreferences.getAllRiskItemValue());


    }


    private void itemtypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.select_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mItemTypeSpinnerA2.setAdapter(staticAdapter);

        mItemTypeSpinnerA2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mItemTypeSpinnerA2.getItemAtPosition(0);



            }
        });

    }


    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn2A2.setOnClickListener(this);
        mVBackBtn2A2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2_a2:
//                validate user input
                validateUserInputs();
                break;

            case R.id.v_back_btn2_a2:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment allriskFragment2 = new AllriskFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_allrisk_form_container, allriskFragment2);
                ft.commit();

                break;
        }
    }

    private void validateUserInputs() {


        boolean isValid = true;

        if (mItemDescA2.getText().toString().isEmpty()) {
            mInputLayoutItemDescriptnA2.setError("Your Item Description is required!");

            isValid = false;
        } else if (mStartDateA2.getText().toString().isEmpty()) {
            mInputLayoutStartDateA2.setError("Start Date is required!");

            isValid = false;
        } else if (mSerialNumA2.getText().toString().isEmpty()) {
            mInputLayoutSerialNoA2.setError("Serial Number is required!");
            isValid = false;
        } else if (mItemValueA2.getText().toString().isEmpty()) {
            mInputLayoutItemValueA2.setError("Chasis Number is required!");

            isValid = false;
        }else {
            mInputLayoutItemValueA2.setErrorEnabled(false);
            mInputLayoutSerialNoA2.setErrorEnabled(false);
            mInputLayoutStartDateA2.setErrorEnabled(false);
            mInputLayoutItemDescriptnA2.setErrorEnabled(false);

        }
        itemTypeString = mItemTypeSpinnerA2.getSelectedItem().toString();
        if (itemTypeString.equals("Select Item")) {
            showMessage("Select Item");
            isValid = false;
        }


        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




    }

    private void initFragment() {
        mBtnLayout2A2.setVisibility(View.GONE);
        mProgressbar.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setAllRiskItemDesc(mItemDescA2.getText().toString());
            userPreferences.setAllRiskStartDate(mStartDateA2.getText().toString());

            userPreferences.setAllRiskItemType(itemTypeString);
            userPreferences.setAllRiskSerialNo(mSerialNumA2.getText().toString());
            userPreferences.setAllRiskItemValue(mItemValueA2.getText().toString());

           // Fragment quoteBuyFragment3 = new MotorInsureFragment3();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_allrisk_form_container, AllriskFragment3.newInstance(userPreferences.getAllRiskItemType(),"8000"), AllriskFragment3.class.getSimpleName());
            ft.commit();


        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar.setVisibility(View.GONE);
            mVNextBtn2A2.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }
    }


    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout2, s, Snackbar.LENGTH_SHORT).show();
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
