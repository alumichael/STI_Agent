package com.example.sti_agent.operation_fragment.Swiss;

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

import com.example.sti_agent.Model.Swiss.AdditionInsured;
import com.example.sti_agent.Model.Swiss.Personal_Detail_swiss;
import com.example.sti_agent.Model.Swiss.SwissInsured;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.AddInsuredListAdapter;
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

class SwissFragment4 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String PRIMARY_KEY = "primaryKey";
    private static final String FOREIGN_KEY = "foreignKey";


    // TODO: Rename and change types of parameters
    private String primaryKey,foreignKey;



    @BindView(R.id.qb_form_layout4)
    FrameLayout mQbFormLayout4;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.personal_info_s4)
    TextView mPersonalInfoS3;
    @BindView(R.id.fabShowAddInsure_s4)
    FloatingActionButton mFabShowAddInsureS3;
    @BindView(R.id.btn_layout4_s4)
    LinearLayout mBtnLayout4S4;
    @BindView(R.id.v_back_btn4_s4)
    Button mVBackBtn4S4;
    @BindView(R.id.v_next_btn4_s4)
    Button mVNextBtn4S4;
    @BindView(R.id.progressbar4_s4)
    AVLoadingIndicatorView mProgressbar4S4;

    @BindView(R.id.inputLayoutPin_s4)
    TextInputLayout inputLayoutPin_s4;

    @BindView(R.id.pin_txt_s4)
    EditText pin_txt_s4;

    @BindView(R.id.modeOfPayment_spinner_s4)
    Spinner modeOfPayment_spinner_s4;



    private  int currentStep=3;
    Realm realm;
    AddInsuredListAdapter addInsureListAdapter;




    public SwissFragment4() {
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
    public static SwissFragment4 newInstance(String param1) {
        SwissFragment4 fragment = new SwissFragment4();
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
        View view=inflater.inflate(R.layout.fragment_swiss4, container, false);
        ButterKnife.bind(this,view);
        //  mStepView next registration step
        mStepView.go(currentStep, true);
        realm= Realm.getDefaultInstance();


        init();
        setViewActions();

        return  view;
    }
    private void init(){

        UserPreferences userPreferences=new UserPreferences(getContext());

        //retrieve data for personal detail first
        SwissInsured swissInsured;

        swissInsured=realm.where(SwissInsured.class).equalTo("id",primaryKey).findFirst();
        String total_quoteprice=swissInsured.getQuote_price();
        RealmList<Personal_Detail_swiss> personal_detail_swisses=swissInsured.getPersonal_detail_swisses();


            String insurer="Prefix: "+personal_detail_swisses.get(0).getPrefix()+"\n"+"First Name: "+personal_detail_swisses.get(0).getFirst_name()+"\n"+
                    "Last Name: "+personal_detail_swisses.get(0).getLast_name()+"\n"+"Phone Number: "+personal_detail_swisses.get(0).getPhone()+"\n"+
                    "Gender: "+personal_detail_swisses.get(0).getResident_address()+"\n"+ "Total Premium: "+total_quoteprice;
            mPersonalInfoS3.setText(insurer);

        




    }



    //seting onclicks listeners
    private void setViewActions() {

        mVNextBtn4S4.setOnClickListener(this);
        mVBackBtn4S4.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn4_s4:
//                send quote to client and sti
                mSubmit();
                break;

            case R.id.v_back_btn4_s4:

                mStepView.go(1, true);

                Fragment swissFragment2 = new SwissFragment2();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_swiss_form_container, swissFragment2);
                ft.commit();

                break;
        }
    }


    @OnClick(R.id.fabShowAddInsure_s4)
    public void showBottomAddInsureList() {
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
        final RealmResults<AdditionInsured> results;
        //String title;
        results=realm.where(AdditionInsured.class).findAll();

        if(results==null){
            showMessage("Result is null");
        }

        //Bind
        addInsureListAdapter=new AddInsuredListAdapter(results,getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getBaseContext(),RecyclerView.VERTICAL,false);
        recycler_vehicles.setLayoutManager(linearLayoutManager);
        recycler_vehicles.setAdapter(addInsureListAdapter);

        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recycler_note.setLayoutManager(mLayoutManager);
        recycler_note.setItemAnimator(new DefaultItemAnimator());
        recycler_note.setAdapter(adapter);*/


        dialog.setContentView(view);
        dialog.show();
    }

    private void mSubmit() {

        mBtnLayout4S4.setVisibility(View.GONE);
        mProgressbar4S4.setVisibility(View.VISIBLE);



        asyncSwissInsured(primaryKey);
       // asyncVehicleList(primaryKey);

        showMessage("Swis-F Record Deleted");


        



    }

    //To Delete vehicle
    private void asyncSwissInsured(final String id){
        AsyncTask<Void,Void,Void> remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                SwissInsured swissPolicy=realm.where(SwissInsured.class).equalTo("id",id).findFirst();
                if(swissPolicy !=null){
                    realm.beginTransaction();
                    swissPolicy.deleteFromRealm();
                    realm.commitTransaction();
                }

                RealmResults<AdditionInsured> addInsureDetails=realm.where(AdditionInsured.class).findAll();
                if(addInsureDetails!=null){
                    realm.beginTransaction();
                    addInsureDetails.deleteAllFromRealm();
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

                SwissInsured expenses=realm.where(SwissInsured.class).equalTo("id",id).findFirst();
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
