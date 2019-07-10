package com.example.sti_agent.operation_fragment.Claim;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.operation_fragment.Swiss.SwissFragment2;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SubFragment_Claim extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.claim_form_layout1)
    FrameLayout mClaimFormLayout1;
    @BindView(R.id.claim_type_spinner)
    Spinner mClaimTypeSpinner;
    @BindView(R.id.polynum_type_spinner)
    Spinner mPolynumTypeSpinner;
    @BindView(R.id.inputLayoutLastDescClaim)
    TextInputLayout mInputLayoutLastDescClaim;
    @BindView(R.id.descclaim_editxt_s1)
    EditText mDescclaimEditxtS1;
    @BindView(R.id.inputLayoutDateofLoss)
    TextInputLayout mInputLayoutDateofLoss;
    @BindView(R.id.lossde_editxt_s1)
    EditText mLossdeEditxtS1;
    @BindView(R.id.sti_est_spinner)
    Spinner mStiEstSpinner;
    @BindView(R.id.inputLayoutEstimateCost)
    TextInputLayout mInputLayoutEstimateCost;
    @BindView(R.id.est_cost_editxt_s1)
    EditText mEstCostEditxtS1;
    @BindView(R.id.inputLayoutPin)
    TextInputLayout mInputLayoutPin;
    @BindView(R.id.pin_editxt)
    EditText mPinEditxt;
    @BindView(R.id.upload_estimate_cost)
    Button mUploadEstimateCost;
    @BindView(R.id.upload_damage_pix)
    Button mUploadDamagePix;
    @BindView(R.id.upload_document)
    Button mUploadDocument;
    @BindView(R.id.proceed)
    Button mProceed;
    @BindView(R.id.progressbar1_s1)
    AVLoadingIndicatorView mProgressbar1S1;



    String claimTypeString,policyTypeString,stiEstTypeString,pinString;
    private int currentStep = 0;


    public SubFragment_Claim() {
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
    public static SubFragment_Claim newInstance(String param1, String param2) {
        SubFragment_Claim fragment = new SubFragment_Claim();
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
        View view=inflater.inflate(R.layout.fragment_sub_claim, container, false);
        ButterKnife.bind(this,view);


        ArrayList<String> policies = new ArrayList<String>(
                Arrays.asList("Policy Number","MOT/8767/CF", "MOV/5688/hsfgs", "TL/576223/hdgd77"));



        claimtypeSpinner();
        polyNumTypeSpinner(policies);
        stiEsttypeSpinner();

        setViewActions();

        return  view;
    }



    private void claimtypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.claim_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mClaimTypeSpinner.setAdapter(staticAdapter);

        mClaimTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mClaimTypeSpinner.getItemAtPosition(0);


            }
        });

    }

    private void stiEsttypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.sti_est_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mStiEstSpinner.setAdapter(staticAdapter);

        mStiEstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //De-Visualizing the individual form
                mStiEstSpinner.getItemAtPosition(0);


            }
        });

    }


    private void polyNumTypeSpinner(ArrayList<String> arrayList) {
        // Create an ArrayAdapter using the string array and a default spinner

        mPolynumTypeSpinner
                .setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayList));

        mPolynumTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String VehicleTypeString = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPolynumTypeSpinner.getItemAtPosition(0);
            }
        });

    }

    //seting onclicks listeners
    private void setViewActions() {
        mUploadDamagePix.setOnClickListener(this);
        mUploadDocument.setOnClickListener(this);
        mUploadEstimateCost.setOnClickListener(this);
        mProceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload_damage_pix:

                break;

            case R.id.upload_estimate_cost:

                break;

            case R.id.upload_document:

                break;
            case R.id.proceed:
//                validate user input

                validateUserInputs();
                break;
        }
    }

    private void validateUserInputs() {

        boolean isValid = true;

            if (mDescclaimEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutLastDescClaim.setError("Claim Description is required!");
                isValid = false;
            } else if (mLossdeEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutDateofLoss.setError("Loss Date is required!");
                isValid = false;
            } else if (mEstCostEditxtS1.getText().toString().isEmpty()) {
                mInputLayoutEstimateCost.setError("Put your own estimate, even if you want STI to provide estimate");
                isValid = false;
            }else if (mPinEditxt.getText().toString().isEmpty()) {
                mInputLayoutPin.setError("Your Pin is required!");
                isValid = false;
            } else if (mPinEditxt.getText().toString().trim().length() >4 ||mPinEditxt.getText().toString().trim().length() <4) {
                mInputLayoutPin.setError("Invalid Entry !");
                Snackbar snackbar = Snackbar.make(mClaimFormLayout1, "If you have not set pin, click to set your pin", Snackbar.LENGTH_LONG);
                snackbar.setAction("Set Pin", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            setPin();


                    }
                });
                snackbar.show();
                isValid = false;
            }else{
                mInputLayoutLastDescClaim.setErrorEnabled(false);
                mInputLayoutDateofLoss.setErrorEnabled(false);
                mInputLayoutEstimateCost.setErrorEnabled(false);
                mInputLayoutPin.setErrorEnabled(false);

            }


            //Claim Type Spinner
            claimTypeString = mClaimTypeSpinner.getSelectedItem().toString();
            if (claimTypeString.equals("Claim Type")) {

                showMessage("Select Claim Type");
                isValid = false;
            }
            //Policy type Spinner
            policyTypeString = mPolynumTypeSpinner.getSelectedItem().toString();
            if (policyTypeString.equals("Policy Number")) {
                showMessage("Select your Policy Number");
                isValid = false;
            }

            stiEstTypeString = mStiEstSpinner.getSelectedItem().toString();
            if (stiEstTypeString.equals("Should STI Provide Estimate")) {
                showMessage("Don't forget to Select Yes or No for STI estimate");
                isValid = false;
            }


            if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
                initFragment();
            }





    }

    private void setPin() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Set Your Pin");

        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("1234");
        builder.setView(input);

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().isEmpty()||input.getText().toString().trim().length()>4||input.getText().toString().trim().length()<4) {
                    showMessage("Invalid Input");
                    return;
                }

                pinString = input.getText().toString();

               //Post request to set pin with other parameter
                showMessage("Pin Set Successfully");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void initFragment() {
        mProceed.setVisibility(View.GONE);
        mProgressbar1S1.setVisibility(View.VISIBLE);

        try {
            //Post Request for claims



        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1S1.setVisibility(View.GONE);
            mProceed.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mClaimFormLayout1, s, Snackbar.LENGTH_SHORT).show();
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
