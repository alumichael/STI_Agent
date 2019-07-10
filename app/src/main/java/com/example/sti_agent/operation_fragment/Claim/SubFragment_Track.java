package com.example.sti_agent.operation_fragment.Claim;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.operation_fragment.Swiss.SwissFragment2;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SubFragment_Track extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.track_form_layout1)
    FrameLayout mTrackFormLayout1;
    @BindView(R.id.polcynum_track_type_spinner)
    Spinner mPolcynumTrackTypeSpinner;
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.progressbar1_s1)
    AVLoadingIndicatorView mProgressbar1S1;
    @BindView(R.id.recycler_track_claim)
    RecyclerView mRecyclerTrackClaim;

    @BindView(R.id.no_post_linearLayout)
    LinearLayout no_post_linearLayout;




    String policyNumString;


    public SubFragment_Track() {
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
    public static SubFragment_Track newInstance(String param1, String param2) {
        SubFragment_Track fragment = new SubFragment_Track();
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
        View view=inflater.inflate(R.layout.fragment_sub_track, container, false);
        ButterKnife.bind(this,view);
        ArrayList<String> policies = new ArrayList<String>(
                Arrays.asList("Policy Number","MOT/8767/CF", "MOV/5688/hsfgs", "TL/576223/hdgd77"));



        init();

        polyNumTypeSpinner(policies);

        setViewActions();

        return  view;
    }

    private  void init(){
        mRecyclerTrackClaim.setVisibility(View.GONE);
        no_post_linearLayout.setVisibility(View.VISIBLE);


    }
    private void polyNumTypeSpinner(ArrayList<String> arrayList) {
        // Create an ArrayAdapter using the string array and a default spinner

        mPolcynumTrackTypeSpinner
                .setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayList));

        mPolcynumTrackTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String policuNumTypeString = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mPolcynumTrackTypeSpinner.getItemAtPosition(0);
            }
        });

    }

    //seting onclicks listeners
    private void setViewActions() {
        mSearchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_btn:
//                validate user input
                validateUserInputs();
                break;
        }
    }

    private void validateUserInputs() {

        boolean isValid = true;



            //Type Spinner
            policyNumString = mPolcynumTrackTypeSpinner.getSelectedItem().toString();
            if (policyNumString.equals("Policy Number")) {

                showMessage("Select Policy Number");
                isValid = false;
            }

            if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
                initFragment();
            }




    }

    private void initFragment() {
        mSearchBtn.setVisibility(View.GONE);
        mProgressbar1S1.setVisibility(View.VISIBLE);

        try {
            //Post request with policy num and get request the claim into recycler view


        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            mProgressbar1S1.setVisibility(View.GONE);
            mSearchBtn.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }

    }


    private void showMessage(String s) {
        Snackbar.make(mTrackFormLayout1, s, Snackbar.LENGTH_SHORT).show();
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
