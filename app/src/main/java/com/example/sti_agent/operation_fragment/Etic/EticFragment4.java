package com.example.sti_agent.operation_fragment.Etic;

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

import com.example.sti_agent.Model.Etic.EticPolicy;
import com.example.sti_agent.Model.Etic.Personal_Detail_etic;
import com.example.sti_agent.Model.Etic.Travel_Info;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.travel_infoListAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

class EticFragment4 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String PRIMARY_KEY = "primaryKey";
    private static final String FOREIGN_KEY = "foreignKey";


    // TODO: Rename and change types of parameters
    private String primaryKey,foreignKey;



    @BindView(R.id.qb_form_layout3)
    FrameLayout mQbFormLayout3;
    @BindView(R.id.step_view)
    com.shuhart.stepview.StepView mStepView;
    @BindView(R.id.personal_info_txt_e4)
    TextView mPersonalInfoTxtE4;
    @BindView(R.id.fabShowTranspDetail_e4)
    FloatingActionButton mFabShowTranspDetailE4;
    @BindView(R.id.inputLayoutPin_e4)
    TextInputLayout mInputLayoutPinE4;
    @BindView(R.id.pin_txt_e4)
    EditText mPinTxtE4;
    @BindView(R.id.modeOfPayment_spinner_e4)
    Spinner mModeOfPaymentSpinnerE4;
    @BindView(R.id.btn_layout4_e4)
    LinearLayout mBtnLayout4E4;
    @BindView(R.id.v_back_btn4_e4)
    Button mVBackBtn4E4;
    @BindView(R.id.v_next_btn4_e4)
    Button mVNextBtn4E4;
    @BindView(R.id.progressbar4_m4)
    AVLoadingIndicatorView mProgressbar4M4;



    private  int currentStep=3;
    Realm realm;
    travel_infoListAdapter travelListAdapter;




    public EticFragment4() {
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
    public static EticFragment4 newInstance(String param1) {
        EticFragment4 fragment = new EticFragment4();
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
        View view=inflater.inflate(R.layout.fragment_etic4, container, false);
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
        mModeOfPaymentSpinnerE4.setAdapter(staticAdapter);

        mModeOfPaymentSpinnerE4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String modeofPaymentTypeString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mModeOfPaymentSpinnerE4.getItemAtPosition(0);
            }
        });

    }
    private void init(){

        //retrieve data for personal detail first
        EticPolicy eticPolicy;

        eticPolicy=realm.where(EticPolicy.class).equalTo("id",primaryKey).findFirst();
        String total_quoteprice=eticPolicy.getQuote_price();
        RealmList<Personal_Detail_etic> personal_detail_etics=eticPolicy.getPersonal_detail_etic();


        
            String individual="Prefix: "+personal_detail_etics.get(0).getPrefix()+"\n"+"First Name: "+personal_detail_etics.get(0).getFirst_name()+"\n"+
                    "Last Name: "+personal_detail_etics.get(0).getLast_name()+"\n"+"Phone Number: "+personal_detail_etics.get(0).getPhone()+"\n"+
                    "Gender: "+personal_detail_etics.get(0).getResident_address()+"\n"+"Mailing Address: "+personal_detail_etics.get(0).getMailing_addr()+"\n"+
                    "Premium: "+total_quoteprice;
            mPersonalInfoTxtE4.setText(individual);
            

    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn4E4.setOnClickListener(this);
        mVBackBtn4E4.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn4_e4:
//                send quote to client and sti
                mSubmit();
                break;

            case R.id.v_back_btn4_e4:

                mStepView.go(1, true);

                Fragment eticFragment2 = new EticFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_etic_form_container, eticFragment2);
                ft.commit();

                break;
        }
    }


    @OnClick(R.id.fabShowTranspDetail_e4)
    public void showBottomVehicleList() {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_vehicles, null);
        final TextView textView = (TextView) view.findViewById(R.id.detail);
        final RecyclerView recycler_vehicles = (RecyclerView) view.findViewById(R.id.recycler_detail);
        final ImageView close = (ImageView) view.findViewById(R.id.close);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //retrieve data
        final RealmResults<Travel_Info> results;
        //String title;
        results=realm.where(Travel_Info.class).findAll();

        if(results==null){
            showMessage("Result is null");
        }

        //Bind
        travelListAdapter=new travel_infoListAdapter(results,getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),RecyclerView.VERTICAL,false);
        recycler_vehicles.setLayoutManager(linearLayoutManager);
        recycler_vehicles.setAdapter(travelListAdapter);

        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recycler_note.setLayoutManager(mLayoutManager);
        recycler_note.setItemAnimator(new DefaultItemAnimator());
        recycler_note.setAdapter(adapter);*/


        dialog.setContentView(view);
        dialog.show();
    }

    private void mSubmit() {

        mBtnLayout4E4.setVisibility(View.GONE);
        mProgressbar4M4.setVisibility(View.VISIBLE);



        asyncEticPolicy(primaryKey);
       // asyncVehicleList(primaryKey);

        showMessage("Vehicle Record Deleted");
        


    }

    //To Delete vehicle
    private void asyncEticPolicy(final String id){
        AsyncTask<Void,Void,Void> remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                EticPolicy eticPolicy=realm.where(EticPolicy.class).equalTo("id",id).findFirst();
                if(eticPolicy !=null){
                    realm.beginTransaction();
                    eticPolicy.deleteFromRealm();
                    realm.commitTransaction();
                }

                RealmResults<Travel_Info> travel_infos=realm.where(Travel_Info.class).findAll();
                if(travel_infos!=null){
                    realm.beginTransaction();
                    travel_infos.deleteAllFromRealm();
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

                EticPolicy expenses=realm.where(EticPolicy.class).equalTo("id",id).findFirst();
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
