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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.Model.Swiss.AdditionInsured;
import com.example.sti_agent.Model.Swiss.Personal_Detail_swiss;
import com.example.sti_agent.Model.Swiss.SwissInsured;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;


public class SwissFragment3 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ELIGIBILITY = "eligibility";
    private static final String PREMIUM_AMOUNT = "amount";

    // TODO: Rename and change types of parameters
    private String eligibity;
    private String p_amount;


    @BindView(R.id.qb_form_layout3)
    FrameLayout mQbFormLayout3;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.eligibility_txt_s3)
    TextView mEligibilityTxtS3;
    @BindView(R.id.amount_s3)
    TextView mAmountS3;
    @BindView(R.id.btn_layout3_s3)
    LinearLayout mBtnLayout3S3;
    @BindView(R.id.v_back_btn3_s3)
    Button mVBackBtn3S3;
    @BindView(R.id.v_next_btn3_s3)
    Button mVNextBtn3S3;
    @BindView(R.id.progressbar3_s3)
    AVLoadingIndicatorView mProgressbar3S3;
    @BindView(R.id.fabAddInsure_s3)
    FloatingActionButton mFabAddInsureS3;
    Realm realm;



    private  int currentStep=2;

    SwissInsured id=new SwissInsured();
    String primaryKey=id.getId();


    public SwissFragment3() {
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
    public static SwissFragment3 newInstance(String param1, String param2) {
        SwissFragment3 fragment = new SwissFragment3();
        Bundle args = new Bundle();
        args.putString(ELIGIBILITY, param1);
        args.putString(PREMIUM_AMOUNT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserPreferences userPreferences=new UserPreferences(getContext());
        if (getArguments() != null) {
            eligibity = getArguments().getString(ELIGIBILITY);

            int oldquote=userPreferences.getTempSwissQuotePrice();
            p_amount=getArguments().getString(PREMIUM_AMOUNT);

            int newquote=Integer.parseInt(getArguments().getString(PREMIUM_AMOUNT));
            int total_quote=oldquote+newquote;
            userPreferences.setTempSwissQuotePrice(total_quote);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_swiss3, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step
        mStepView.go(currentStep, true);


        //instancial Realm db
        realm=Realm.getDefaultInstance();

        mEligibilityTxtS3.setText(eligibity);
        if(p_amount==null){
            p_amount="000";
            String format = p_amount + ".00";
            mAmountS3.setText(format);
        }else {
            String format = p_amount + ".00";
            mAmountS3.setText(format);
        }




        setViewActions();

        return  view;
    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn3S3.setOnClickListener(this);
        mVBackBtn3S3.setOnClickListener(this);
        mFabAddInsureS3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn3_s3:
//                send quote to client and sti
                mailClientAndSti();
                break;

            case R.id.fabAddInsure_s3:
                UserPreferences userPreferences=new UserPreferences(getContext());


                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Personal_Detail_swiss personal_detail_swiss=new Personal_Detail_swiss();

                        if(realm.isEmpty()){


                            personal_detail_swiss.setPrefix(userPreferences.getSwissIPrefix());
                            personal_detail_swiss.setFirst_name(userPreferences.getSwissIFirstName());
                            personal_detail_swiss.setLast_name(userPreferences.getSwissILastName());
                            personal_detail_swiss.setGender(userPreferences.getSwissIGender());
                            personal_detail_swiss.setMarital_status(userPreferences.getSwissIMaritalStatus());
                            personal_detail_swiss.setPhone(userPreferences.getSwissIPhoneNum());
                            personal_detail_swiss.setResident_address(userPreferences.getSwissIResAdrr());
                            personal_detail_swiss.setNext_of_kin(userPreferences.getSwissINextKin());
                            personal_detail_swiss.setNext_of_kin_address(userPreferences.getSwissINextKinAddr());
                            personal_detail_swiss.setNext_of_kin_phone(userPreferences.getSwissINextKinPhoneNum());
                            personal_detail_swiss.setDate_of_birth(userPreferences.getSwissIDob());
                            personal_detail_swiss.setDisability(userPreferences.getSwissIDisable());
                            personal_detail_swiss.setBenefit_category(userPreferences.getSwissIBenefit());
                           
                            //Additional Insured List
                            AdditionInsured additionInsured=new AdditionInsured();
                            additionInsured.setFirst_name(userPreferences.getSwissIAddFirstName());
                            additionInsured.setLast_name(userPreferences.getSwissIAddLastName());
                            additionInsured.setDate_of_birth(userPreferences.getSwissIAddDOB());
                            additionInsured.setGender(userPreferences.getSwissIAddGender());
                            additionInsured.setPhone(userPreferences.getSwissIAddPhoneNum());
                            additionInsured.setEmail(userPreferences.getSwissIAddEmail());
                            additionInsured.setDisability(userPreferences.getSwissIAddDisability());
                            additionInsured.setBenefit_category(userPreferences.getSwissIAddBenefitCat());
                            additionInsured.setMarital_status(userPreferences.getSwissIAddMaritalStatus());
                            additionInsured.setPicture("");
                            RealmList<AdditionInsured> additionInsuredList=new RealmList<>();
                            additionInsuredList.add(additionInsured);

                            personal_detail_swiss.setAdditionInsureds(additionInsuredList);
                            

                            final Personal_Detail_swiss personal_detail_swiss1=realm.copyToRealm(personal_detail_swiss);

                            SwissInsured swissInsured=realm.createObject(SwissInsured.class,primaryKey);
                            swissInsured.setAgent_id("1");
                            swissInsured.setQuote_price(String.valueOf(userPreferences.getTempSwissQuotePrice()));
                            swissInsured.setPayment_source("paystack");
                            swissInsured.setPin("11234");

                            Log.i("Primary1",primaryKey);

                            swissInsured.getPersonal_detail_swisses().add(personal_detail_swiss1);



                        }else if(!realm.isEmpty()){



                            //Vehicle List
                            AdditionInsured additionInsured=new AdditionInsured();
                            additionInsured.setFirst_name(userPreferences.getSwissIAddFirstName());
                            additionInsured.setLast_name(userPreferences.getSwissIAddLastName());
                            additionInsured.setDate_of_birth(userPreferences.getSwissIAddDOB());
                            additionInsured.setGender(userPreferences.getSwissIAddGender());
                            additionInsured.setPhone(userPreferences.getSwissIAddPhoneNum());
                            additionInsured.setEmail(userPreferences.getSwissIAddEmail());
                            additionInsured.setDisability(userPreferences.getSwissIAddDisability());
                            additionInsured.setBenefit_category(userPreferences.getSwissIAddBenefitCat());
                            additionInsured.setMarital_status(userPreferences.getSwissIAddMaritalStatus());
                            additionInsured.setPicture("");

                            RealmList<AdditionInsured>additionInsuredList=new RealmList<>();
                            additionInsuredList.add(additionInsured);
                            personal_detail_swiss.setAdditionInsureds(additionInsuredList);



                            final Personal_Detail_swiss personal_detail_swiss2=realm.copyToRealm(personal_detail_swiss);
                            SwissInsured swissInsured=realm.createObject(SwissInsured.class,primaryKey);
                            swissInsured.setQuote_price(String.valueOf(userPreferences.getTempSwissQuotePrice()));

                            swissInsured.getPersonal_detail_swisses().add(personal_detail_swiss2);
                            showMessage(primaryKey);



                        }else {
                            showMessage("Invalid transaction");
                        }



                    }
                });


                mStepView.done(false);
                mStepView.go(1, true);

                Fragment swissFragment2 = new SwissFragment2();
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.fragment_swiss_form_container, swissFragment2);
                ftrans.commit();
                break;

            case R.id.v_back_btn3_s3:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment swissFragment22 = new SwissFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_swiss_form_container, swissFragment22);
                ft.commit();
                break;
        }
    }

    private void mailClientAndSti() {
        UserPreferences userPreferences = new UserPreferences(getContext());

        mBtnLayout3S3.setVisibility(View.GONE);
        mProgressbar3S3.setVisibility(View.VISIBLE);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Personal_Detail_swiss personal_detail_swiss=new Personal_Detail_swiss();



                    personal_detail_swiss.setPrefix(userPreferences.getSwissIPrefix());
                    personal_detail_swiss.setFirst_name(userPreferences.getSwissIFirstName());
                    personal_detail_swiss.setLast_name(userPreferences.getSwissILastName());
                    personal_detail_swiss.setGender(userPreferences.getSwissIGender());
                    personal_detail_swiss.setMarital_status(userPreferences.getSwissIMaritalStatus());
                    personal_detail_swiss.setPhone(userPreferences.getSwissIPhoneNum());
                    personal_detail_swiss.setResident_address(userPreferences.getSwissIResAdrr());
                    personal_detail_swiss.setNext_of_kin(userPreferences.getSwissINextKin());
                    personal_detail_swiss.setNext_of_kin_address(userPreferences.getSwissINextKinAddr());
                    personal_detail_swiss.setNext_of_kin_phone(userPreferences.getSwissINextKinPhoneNum());
                    personal_detail_swiss.setDate_of_birth(userPreferences.getSwissIDob());
                    personal_detail_swiss.setDisability(userPreferences.getSwissIDisable());
                    personal_detail_swiss.setBenefit_category(userPreferences.getSwissIBenefit());

                    //Additional Insured List
                    AdditionInsured additionInsured=new AdditionInsured();
                    additionInsured.setFirst_name(userPreferences.getSwissIAddFirstName());
                    additionInsured.setLast_name(userPreferences.getSwissIAddLastName());
                    additionInsured.setDate_of_birth(userPreferences.getSwissIAddDOB());
                    additionInsured.setGender(userPreferences.getSwissIAddGender());
                    additionInsured.setPhone(userPreferences.getSwissIAddPhoneNum());
                    additionInsured.setEmail(userPreferences.getSwissIAddEmail());
                    additionInsured.setDisability(userPreferences.getSwissIAddDisability());
                    additionInsured.setBenefit_category(userPreferences.getSwissIAddBenefitCat());
                    additionInsured.setMarital_status(userPreferences.getSwissIAddMaritalStatus());
                    additionInsured.setPicture("");
                    RealmList<AdditionInsured> additionInsuredList=new RealmList<>();
                    additionInsuredList.add(additionInsured);

                    personal_detail_swiss.setAdditionInsureds(additionInsuredList);


                    final Personal_Detail_swiss personal_detail_swiss1=realm.copyToRealm(personal_detail_swiss);

                    SwissInsured swissInsured=realm.createObject(SwissInsured.class,primaryKey);
                    swissInsured.setAgent_id("1");
                    swissInsured.setQuote_price(String.valueOf(userPreferences.getTempSwissQuotePrice()));
                    swissInsured.setPayment_source("paystack");
                    swissInsured.setPin("11234");

                    Log.i("Primary1",primaryKey);

                    swissInsured.getPersonal_detail_swisses().add(personal_detail_swiss1);



            }
        });


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_swiss_form_container, SwissFragment4.newInstance(primaryKey));
        ft.commit();



    }




    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout3, s, Snackbar.LENGTH_SHORT).show();
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
