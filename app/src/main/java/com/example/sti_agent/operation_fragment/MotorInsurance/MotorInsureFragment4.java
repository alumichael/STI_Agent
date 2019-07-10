package com.example.sti_agent.operation_fragment.MotorInsurance;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.Model.Vehicle.Personal_detail;
import com.example.sti_agent.Model.Vehicle.VehicleDetails;
import com.example.sti_agent.Model.Vehicle.VehiclePictures;
import com.example.sti_agent.Model.Vehicle.VehiclePolicy;
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

class MotorInsureFragment4 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VEHICLE_MAKER = "vehicle_maker";
    private static final String PREMIUM_AMOUNT = "amount";

    // TODO: Rename and change types of parameters
    private String vehicleMaker;
    private int p_amount;

    @BindView(R.id.step_view)
    StepView stepView;

    @BindView(R.id.v_next_btn2)
    Button v_next_btn;

    @BindView(R.id.v_back_btn2)
    Button v_back_btn;

    @BindView(R.id.front_img_btn)
    Button front_img_btn;
    @BindView(R.id.back_img_btn)
    Button back_img_btn;
    @BindView(R.id.left_img_btn)
    Button left_img_btn;
    @BindView(R.id.right_img_btn)
    Button right_img_btn1;

    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;


    @BindView(R.id.qb_form_layout3)
    FrameLayout qb_form_layout4;

    @BindView(R.id.btn_layout3)
    LinearLayout btn_layout3;

    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;





    private  int currentStep=3;
    Realm realm;

    VehiclePolicy id=new VehiclePolicy();
    String primaryKey=id.getId();



    public MotorInsureFragment4() {
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
    public static MotorInsureFragment4 newInstance(String param1, String param2) {
        MotorInsureFragment4 fragment = new MotorInsureFragment4();
        Bundle args = new Bundle();
        args.putString(VEHICLE_MAKER, param1);
        args.putString(PREMIUM_AMOUNT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UserPreferences userPreferences=new UserPreferences(getContext());
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vehicleMaker = getArguments().getString(VEHICLE_MAKER);
            int oldquote=userPreferences.getTempQuotePrice();

            int newquote=Integer.parseInt(getArguments().getString(PREMIUM_AMOUNT));
            int total_quote=oldquote+newquote;
            userPreferences.setTempQuotePrice(total_quote);
            Log.i("PrimaryVariable",primaryKey);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_motor_insured4, container, false);
        ButterKnife.bind(this,view);
        //        stepView next registration step
        stepView.go(currentStep, true);

        realm=Realm.getDefaultInstance();
        UserPreferences userPreferences=new UserPreferences(getContext());



        setViewActions();

        return  view;
    }



    //seting onclicks listeners
    private void setViewActions() {

        v_next_btn.setOnClickListener(this);
        v_back_btn.setOnClickListener(this);
        fabAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2:
//                send quote to client and sti
                mSummary();
                break;

            case R.id.v_back_btn2:
                if (currentStep > 0) {
                    currentStep--;
                }
                stepView.done(false);
                stepView.go(currentStep, true);

                Fragment motorInsureFragment3 = new MotorInsureFragment3();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_motor_form_container, motorInsureFragment3);
                ft.commit();

                break;

            case R.id.fabAdd:
                UserPreferences userPreferences=new UserPreferences(getContext());


                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Personal_detail personal_detail=new Personal_detail();

                        if(realm.isEmpty()){



                            personal_detail.setPrefix(userPreferences.getMotorIPrefix());
                            personal_detail.setFirst_name(userPreferences.getMotorIFirstName());
                            personal_detail.setLast_name(userPreferences.getMotorILastName());
                            personal_detail.setEmail(userPreferences.getMotorIEmail());
                            personal_detail.setGender(userPreferences.getMotorIGender());
                            personal_detail.setPhone(userPreferences.getMotorIPhoneNum());
                            personal_detail.setResident_address(userPreferences.getMotorIResAdrr());
                            personal_detail.setNext_of_kin(userPreferences.getMotorINextKin());
                            personal_detail.setNext_of_kin_address("");
                            personal_detail.setCustomer_type(userPreferences.getMotorPtype());
                            personal_detail.setCompany_name(userPreferences.getMotorICompanyName());
                            personal_detail.setMailing_address(userPreferences.getMotorIMailingAddr());
                            personal_detail.setTin_number(userPreferences.getMotorITinNumber());
                            personal_detail.setOffice_address(userPreferences.getMotorIOff_addr());
                            personal_detail.setContact_person(userPreferences.getMotorIContPerson());
                            //Vehicle List
                            VehicleDetails vehicleDetails=new VehicleDetails();
                            vehicleDetails.setPeriod("");
                            vehicleDetails.setStartDate(userPreferences.getMotorStartDate());
                            vehicleDetails.setPolicy_type(userPreferences.getMotorPolicyType());
                            vehicleDetails.setEnhanced_third_party(userPreferences.getMotorPEnhanceType());
                            vehicleDetails.setPrivate_policy(userPreferences.getMotorPrivateType());
                            vehicleDetails.setCommercial_policy(userPreferences.getMotorCommercialType());
                            vehicleDetails.setMotor_cycle_policy(userPreferences.getMotorPolicyType());
                            vehicleDetails.setVehicle_make(userPreferences.getMotorVehicleMake());
                            vehicleDetails.setVehicle_type(userPreferences.getMotorVehicleType());
                            vehicleDetails.setBody_type(userPreferences.getMotorVehicleBody());
                            vehicleDetails.setYear(userPreferences.getMotorVehicleYear());
                            vehicleDetails.setRegistration_number(userPreferences.getMotorVehicleRegNum());
                            vehicleDetails.setChasis_number(userPreferences.getMotorVehicleChasisNum());
                            vehicleDetails.setEngine_number(userPreferences.getMotorVehicleEngNum());
                            vehicleDetails.setMotorcylce_value(userPreferences.getMotorCycleValue());
                            vehicleDetails.setVehicle_value(userPreferences.getMotorVehicleValue());

                            //Vehicle Picture List
                            VehiclePictures vehiclePictures=new VehiclePictures();
                            vehiclePictures.setFront_view("Front Link");
                            vehiclePictures.setBack_view("Back Link");
                            vehiclePictures.setLeft_view("Left Link");
                            vehiclePictures.setRight_view("Right Link");

                            RealmList<VehiclePictures>vehiclePicturesList=new RealmList<>();
                            vehiclePicturesList.add(vehiclePictures);
                            vehicleDetails.setVehiclePictures(vehiclePicturesList);

                            RealmList<VehicleDetails>vehicleDetailsList=new RealmList<>();
                            vehicleDetailsList.add(vehicleDetails);

                            personal_detail.setVehicle_info(vehicleDetailsList);




                                    final Personal_detail personal_detail1=realm.copyToRealm(personal_detail);

                                    VehiclePolicy vehiclePolicy=realm.createObject(VehiclePolicy.class,primaryKey);
                                    vehiclePolicy.setAgent_id("1");
                                    vehiclePolicy.setQuote_price(String.valueOf(userPreferences.getTempQuotePrice()));
                                    vehiclePolicy.setPayment_source("paystack");
                                    vehiclePolicy.setPin("11234");

                                    Log.i("Primary1",primaryKey);

                                    vehiclePolicy.getPersonal_info().add(personal_detail1);



                        }else if(!realm.isEmpty()){



                            //Vehicle List
                            VehicleDetails vehicleDetails=new VehicleDetails();
                            vehicleDetails.setPeriod("");
                            vehicleDetails.setStartDate(userPreferences.getMotorStartDate());
                            vehicleDetails.setPolicy_type(userPreferences.getMotorPolicyType());
                            vehicleDetails.setEnhanced_third_party(userPreferences.getMotorPEnhanceType());
                            vehicleDetails.setPrivate_policy(userPreferences.getMotorPrivateType());
                            vehicleDetails.setCommercial_policy(userPreferences.getMotorCommercialType());
                            vehicleDetails.setMotor_cycle_policy(userPreferences.getMotorPolicyType());
                            vehicleDetails.setVehicle_make(userPreferences.getMotorVehicleMake());
                            vehicleDetails.setVehicle_type(userPreferences.getMotorVehicleType());
                            vehicleDetails.setBody_type(userPreferences.getMotorVehicleBody());
                            vehicleDetails.setYear(userPreferences.getMotorVehicleYear());
                            vehicleDetails.setRegistration_number(userPreferences.getMotorVehicleRegNum());
                            vehicleDetails.setChasis_number(userPreferences.getMotorVehicleChasisNum());
                            vehicleDetails.setEngine_number(userPreferences.getMotorVehicleEngNum());
                            vehicleDetails.setMotorcylce_value(userPreferences.getMotorCycleValue());
                            //Vehicle Picture List
                            VehiclePictures vehiclePictures=new VehiclePictures();
                            vehiclePictures.setFront_view("Front Link");
                            vehiclePictures.setBack_view("Back Link");
                            vehiclePictures.setLeft_view("Left Link");
                            vehiclePictures.setRight_view("Right Link");

                            RealmList<VehiclePictures>vehiclePicturesList=new RealmList<>();
                            vehiclePicturesList.add(vehiclePictures);
                            vehicleDetails.setVehiclePictures(vehiclePicturesList);

                            RealmList<VehicleDetails>vehicleDetailsList=new RealmList<>();
                            vehicleDetailsList.add(vehicleDetails);
                            personal_detail.setVehicle_info(vehicleDetailsList);

                            final Personal_detail personal_detail2=realm.copyToRealm(personal_detail);
                            VehiclePolicy vehiclePolicy=realm.createObject(VehiclePolicy.class,primaryKey);
                            vehiclePolicy.setQuote_price(String.valueOf(userPreferences.getTempQuotePrice()));

                            vehiclePolicy.getPersonal_info().add(personal_detail2);

                            showMessage(primaryKey);



                        }else {
                            showMessage("Invalid transaction");
                        }



                    }
                });


                stepView.done(false);
                stepView.go(1, true);

                Fragment quoteBuyFragment2 = new MotorInsureFragment2();
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.fragment_motor_form_container, quoteBuyFragment2);
                ftrans.commit();
                break;
        }
    }

    private void mSummary() {

        UserPreferences userPreferences=new UserPreferences(getContext());
        btn_layout3.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);


        Personal_detail personal_detail=new Personal_detail();

        personal_detail.setPrefix(userPreferences.getMotorIPrefix());
        personal_detail.setFirst_name(userPreferences.getMotorIFirstName());
        personal_detail.setLast_name(userPreferences.getMotorILastName());
        personal_detail.setEmail(userPreferences.getMotorIEmail());
        personal_detail.setGender(userPreferences.getMotorIGender());
        personal_detail.setPhone(userPreferences.getMotorIPhoneNum());
        personal_detail.setResident_address(userPreferences.getMotorIResAdrr());
        personal_detail.setNext_of_kin(userPreferences.getMotorINextKin());
        personal_detail.setNext_of_kin_address("");
        personal_detail.setCustomer_type(userPreferences.getMotorPtype());
        personal_detail.setCompany_name(userPreferences.getMotorICompanyName());
        personal_detail.setMailing_address(userPreferences.getMotorIMailingAddr());
        personal_detail.setTin_number(userPreferences.getMotorITinNumber());
        personal_detail.setOffice_address(userPreferences.getMotorIOff_addr());
        personal_detail.setContact_person(userPreferences.getMotorIContPerson());
        //Vehicle List
        VehicleDetails vehicleDetails=new VehicleDetails();
        vehicleDetails.setPeriod("");
        vehicleDetails.setStartDate(userPreferences.getMotorStartDate());
        vehicleDetails.setPolicy_type(userPreferences.getMotorPolicyType());
        vehicleDetails.setEnhanced_third_party(userPreferences.getMotorPEnhanceType());
        vehicleDetails.setPrivate_policy(userPreferences.getMotorPrivateType());
        vehicleDetails.setCommercial_policy(userPreferences.getMotorCommercialType());
        vehicleDetails.setMotor_cycle_policy(userPreferences.getMotorPolicyType());
        vehicleDetails.setVehicle_make(userPreferences.getMotorVehicleMake());
        vehicleDetails.setVehicle_type(userPreferences.getMotorVehicleType());
        vehicleDetails.setBody_type(userPreferences.getMotorVehicleBody());
        vehicleDetails.setYear(userPreferences.getMotorVehicleYear());
        vehicleDetails.setRegistration_number(userPreferences.getMotorVehicleRegNum());
        vehicleDetails.setChasis_number(userPreferences.getMotorVehicleChasisNum());
        vehicleDetails.setEngine_number(userPreferences.getMotorVehicleEngNum());
        vehicleDetails.setMotorcylce_value(userPreferences.getMotorCycleValue());
        vehicleDetails.setVehicle_value(userPreferences.getMotorVehicleValue());

        //Vehicle Picture List
        VehiclePictures vehiclePictures=new VehiclePictures();
        vehiclePictures.setFront_view("Front Link");
        vehiclePictures.setBack_view("Back Link");
        vehiclePictures.setLeft_view("Left Link");
        vehiclePictures.setRight_view("Right Link");

        RealmList<VehiclePictures>vehiclePicturesList=new RealmList<>();
        vehiclePicturesList.add(vehiclePictures);
        vehicleDetails.setVehiclePictures(vehiclePicturesList);


        RealmList<VehicleDetails>vehicleDetailsList=new RealmList<>();
        vehicleDetailsList.add(vehicleDetails);

        personal_detail.setVehicle_info(vehicleDetailsList);


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {



                final Personal_detail personal_detail1=realm.copyToRealm(personal_detail);

                VehiclePolicy vehiclePolicy=realm.createObject(VehiclePolicy.class,primaryKey);
                vehiclePolicy.setAgent_id("1");
                vehiclePolicy.setQuote_price(String.valueOf(userPreferences.getTempQuotePrice()));
                vehiclePolicy.setPayment_source("paystack");
                vehiclePolicy.setPin("11234");

                vehiclePolicy.getPersonal_info().add(personal_detail1);

            }
        });
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_motor_form_container, MotorInsureFragment5.newInstance(primaryKey));
        ft.commit();




    }




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
