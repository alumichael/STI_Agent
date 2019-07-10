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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MarineFragment2 extends Fragment implements View.OnClickListener{
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
    @BindView(R.id.inputLayoutProfInvoice_m2)
    TextInputLayout mInputLayoutProfInvoiceM2;
    @BindView(R.id.prof_invoice_txt_m2)
    EditText mProfInvoiceTxtM2;
    @BindView(R.id.inputLayoutProfInvoiceDate_m2)
    TextInputLayout mInputLayoutProfInvoiceDateM2;
    @BindView(R.id.prof_invoice_date_txt_m2)
    EditText mProfInvoiceDateTxtM2;
    @BindView(R.id.inputLayoutDescGoods_m2)
    TextInputLayout mInputLayoutDescGoodsM2;
    @BindView(R.id.desc_goods_m2)
    EditText mDescGoodsM2;
    @BindView(R.id.inputLayoutInterest_m2)
    TextInputLayout mInputLayoutInterestM2;
    @BindView(R.id.interest_txt_m2)
    EditText mInterestTxtM2;
    @BindView(R.id.inputLayoutQuantity_m2)
    TextInputLayout mInputLayoutQuantityM2;
    @BindView(R.id.quantity_txt_m2)
    EditText mQuantityTxtM2;
    @BindView(R.id.currency_spinner_m2)
    Spinner mCurrencySpinnerM2;
    @BindView(R.id.inputLayoutTotalAmt)
    TextInputLayout mInputLayoutTotalAmt;
    @BindView(R.id.total_amt_txt_m2)
    EditText mTotalAmtTxtM2;
    @BindView(R.id.inputLayoutConversionRate)
    TextInputLayout mInputLayoutConversionRate;
    @BindView(R.id.coversion_rate_txt_m2)
    EditText mCoversionRateTxtM2;
    @BindView(R.id.inputLayoutPortOfLoad)
    TextInputLayout mInputLayoutPortOfLoad;
    @BindView(R.id.portofload_txt_m2)
    EditText mPortofloadTxtM2;
    @BindView(R.id.inputLayoutPortDischarge_m2)
    TextInputLayout mInputLayoutPortDischargeM2;
    @BindView(R.id.portdischarge_txt_m2)
    EditText mPortdischargeTxtM2;
    @BindView(R.id.modeOfConvey_spinner_m2)
    Spinner mModeOfConveySpinnerM2;
    @BindView(R.id.upload_prof_doc_btn_m2)
    Button mUploadProfDocBtnM2;
    @BindView(R.id.btn_layout2_m2)
    LinearLayout mBtnLayout2M2;
    @BindView(R.id.v_back_btn2_m2)
    Button mVBackBtn2M2;
    @BindView(R.id.v_next_btn2_m2)
    Button mVNextBtn2M2;
    @BindView(R.id.progressbar2_m2)
    AVLoadingIndicatorView mProgressbar2M2;
    
   

    private int currentStep = 1;
    String currencyString,conveyString;




    public MarineFragment2() {
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
    public static MarineFragment2 newInstance(String param1, String param2) {
        MarineFragment2 fragment = new MarineFragment2();
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
        View view=inflater.inflate(R.layout.fragment_marine2, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step

        mStepView.go(currentStep, true);

        init();


        currencySpinner();
        conveySpinner();
        setViewActions();

        return  view;
    }


    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        mProfInvoiceTxtM2.setText(userPreferences.getMarineIProfInvNO());

        mProfInvoiceDateTxtM2.setText(userPreferences.getMarineIDateProfInv());

        mDescGoodsM2.setText(userPreferences.getMarineIDescOfGoods());

        mInterestTxtM2.setText(userPreferences.getMarineIIntetrest());

        mQuantityTxtM2.setText(userPreferences.getMarineIQuantity());

        mTotalAmtTxtM2.setText(userPreferences.getMarineITotalAmount());

        mCoversionRateTxtM2.setText(userPreferences.getMarineINairaConvert());
        mPortofloadTxtM2.setText(userPreferences.getMarineIPortOfLoading());
        mPortdischargeTxtM2.setText(userPreferences.getMarineIPortOfDischarge());



    }



    private void currencySpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.currency_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mCurrencySpinnerM2.setAdapter(staticAdapter);

        mCurrencySpinnerM2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String currencyString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCurrencySpinnerM2.getItemAtPosition(0);
            }
        });

    }


    private void conveySpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.convey_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mModeOfConveySpinnerM2.setAdapter(staticAdapter);

        mModeOfConveySpinnerM2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String conveyString = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mModeOfConveySpinnerM2.getItemAtPosition(0);
            }
        });

    }

    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn2M2.setOnClickListener(this);
        mVBackBtn2M2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2_m2:
//                validate user input
                validateUserInputs();
                break;

            case R.id.v_back_btn2_m2:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment marineFragment1 = new MarineFragment1();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_marine_form_container, marineFragment1);
                ft.commit();

                break;
        }
    }

    private void validateUserInputs() {


        boolean isValid = true;

        if (mProfInvoiceTxtM2.getText().toString().isEmpty()) {
            mInputLayoutProfInvoiceM2.setError("Your Proforma Invoice Number is required!");
            isValid = false;
        } else if (mProfInvoiceDateTxtM2.getText().toString().isEmpty()) {
            mProfInvoiceDateTxtM2.setError("Your Proforma Invoice date is required!");
            isValid = false;
        } else if (mDescGoodsM2.getText().toString().isEmpty()) {
            mInputLayoutDescGoodsM2.setError("Your Goods description is required!");

            isValid = false;
        } else if (mInterestTxtM2.getText().toString().isEmpty()) {
            mInputLayoutInterestM2.setError("Interest is required!");
            isValid = false;
        } else if (mQuantityTxtM2.getText().toString().isEmpty()) {
            mInputLayoutQuantityM2.setError("Quantity is required!");

            isValid = false;
        }else if (mTotalAmtTxtM2.getText().toString().isEmpty()) {
            mInputLayoutTotalAmt.setError("Total Amount or Value on Proforma invoice is required!");
            isValid = false;
        }else {
            mInputLayoutProfInvoiceM2.setErrorEnabled(false);
            mInputLayoutTotalAmt.setErrorEnabled(false);
            mInputLayoutDescGoodsM2.setErrorEnabled(false);
            mInputLayoutDescGoodsM2.setErrorEnabled(false);
            mInputLayoutInterestM2.setErrorEnabled(false);
            mInputLayoutQuantityM2.setErrorEnabled(false);
        }

        if (mCoversionRateTxtM2.getText().toString().isEmpty()) {
            mInputLayoutConversionRate.setError("Conversion rate is required!");

            isValid = false;
        }else {
            mInputLayoutConversionRate.setErrorEnabled(false);
        }

        if (mPortofloadTxtM2.getText().toString().isEmpty()) {
            mInputLayoutProfInvoiceM2.setError("Port of Loading is required!");

            isValid = false;
        }else {
            mInputLayoutProfInvoiceM2.setErrorEnabled(false);
        }

        if (mPortdischargeTxtM2.getText().toString().isEmpty()) {
            mInputLayoutPortDischargeM2.setError("Port of Discharge is required!");

            isValid = false;
        }else {
            mInputLayoutPortDischargeM2.setErrorEnabled(false);
        }


        // Spinner Validations
        //currency spinner
        currencyString = mCurrencySpinnerM2.getSelectedItem().toString();
        if (currencyString.equals("Currency")) {
            showMessage("Select Currency Type");
            isValid = false;
        }
        //mode of convey Spinner
        conveyString = mModeOfConveySpinnerM2.getSelectedItem().toString();
        if (conveyString.equals("Mode of Conveyance")) {
            showMessage("Select your Mode of Conveyance");
            isValid = false;
        }




        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }




    }

    private void initFragment() {
        mBtnLayout2M2.setVisibility(View.GONE);
        mProgressbar2M2.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setMarineIProfInvNO(mProfInvoiceTxtM2.getText().toString());
            userPreferences.setMarineICurrency(currencyString);
            userPreferences.setMarineIModeOfConvey(conveyString);
            userPreferences.setMarineIDateProfInv(mProfInvoiceDateTxtM2.getText().toString());
            userPreferences.setMarineIDescOfGoods(mDescGoodsM2.getText().toString());
            userPreferences.setMarineIIntetrest(mInterestTxtM2.getText().toString());
            userPreferences.setMarineIQuantity(mQuantityTxtM2.getText().toString());
            userPreferences.setMarineITotalAmount(mTotalAmtTxtM2.getText().toString());
            userPreferences.setMarineINairaConvert(mCoversionRateTxtM2.getText().toString());
            userPreferences.setMarineIPortOfLoading(mPortofloadTxtM2.getText().toString());
            userPreferences.setMarineIPortOfDischarge(mPortdischargeTxtM2.getText().toString());





           // Fragment quoteBuyFragment3 = new MarineInsureFragment3();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_marine_form_container, MarineFragment3.newInstance("Premium","4000"), MarineFragment3.class.getSimpleName());
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar2M2.setVisibility(View.GONE);
            mVNextBtn2M2.setVisibility(View.VISIBLE);
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
