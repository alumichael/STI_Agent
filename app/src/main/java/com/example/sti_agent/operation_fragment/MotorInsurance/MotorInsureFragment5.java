package com.example.sti_agent.operation_fragment.MotorInsurance;

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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Model.Vehicle.Personal_detail;
import com.example.sti_agent.Model.Vehicle.VehicleDetails;
import com.example.sti_agent.Model.Vehicle.VehiclePolicy;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.VehiclesListAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

class MotorInsureFragment5 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String PRIMARY_KEY = "primaryKey";
    private static final String FOREIGN_KEY = "foreignKey";


    // TODO: Rename and change types of parameters
    private String primaryKey,foreignKey;


    @BindView(R.id.step_view)
    StepView stepView;

    @BindView(R.id.v_next_btn2)
    Button v_next_btn;

    @BindView(R.id.v_back_btn2)
    Button v_back_btn;

    @BindView(R.id.personal_info)
    TextView personal_info;





    @BindView(R.id.qb_form_layout3)
    FrameLayout qb_form_layout4;

    @BindView(R.id.btn_layout3)
    LinearLayout btn_layout3;

    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;


    private  int currentStep=4;
    Realm realm;
    VehiclesListAdapter vehiclesListAdapter;




    public MotorInsureFragment5() {
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
    public static MotorInsureFragment5 newInstance(String param1) {
        MotorInsureFragment5 fragment = new MotorInsureFragment5();
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
        View view=inflater.inflate(R.layout.fragment_motor_insured5, container, false);
        ButterKnife.bind(this,view);
        //  stepView next registration step
        stepView.go(currentStep, true);
        realm= Realm.getDefaultInstance();


        init();
        setViewActions();

        return  view;
    }
    private void init(){

        UserPreferences userPreferences=new UserPreferences(getContext());

        //retrieve data for personal detail first
        VehiclePolicy vehiclePolicy;

        vehiclePolicy=realm.where(VehiclePolicy.class).equalTo("id",primaryKey).findFirst();
        String total_quoteprice=vehiclePolicy.getQuote_price();
        RealmList<Personal_detail> personal_details=vehiclePolicy.getPersonal_info();




        if(userPreferences.getMotorPtype().equals("Corporate")){
            String corperate="Comapany Name: "+personal_details.get(0).getCompany_name()+"\n"+"TIN Number: "+personal_details.get(0).getTin_number()+"\n"+
                    "Last Name: "+personal_details.get(0).getLast_name()+"\n"+"Phone Number: "+personal_details.get(0).getPhone()+"\n"+
                    "Office Address: "+personal_details.get(0).getOffice_address()+"\n"+"Contact Person: "+personal_details.get(0).getContact_person()+"\n"+
                    "Phone Number: "+personal_details.get(0).getPhone()+"\n"+"Email Address: "+personal_details.get(0).getEmail()+"\n"+
                    "Total Premium: "+total_quoteprice;
            personal_info.setText(corperate);

        }else if (userPreferences.getMotorPtype().equals("Individual")){
            String individual="Prefix: "+personal_details.get(0).getPrefix()+"\n"+"First Name: "+personal_details.get(0).getFirst_name()+"\n"+
                    "Last Name: "+personal_details.get(0).getLast_name()+"\n"+"Phone Number: "+personal_details.get(0).getPhone()+"\n"+
                    "Gender: "+personal_details.get(0).getResident_address()+"\n"+"Mailing Address: "+personal_details.get(0).getMailing_address()+"\n"+
                    "Total Premium: "+total_quoteprice;
            personal_info.setText(individual);

        }




    }



    //seting onclicks listeners
    private void setViewActions() {

        v_next_btn.setOnClickListener(this);
        v_back_btn.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2:
//                send quote to client and sti
                mSubmit();
                break;

            case R.id.v_back_btn2:

                stepView.go(1, true);

                Fragment quoteBuyFragment2 = new MotorInsureFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_motor_form_container, quoteBuyFragment2);
                ft.commit();

                break;
        }
    }


    @OnClick(R.id.fabShowVehicles)
    public void showBottomVehicleList() {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_vehicles, null);
        final TextView textView = (TextView) view.findViewById(R.id.detail);
        final RecyclerView recycler_vehicles = (RecyclerView) view.findViewById(R.id.recycler_detail);
        final ImageView close = (ImageView) view.findViewById(R.id.close);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stepView.go(5, true);
                dialog.dismiss();
            }
        });

        //retrieve data
        final RealmResults<VehicleDetails> results;
        //String title;
        results=realm.where(VehicleDetails.class).findAll();

        if(results==null){
            showMessage("REsult is null");
        }

        //Bind
        vehiclesListAdapter=new VehiclesListAdapter(results,getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),RecyclerView.VERTICAL,false);
        recycler_vehicles.setLayoutManager(linearLayoutManager);
        recycler_vehicles.setAdapter(vehiclesListAdapter);

        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recycler_note.setLayoutManager(mLayoutManager);
        recycler_note.setItemAnimator(new DefaultItemAnimator());
        recycler_note.setAdapter(adapter);*/


        dialog.setContentView(view);
        dialog.show();
    }

    private void mSubmit() {

        btn_layout3.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);



        asyncVehiclePolicy(primaryKey);
       // asyncVehicleList(primaryKey);

        showMessage("Vehicle Record Deleted");



        /*Fragment quoteBuyFragment6 = new QuoteBuyFragment6();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_quote_form_container, quoteBuyFragment6);
        ft.commit();*/



    }

    //To Delete vehicle
    private void asyncVehiclePolicy(final String id){
        AsyncTask<Void,Void,Void> remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                VehiclePolicy vehiclePolicy=realm.where(VehiclePolicy.class).equalTo("id",id).findFirst();
                if(vehiclePolicy !=null){
                    realm.beginTransaction();
                    vehiclePolicy.deleteFromRealm();
                    realm.commitTransaction();
                }

                RealmResults<VehicleDetails> vehicleDetails=realm.where(VehicleDetails.class).findAll();
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

                VehiclePolicy expenses=realm.where(VehiclePolicy.class).equalTo("id",id).findFirst();
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
        Snackbar.make(qb_form_layout4, s, Snackbar.LENGTH_SHORT).show();
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
