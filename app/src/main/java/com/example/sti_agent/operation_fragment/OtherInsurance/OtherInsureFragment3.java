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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.Model.Other.OtherPolicy;
import com.example.sti_agent.Model.Other.insureProduct;
import com.example.sti_agent.Model.Other.Personal_Detail_other;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class OtherInsureFragment3 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String SELECTED_PRODUCT = "seleted_product";
    private static final String PREMIUM_AMOUNT = "amount";

    // TODO: Rename and change types of parameters
    private String seleted_product;
    private String p_amount;


    @BindView(R.id.qb_form_layout3)
    FrameLayout mQbFormLayout3;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.selected_prod_txt_o3)
    TextView mSelectedProdTxtO3;
    @BindView(R.id.amount_o3)
    TextView mAmountO3;
    @BindView(R.id.btn_layout3_o3)
    LinearLayout mBtnLayout3O3;
    @BindView(R.id.v_back_btn3_o3)
    Button mVBackBtn3O3;
    @BindView(R.id.v_next_btn3_o3)
    Button mVNextBtn3O3;
    @BindView(R.id.progressbar3_o3)
    AVLoadingIndicatorView mProgressbar3O3;



    private  int currentStep=2;
    Realm realm;

    OtherPolicy id=new OtherPolicy();
    String primaryKey=id.getId();



    public OtherInsureFragment3() {
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
    public static OtherInsureFragment3 newInstance(String param1, String param2) {
        OtherInsureFragment3 fragment = new OtherInsureFragment3();
        Bundle args = new Bundle();
        args.putString(SELECTED_PRODUCT, param1);
        args.putString(PREMIUM_AMOUNT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UserPreferences userPreferences=new UserPreferences(getContext());
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            seleted_product = getArguments().getString(SELECTED_PRODUCT);

            p_amount=getArguments().getString(PREMIUM_AMOUNT);
            int oldquote=userPreferences.getTempOtherQuotePrice();

            int newquote=Integer.parseInt(getArguments().getString(PREMIUM_AMOUNT));
            int total_quote=oldquote+newquote;
            userPreferences.setTempOtherQuotePrice(total_quote);
            
            Log.i("PrimaryVariable",primaryKey);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_other3, container, false);
        ButterKnife.bind(this,view);
        //        mStepView next registration step
        mStepView.go(currentStep, true);

        realm=Realm.getDefaultInstance();
        UserPreferences userPreferences=new UserPreferences(getContext());

        mSelectedProdTxtO3.setText(seleted_product);
        if(p_amount==null){
            p_amount="000";
            String format = p_amount + ".00";
            mAmountO3.setText(format);
        }else {
            String format = p_amount + ".00";
            mAmountO3.setText(format);
        }


        setViewActions();

        return  view;
    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn3O3.setOnClickListener(this);
        mVBackBtn3O3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn3_o3:
//                send quote to client and sti
                mSummary();
                break;

            case R.id.v_back_btn3_o3:
                if (currentStep > 0) {
                    currentStep--;
                }
                mStepView.done(false);
                mStepView.go(currentStep, true);

                Fragment quoteBuyFragment3 = new OtherInsureFragment3();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_other_form_container, quoteBuyFragment3);
                ft.commit();

                break;


        }
    }

    private void mSummary() {

        UserPreferences userPreferences=new UserPreferences(getContext());
        mBtnLayout3O3.setVisibility(View.GONE);
        mProgressbar3O3.setVisibility(View.VISIBLE);


        Personal_Detail_other personal_detail_other=new Personal_Detail_other();

        personal_detail_other.setPrefix(userPreferences.getOtherIPrefix());
        personal_detail_other.setFirst_name(userPreferences.getOtherIFirstName());
        personal_detail_other.setLast_name(userPreferences.getOtherILastName());
        personal_detail_other.setEmail(userPreferences.getOtherIEmail());
        personal_detail_other.setGender(userPreferences.getOtherIGender());
        personal_detail_other.setPhone(userPreferences.getOtherIPhoneNum());
        personal_detail_other.setResident_address(userPreferences.getOtherIResAdrr());
        personal_detail_other.setNext_of_kin(userPreferences.getOtherINextKin());
        personal_detail_other.setPolicy_type(userPreferences.getOtherPtype());
        personal_detail_other.setCompany_name(userPreferences.getOtherICompanyName());
        personal_detail_other.setMailing_addr(userPreferences.getOtherIMailingAddr());
        personal_detail_other.setTin_num(userPreferences.getOtherITinNumber());
        personal_detail_other.setOffice_address(userPreferences.getOtherIOff_addr());
        personal_detail_other.setContact_person(userPreferences.getOtherIContPerson());
        //Item List
        insureProduct insureProduct=new insureProduct();
        insureProduct.setProduct(userPreferences.getOtherProductType());
        insureProduct.setDescription(userPreferences.getOtherDescItem());

        RealmList<insureProduct>insureProductList=new RealmList<>();
        insureProductList.add(insureProduct);

        personal_detail_other.setInsureProducts(insureProductList);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {



                final Personal_Detail_other personal_detail_other1=realm.copyToRealm(personal_detail_other);

                OtherPolicy allriskPolicy=realm.createObject(OtherPolicy.class,primaryKey);
                allriskPolicy.setAgent_id("1");
                allriskPolicy.setQuote_price(String.valueOf(userPreferences.getTempOtherQuotePrice()));
                allriskPolicy.setPayment_source("paystack");
                allriskPolicy.setPin("11234");

                allriskPolicy.getPersonal_detail_others().add(personal_detail_other1);

            }
        });


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_other_form_container, OtherInsureFragment4.newInstance(primaryKey));
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
