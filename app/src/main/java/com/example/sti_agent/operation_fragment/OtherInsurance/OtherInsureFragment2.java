package com.example.sti_agent.operation_fragment.OtherInsurance;

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

import butterknife.BindView;
import butterknife.ButterKnife;


public class OtherInsureFragment2 extends Fragment implements View.OnClickListener{
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
    @BindView(R.id.select_prod_type_spinner_o2)
    Spinner mSelectProdTypeSpinnerO2;
    @BindView(R.id.inputLayoutDescItem_o2)
    TextInputLayout mInputLayoutDescItemO2;
    @BindView(R.id.desc_item_txt_o2)
    EditText mDescItemTxtO2;
    @BindView(R.id.btn_layout2)
    LinearLayout mBtnLayout2;
    @BindView(R.id.v_back_btn2_o2)
    Button mVBackBtn2O2;
    @BindView(R.id.v_next_btn2_o2)
    Button mVNextBtn2O2;
    @BindView(R.id.progressbar2_o2)
    AVLoadingIndicatorView mProgressbar2O2;


    private int currentStep = 1;

    

    String productTypeString;

    public OtherInsureFragment2() {
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
    public static OtherInsureFragment2 newInstance(String param1, String param2) {
        OtherInsureFragment2 fragment = new OtherInsureFragment2();
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
        View view=inflater.inflate(R.layout.fragment_other2, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step


        mStepView.go(currentStep, true);

        init();

        producttypeSpinner();


        setViewActions();

        return  view;
    }


    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mDescItemTxtO2.setText(userPreferences.getOtherDescItem());

    }


    private void producttypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.product_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSelectProdTypeSpinnerO2.setAdapter(staticAdapter);

        mSelectProdTypeSpinnerO2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSelectProdTypeSpinnerO2.getItemAtPosition(0);



            }
        });

    }


    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn2O2.setOnClickListener(this);
        mVBackBtn2O2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2_o2:
//                validate user input
                validateUserInputs();
                break;

            case R.id.v_back_btn2_o2:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment otherInsureFragment2 = new OtherInsureFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_other_form_container, otherInsureFragment2);
                ft.commit();

                break;
        }
    }

    private void validateUserInputs() {


        boolean isValid = true;

        if (mDescItemTxtO2.getText().toString().isEmpty()) {
            mInputLayoutDescItemO2.setError("Your Product Description is required!");

            isValid = false;
        } else {
            mInputLayoutDescItemO2.setErrorEnabled(false);
           
        }

        productTypeString = mSelectProdTypeSpinnerO2.getSelectedItem().toString();
        if (productTypeString.equals("Select Item")) {
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
        mBtnLayout2.setVisibility(View.GONE);
        mProgressbar2O2.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setOtherDescItem(mDescItemTxtO2.getText().toString());
            userPreferences.setOtherProductType(productTypeString);
            // Fragment quoteBuyFragment3 = new MotorInsureFragment3();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_other_form_container, OtherInsureFragment3.newInstance(userPreferences.getOtherProductType(),"8000"), OtherInsureFragment3.class.getSimpleName());
            ft.commit();


        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar2O2.setVisibility(View.GONE);
            mVNextBtn2O2.setVisibility(View.VISIBLE);
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
