package com.example.sti_agent.operation_fragment.OtherInsurance;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Model.Other.OtherPolicy;
import com.example.sti_agent.Model.Other.Personal_Detail_other;
import com.example.sti_agent.Model.Other.insureProduct;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.otherInsureListAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

class OtherInsureFragment4 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String PRIMARY_KEY = "primaryKey";
    private static final String FOREIGN_KEY = "foreignKey";


    // TODO: Rename and change types of parameters
    private String primaryKey,foreignKey;



    @BindView(R.id.qb_form_layout3)
    FrameLayout mQbFormLayout3;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.personal_info_txt_o4)
    TextView mPersonalInfoTxtO4;
    @BindView(R.id.fabShowProdDetail_o4)
    FloatingActionButton mFabShowProdDetailO4;
    @BindView(R.id.inputLayoutPin_o4)
    TextInputLayout mInputLayoutPinO4;
    @BindView(R.id.pin_txt_o4)
    EditText mPinTxtO4;
    @BindView(R.id.modeOfPayment_spinner_o4)
    Spinner mModeOfPaymentSpinnerO4;
    @BindView(R.id.btn_layout4_o4)
    LinearLayout mBtnLayout4O4;
    @BindView(R.id.v_back_btn4_o4)
    Button mVBackBtn4O4;
    @BindView(R.id.v_next_btn4_o4)
    Button mVNextBtn4O4;
    @BindView(R.id.progressbar4_o4)
    AVLoadingIndicatorView mProgressbar4O4;



    private  int currentStep=3;
    Realm realm;
    otherInsureListAdapter otherinsureListAdapter;




    public OtherInsureFragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment Fragment_Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherInsureFragment4 newInstance(String param1) {
        OtherInsureFragment4 fragment = new OtherInsureFragment4();
        Bundle args = new Bundle();
        args.putString(PRIMARY_KEY,param1);
        //args.putString(FOREIGN_KEY,param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            primaryKey = getArguments().getString(PRIMARY_KEY);
            //foreignKey = getArguments().getString(FOREIGN_KEY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_other4, container, false);
        ButterKnife.bind(this,view);
        //  mStepView next registration step
        mStepView.go(currentStep, true);
        realm= Realm.getDefaultInstance();


        init();
        modeofPaymentSpinner();
        setViewActions();

        return  view;
    }
    private void modeofPaymentSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.mode_of_payment,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mModeOfPaymentSpinnerO4.setAdapter(staticAdapter);

        mModeOfPaymentSpinnerO4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String modeofPaymentTypeString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mModeOfPaymentSpinnerO4.getItemAtPosition(0);
            }
        });

    }
    private void init(){

        UserPreferences userPreferences=new UserPreferences(getContext());

        //retrieve data for personal detail first
        OtherPolicy otherPolicy;

        otherPolicy=realm.where(OtherPolicy.class).equalTo("id",primaryKey).findFirst();
        String total_quoteprice=otherPolicy.getQuote_price();
        RealmList<Personal_Detail_other> personal_detail_otherss=otherPolicy.getPersonal_detail_others();




        if(userPreferences.getMotorPtype().equals("Corporate")){
            String corperate="Comapany Name: "+personal_detail_otherss.get(0).getCompany_name()+"\n"+"TIN Number: "+personal_detail_otherss.get(0).getTin_num()+"\n"+
                   "\n"+"Phone Number: "+personal_detail_otherss.get(0).getPhone()+"\n"+
                    "Office Address: "+personal_detail_otherss.get(0).getOffice_address()+"\n"+"Contact Person: "+personal_detail_otherss.get(0).getContact_person()+"\n"+
                    "Phone Number: "+personal_detail_otherss.get(0).getPhone()+"\n"+"Email Address: "+personal_detail_otherss.get(0).getEmail()+"\n"+
                    "Total Premium: "+total_quoteprice;
            mPersonalInfoTxtO4.setText(corperate);

        }else if (userPreferences.getMotorPtype().equals("Individual")){
            String individual="Prefix: "+personal_detail_otherss.get(0).getPrefix()+"\n"+"First Name: "+personal_detail_otherss.get(0).getFirst_name()+"\n"+
                    "Last Name: "+personal_detail_otherss.get(0).getLast_name()+"\n"+"Phone Number: "+personal_detail_otherss.get(0).getPhone()+"\n"+
                    "Gender: "+personal_detail_otherss.get(0).getResident_address()+"\n"+"Mailing Address: "+personal_detail_otherss.get(0).getMailing_addr()+"\n"+
                    "Total Premium: "+total_quoteprice;
            mPersonalInfoTxtO4.setText(individual);

        }




    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn4O4.setOnClickListener(this);
        mVBackBtn4O4.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn4_o4:
//                send quote to client and sti
                mSubmit();
                break;

            case R.id.v_back_btn4_o4:

                mStepView.go(1, true);

                Fragment otherInsureFragment2 = new OtherInsureFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_other_form_container, otherInsureFragment2);
                ft.commit();

                break;
        }
    }


    @OnClick(R.id.fabShowProdDetail_o4)
    public void showBottomVehicleList() {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_vehicles, null);
        final TextView textView = (TextView) view.findViewById(R.id.detail);
        final RecyclerView recycler_vehicles = (RecyclerView) view.findViewById(R.id.recycler_detail);
        final ImageView close = (ImageView) view.findViewById(R.id.close);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mStepView.go(5, true);
                dialog.dismiss();
            }
        });

        //retrieve data
        final RealmResults<insureProduct> results;
        //String title;
        results=realm.where(insureProduct.class).findAll();

        if(results==null){
            showMessage("Result is null");
        }

        //Bind
        otherinsureListAdapter=new otherInsureListAdapter(results,getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),RecyclerView.VERTICAL,false);
        recycler_vehicles.setLayoutManager(linearLayoutManager);
        recycler_vehicles.setAdapter(otherinsureListAdapter);

        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recycler_note.setLayoutManager(mLayoutManager);
        recycler_note.setItemAnimator(new DefaultItemAnimator());
        recycler_note.setAdapter(adapter);*/


        dialog.setContentView(view);
        dialog.show();
    }

    private void mSubmit() {

        mBtnLayout4O4.setVisibility(View.GONE);
        mProgressbar4O4.setVisibility(View.VISIBLE);



        asyncOtherPolicy(primaryKey);
       // asyncVehicleList(primaryKey);

        showMessage("Other Insured Record Deleted");



        /*Fragment quoteBuyFragment6 = new QuoteBuyFragment6();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_quote_form_container, quoteBuyFragment6);
        ft.commit();*/



    }

    //To Delete vehicle
    private void asyncOtherPolicy(final String id){
        AsyncTask<Void,Void,Void> remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                OtherPolicy otherPolicy=realm.where(OtherPolicy.class).equalTo("id",id).findFirst();
                if(otherPolicy !=null){
                    realm.beginTransaction();
                    otherPolicy.deleteFromRealm();
                    realm.commitTransaction();
                }

                RealmResults<insureProduct> insureproduct=realm.where(insureProduct.class).findAll();
                if(insureproduct!=null){
                    realm.beginTransaction();
                    insureproduct.deleteAllFromRealm();
                    realm.commitTransaction();
                }else {
                    showMessage("Error in deletion");
                }
                realm.close();
                return null;
            }
        };
        remoteItem.execute();
    }

    /*//To Delete vehicle
    private void asyncVehicleList(final String id){
        AsyncTask<Void,Void,Void> remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                OtherPolicy expenses=realm.where(OtherPolicy.class).equalTo("id",id).findFirst();
                if(expenses !=null){
                    realm.beginTransaction();
                    expenses.deleteFromRealm();
                    realm.commitTransaction();
                }
                realm.close();
                return null;
            }
        };
        remoteItem.execute();
    }
*/





    private void showMessage(String s) {
        Snackbar.make(mQbFormLayout3, s, Snackbar.LENGTH_SHORT).show();
    }

}
