package com.example.sti_agent.UserMain_Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.Model.Errors.APIError;
import com.example.sti_agent.Model.Errors.ErrorUtils;
import com.example.sti_agent.Model.ServiceGenerator;
import com.example.sti_agent.Model.WalletModel.FundWallet;
import com.example.sti_agent.Model.WalletModel.GetWalletFunded;
import com.example.sti_agent.Model.WalletModel.WalletObj;
import com.example.sti_agent.NetworkConnection;
import com.example.sti_agent.R;
import com.example.sti_agent.SignIn;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.retrofit_interface.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_FundWallet extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.inputLayoutAmount)
    TextInputLayout inputLayoutAmount;
    @BindView(R.id.amount_editxt)
    EditText amountEditxt;
    @BindView(R.id.inputLayoutDescriptn)
    TextInputLayout inputLayoutDescriptn;
    @BindView(R.id.desc_editxt)
    EditText descEditxt;
    @BindView(R.id.fund_btn)
    Button fundBtn;
    @BindView(R.id.fund_layout)
    CoordinatorLayout fund_layout;
    @BindView(R.id.avi1)
    AVLoadingIndicatorView avi1;
    UserPreferences userPreferences;
    Fragment fragment;

    public Fragment_FundWallet() {
        // Required empty public constructor
    }
    NetworkConnection networkConnection=new NetworkConnection();
    ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

    // TODO: Rename and change types and number of parameters
    public static Fragment_Customers newInstance(String param1, String param2) {
        Fragment_Customers fragment = new Fragment_Customers();
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
        View view=inflater.inflate(R.layout.fragment_fundwallet, container, false);
        ButterKnife.bind(this,view);
        userPreferences=new UserPreferences(getContext());
        fundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });


        return  view;
    }
   // private void sendData(String amount,String)

    private void Validate() {

        if (networkConnection.isNetworkConnected(getContext())) {
            boolean isValid = true;

            if (amountEditxt.getText().toString().isEmpty()) {
                inputLayoutAmount.setError("Amount is required!");
                isValid = false;
            } else if (descEditxt.getText().toString().isEmpty()) {
                inputLayoutDescriptn.setError("Description is required!");
                isValid = false;
            } else {
                inputLayoutAmount.setErrorEnabled(false);
                inputLayoutDescriptn.setErrorEnabled(false);
            }

            if (isValid) {

                //Do debit operation on Paystack and fund wallet API
                //Post Request to Api
                sendData(amountEditxt.getText().toString(),descEditxt.getText().toString());
            }

            return;
        }
        showMessage("No Internet connection discovered!");
    }


    private void sendData(String amount,String desc){

        FundWallet fundWallet=new FundWallet(amount,desc,"CREDIT");
        //To create retrofit instance
        //get client and call object for request
        avi1.setVisibility(View.VISIBLE);
        fundBtn.setVisibility(View.GONE);

        Call<GetWalletFunded> call = client.fund_wallet("Token "+userPreferences.getUserToken(), fundWallet);
        Log.i("Token",userPreferences.getUserToken());

        call.enqueue(new Callback<GetWalletFunded>() {
            @Override
            public void onResponse(Call<GetWalletFunded> call, Response<GetWalletFunded> response) {
                    if (!response.isSuccessful()) {

                        try {
                            APIError apiError = ErrorUtils.parseError(response);

                            showMessage("Invalid Entry: " + apiError.getErrors());
                            Log.i("Invalid EntryK", apiError.getErrors().toString());
                            Log.i("Invalid Entry", response.errorBody().toString());

                        } catch (Exception e) {
                            Log.i("InvalidEntry", e.getMessage());
                            showMessage("Invalid Entry");

                        }
                        fundBtn.setVisibility(View.VISIBLE);
                        avi1.setVisibility(View.GONE);
                        return;
                    }
                    try {
                        avi1.setVisibility(View.GONE);
                        int newBalance = response.body().getWallet().getBalance();
                        userPreferences.setWalletBalance(String.valueOf(newBalance));
                        fragment = new Fragment_Dashboard();
                        showFragment(fragment);
                    }catch (Exception e){
                        showMessage("Failed to Save Balance");
                    }

            }

            @Override
            public void onFailure(Call<GetWalletFunded> call, Throwable t) {
                showMessage("Login Failed " + t.getMessage());
                Log.i("GEtError", t.getMessage());
                fundBtn.setVisibility(View.VISIBLE);
                avi1.setVisibility(View.GONE);
            }
        });



    }

    private void showMessage(String s) {
        Snackbar.make(fund_layout, s, Snackbar.LENGTH_SHORT).show();
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }



}
