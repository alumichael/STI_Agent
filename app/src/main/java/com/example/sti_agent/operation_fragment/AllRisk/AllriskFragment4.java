package com.example.sti_agent.operation_fragment.AllRisk;

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

import com.example.sti_agent.Model.AllRisk.AllriskPolicy;
import com.example.sti_agent.Model.AllRisk.ItemDetail;
import com.example.sti_agent.Model.AllRisk.Personal_Detail_allrisk;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.ItemListAdapter;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment2;
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

class AllriskFragment4 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String PRIMARY_KEY = "primaryKey";
    private static final String FOREIGN_KEY = "foreignKey";


    // TODO: Rename and change types of parameters
    private String primaryKey,foreignKey;


    
    @BindView(R.id.qb_form_layout4)
    FrameLayout mQbFormLayout4;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.personal_info_a4)
    TextView mPersonalInfoA4;
    @BindView(R.id.fabShowItemInfo_a4)
    FloatingActionButton mFabShowItemInfoA4;
    @BindView(R.id.inputLayoutPin_a4)
    TextInputLayout mInputLayoutPinA4;
    @BindView(R.id.pin_txt_a4)
    EditText mPinTxtA4;
    @BindView(R.id.modeOfPayment_spinner_a4)
    Spinner mModeOfPaymentSpinnerA4;
    @BindView(R.id.btn_layout4_a4)
    LinearLayout mBtnLayout4A4;
    @BindView(R.id.v_back_btn4_a4)
    Button mVBackBtn4A4;
    @BindView(R.id.v_next_btn4_a4)
    Button mVNextBtn4A4;
    @BindView(R.id.progressbar4_a4)
    AVLoadingIndicatorView mProgressbar4A4;
    


    private  int currentStep=3;
    Realm realm;
    ItemListAdapter itemListAdapter;




    public AllriskFragment4() {
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
    public static AllriskFragment4 newInstance(String param1) {
        AllriskFragment4 fragment = new AllriskFragment4();
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
        View view=inflater.inflate(R.layout.fragment_allrisk4, container, false);
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
        mModeOfPaymentSpinnerA4.setAdapter(staticAdapter);

        mModeOfPaymentSpinnerA4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String modeofPaymentTypeString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mModeOfPaymentSpinnerA4.getItemAtPosition(0);
            }
        });

    }
    private void init(){

        UserPreferences userPreferences=new UserPreferences(getContext());

        //retrieve data for personal detail first
        AllriskPolicy allriskPolicy;

        allriskPolicy=realm.where(AllriskPolicy.class).equalTo("id",primaryKey).findFirst();
        String total_quoteprice=allriskPolicy.getQuote_price();
        RealmList<Personal_Detail_allrisk> personal_detail_allriskss=allriskPolicy.getPersonal_detail_allrisks();




        if(userPreferences.getMotorPtype().equals("Corporate")){
            String corperate="Comapany Name: "+personal_detail_allriskss.get(0).getCompany_name()+"\n"+"\n"+"Phone Number: "+personal_detail_allriskss.get(0).getPhone()+"\n"+
                    "Office Address: "+personal_detail_allriskss.get(0).getOffice_address()+"\n"+"Contact Person: "+personal_detail_allriskss.get(0).getContact_person()+"\n"+
                    "Phone Number: "+personal_detail_allriskss.get(0).getPhone()+"\n"+"Email Address: "+personal_detail_allriskss.get(0).getEmail()+"\n"+"Mailing Address: "+personal_detail_allriskss.get(0).getMailing_addr()+"\n"+
                    "Total Premium: "+total_quoteprice;
            mPersonalInfoA4.setText(corperate);

        }else if (userPreferences.getMotorPtype().equals("Individual")){
            String individual="Prefix: "+personal_detail_allriskss.get(0).getPrefix()+"\n"+"First Name: "+personal_detail_allriskss.get(0).getFirst_name()+"\n"+
                    "Last Name: "+personal_detail_allriskss.get(0).getLast_name()+"\n"+"Phone Number: "+personal_detail_allriskss.get(0).getPhone()+"\n"+
                    "Gender: "+personal_detail_allriskss.get(0).getResident_address()+"\n"+"Mailing Address: "+personal_detail_allriskss.get(0).getMailing_addr()+"\n"+
                    "Total Premium: "+total_quoteprice;
            mPersonalInfoA4.setText(individual);

        }




    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn4A4.setOnClickListener(this);
        mVBackBtn4A4.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn4_a4:
//                send quote to client and sti
                mSubmit();
                break;

            case R.id.v_back_btn4_a4:

                mStepView.go(1, true);

                Fragment allriskFragment2 = new AllriskFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_allrisk_form_container, allriskFragment2);
                ft.commit();

                break;
        }
    }


    @OnClick(R.id.fabShowItemInfo_a4)
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
        final RealmResults<ItemDetail> results;
        //String title;
        results=realm.where(ItemDetail.class).findAll();

        if(results==null){
            showMessage("Result is null");
        }

        //Bind
        itemListAdapter=new ItemListAdapter(results,getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),RecyclerView.VERTICAL,false);
        recycler_vehicles.setLayoutManager(linearLayoutManager);
        recycler_vehicles.setAdapter(itemListAdapter);




        dialog.setContentView(view);
        dialog.show();
    }

    private void mSubmit() {

        mBtnLayout4A4.setVisibility(View.GONE);
        mProgressbar4A4.setVisibility(View.VISIBLE);



        asyncAllriskPolicy(primaryKey);
       // asyncVehicleList(primaryKey);

        showMessage("AllRisk Insurance Record Deleted");





    }

    //To Delete vehicle
    private void asyncAllriskPolicy(final String id){
        AsyncTask<Void,Void,Void> remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                AllriskPolicy allriskPolicy=realm.where(AllriskPolicy.class).equalTo("id",id).findFirst();
                if(allriskPolicy !=null){
                    realm.beginTransaction();
                    allriskPolicy.deleteFromRealm();
                    realm.commitTransaction();
                }

                RealmResults<ItemDetail> vehicleDetails=realm.where(ItemDetail.class).findAll();
                if(vehicleDetails!=null){
                    realm.beginTransaction();
                    vehicleDetails.deleteAllFromRealm();
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

                AllriskPolicy expenses=realm.where(AllriskPolicy.class).equalTo("id",id).findFirst();
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
        Snackbar.make(mQbFormLayout4, s, Snackbar.LENGTH_SHORT).show();
    }



}
