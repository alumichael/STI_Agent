package com.example.sti_agent.operation_fragment.MotorInsurance;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.Model.Errors.APIError;
import com.example.sti_agent.Model.Errors.ErrorUtils;
import com.example.sti_agent.Model.ServiceGenerator;
import com.example.sti_agent.Model.Vehicle.BrandType.VehicleBrandType;
import com.example.sti_agent.Model.Vehicle.BrandType.VehicleTypeData;
import com.example.sti_agent.Model.Vehicle.VehicleBrand.VehicleData;
import com.example.sti_agent.Model.Vehicle.VehicleBrand.Vehicles_Brand;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.retrofit_interface.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MotorInsureFragment2 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.step_view)
    StepView stepView;

    //EditText
    @BindView(R.id.start_date)
    EditText start_date;
    @BindView(R.id.vehicle_year)
    EditText vehicle_year;
    @BindView(R.id.vehicle_reg_num)
    EditText vehicle_reg_num;
    @BindView(R.id.vehicle_chasis_num)
    EditText vehicle_chasis_num;
    @BindView(R.id.vehicle_engine_num)
    EditText vehicle_engine_num;
    @BindView(R.id.motor_cycle_value)
    EditText motor_cycle_value;
    @BindView(R.id.vehicle_value)
    EditText vehicle_value;

    @BindView(R.id.qb_form_layout2)
    FrameLayout qb_form_layout2;

    //TextInput
    @BindView(R.id.inputLayoutStartDate)
    TextInputLayout inputLayoutStartDate;
    @BindView(R.id.inputLayoutYear)
    TextInputLayout inputLayoutYear;
    @BindView(R.id.inputLayoutRegNum)
    TextInputLayout inputLayoutRegNum;
    @BindView(R.id.inputLayoutChasisNum)
    TextInputLayout inputLayoutChasisNum;
    @BindView(R.id.inputLayoutEngNum)
    TextInputLayout inputLayoutEngNum;
    @BindView(R.id.inputLayoutMotorCyValue)
    TextInputLayout inputLayoutMotorCyValue;
    @BindView(R.id.inputLayoutVehicleValue)
    TextInputLayout inputLayoutVehicleValue;

    //Spinners
    @BindView(R.id.poly_type_spinner)
    Spinner poly_type_spinner;
    @BindView(R.id.private_type_spinner)
    Spinner private_type_spinner;
    @BindView(R.id.commercial_type_spinner)
    Spinner commercial_type_spinner;
    @BindView(R.id.motor_cycle_type_spinner)
    Spinner motor_cycle_type_spinner;
    @BindView(R.id.vehicle_make_spinner)
    Spinner vehicle_make_spinner;
    @BindView(R.id.vehicle_type_spinner)
    Spinner vehicle_type_spinner;
    @BindView(R.id.vehicle_body_type_spinner)
    Spinner vehicle_body_type_spinner;
    @BindView(R.id.prEnhance_type_spinner)
    Spinner prEnhance_type_spinner;

    private int currentStep = 1;

    //The variables for VehincleData
    List<VehicleData> vehicleDataList;
    ArrayList<String> vehiclesMakerSpinnerList=new ArrayList<>();
    //The Variables for VehicleBrandType
    List<VehicleTypeData> vehicleBrandTypeList;
    ArrayList<String> vehiclesBrandSpinnerList=new ArrayList<>();

    int vehicleId;

  //Button
    @BindView(R.id.v_next_btn1)
    Button v_next_btn;

    //Button

    @BindView(R.id.v_back_btn1)
    Button v_back_btn;

    @BindView(R.id.btn_layout2)
    LinearLayout btn_layout2;

    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;

    String polyTypeString,privateTypeString,prEnhanceString,commerTypeString;
    String motorCycleTypeString,vehicleMakeString,vehicleTypeString,vehincleBodyString,startDateStrg;
    ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

    DatePickerDialog datePickerDialog1;
    public MotorInsureFragment2() {
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
    public static MotorInsureFragment2 newInstance(String param1, String param2) {
        MotorInsureFragment2 fragment = new MotorInsureFragment2();
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
        View view=inflater.inflate(R.layout.fragment_motor_insured2, container, false);
        ButterKnife.bind(this,view);
        //        stepView next registration step

        inputLayoutMotorCyValue.setClickable(false);

        stepView.go(currentStep, true);

        init();

        policytypeSpinner();
       // polyTypeString = poly_type_spinner.getSelectedItem().toString();
        privatetypeSpinner();
       // privateTypeString = private_type_spinner.getSelectedItem().toString();
        pEnhancetypeSpinner();
        //prEnhanceString = prEnhance_type_spinner.getSelectedItem().toString();
        commercialtypeSpinner();
        //commerTypeString = commercial_type_spinner.getSelectedItem().toString();
        motoCycletypeSpinner();
        //motorCycleTypeString = motor_cycle_type_spinner.getSelectedItem().toString();
        setViewActions();

        vehicleBodySpinner();
        showDatePicker();


        return  view;
    }


    private  void init(){
        UserPreferences userPreferences = new UserPreferences(getContext());

        //Temporal save and go to next Operation


        start_date.setText(userPreferences.getMotorStartDate());

        vehicle_year.setText(userPreferences.getMotorVehicleYear());

        vehicle_reg_num.setText(userPreferences.getMotorVehicleRegNum());

        vehicle_chasis_num.setText(userPreferences.getMotorVehicleChasisNum());

        vehicle_engine_num.setText(userPreferences.getMotorVehicleEngNum());

        motor_cycle_value.setText(userPreferences.getMotorCycleValue());

        vehicle_value.setText(userPreferences.getMotorVehicleValue());

        fetchVehicleMaker();


    }


    private void policytypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.policy_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        poly_type_spinner.setAdapter(staticAdapter);

        poly_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                if(stringText.equals("Private")){

                    poly_type_spinner.setVisibility(View.VISIBLE);
                    poly_type_spinner.setClickable(true);

                    //De-Visualizing the corporate form
                    commercial_type_spinner.setVisibility(View.GONE);
                    commercial_type_spinner.setClickable(false);
                    motor_cycle_type_spinner.setVisibility(View.GONE);
                    motor_cycle_type_spinner.setClickable(false);
                    inputLayoutMotorCyValue.setVisibility(View.GONE);
                    inputLayoutMotorCyValue.setClickable(false);


                    //Visualizing the individual form
                    inputLayoutStartDate.setVisibility(View.VISIBLE);
                    inputLayoutStartDate.setClickable(true);
                    private_type_spinner.setVisibility(View.VISIBLE);
                    private_type_spinner.setClickable(true);

                    vehicle_make_spinner.setVisibility(View.VISIBLE);
                    vehicle_make_spinner.setClickable(true);
                    vehicle_type_spinner.setVisibility(View.VISIBLE);
                    vehicle_type_spinner.setClickable(true);
                    vehicle_body_type_spinner.setVisibility(View.VISIBLE);
                    vehicle_body_type_spinner.setClickable(true);
                    inputLayoutYear.setVisibility(View.VISIBLE);
                    inputLayoutYear.setClickable(true);
                    inputLayoutRegNum.setVisibility(View.VISIBLE);
                    inputLayoutRegNum.setClickable(true);
                    inputLayoutChasisNum.setVisibility(View.VISIBLE);
                    inputLayoutChasisNum.setClickable(true);
                    inputLayoutEngNum.setVisibility(View.VISIBLE);
                    inputLayoutEngNum.setClickable(true);
                    inputLayoutVehicleValue.setVisibility(View.VISIBLE);
                    inputLayoutVehicleValue.setClickable(true);


                }else if(stringText.equals("Commercial")){

                    poly_type_spinner.setVisibility(View.VISIBLE);
                    poly_type_spinner.setClickable(true);

                    commercial_type_spinner.setVisibility(View.VISIBLE);
                    commercial_type_spinner.setClickable(true);

                    //De-Visualizing the corporate form
                    private_type_spinner.setVisibility(View.GONE);
                    private_type_spinner.setClickable(false);
                    prEnhance_type_spinner.setVisibility(View.GONE);
                    prEnhance_type_spinner.setClickable(false);
                    motor_cycle_type_spinner.setVisibility(View.GONE);
                    motor_cycle_type_spinner.setClickable(false);
                    inputLayoutMotorCyValue.setVisibility(View.GONE);
                    inputLayoutMotorCyValue.setClickable(false);


                    //Visualizing the individual form
                    inputLayoutStartDate.setVisibility(View.VISIBLE);
                    inputLayoutStartDate.setClickable(true);
                    vehicle_make_spinner.setVisibility(View.VISIBLE);
                    vehicle_make_spinner.setClickable(true);
                    vehicle_type_spinner.setVisibility(View.VISIBLE);
                    vehicle_type_spinner.setClickable(true);
                    vehicle_body_type_spinner.setVisibility(View.VISIBLE);
                    vehicle_body_type_spinner.setClickable(true);
                    inputLayoutYear.setVisibility(View.VISIBLE);
                    inputLayoutYear.setClickable(true);
                    inputLayoutRegNum.setVisibility(View.VISIBLE);
                    inputLayoutRegNum.setClickable(true);
                    inputLayoutChasisNum.setVisibility(View.VISIBLE);
                    inputLayoutChasisNum.setClickable(true);
                    inputLayoutEngNum.setVisibility(View.VISIBLE);
                    inputLayoutEngNum.setClickable(true);
                    inputLayoutVehicleValue.setVisibility(View.VISIBLE);
                    inputLayoutVehicleValue.setClickable(true);

                }else if(stringText.equals("Motor Cycle")){
                    poly_type_spinner.setVisibility(View.VISIBLE);
                    poly_type_spinner.setClickable(true);

                    motor_cycle_type_spinner.setVisibility(View.VISIBLE);
                    motor_cycle_type_spinner.setClickable(true);

                    //De-Visualizing the corporate form
                    private_type_spinner.setVisibility(View.GONE);
                    private_type_spinner.setClickable(false);
                    prEnhance_type_spinner.setVisibility(View.GONE);
                    prEnhance_type_spinner.setClickable(false);
                    commercial_type_spinner.setVisibility(View.GONE);
                    commercial_type_spinner.setClickable(false);
                    vehicle_make_spinner.setVisibility(View.GONE);
                    vehicle_make_spinner.setClickable(false);
                    vehicle_type_spinner.setVisibility(View.GONE);
                    vehicle_type_spinner.setClickable(false);
                    vehicle_body_type_spinner.setVisibility(View.GONE);
                    vehicle_body_type_spinner.setClickable(false);

                    inputLayoutVehicleValue.setVisibility(View.GONE);
                    inputLayoutVehicleValue.setClickable(false);



                    //Visualizing the individual form
                    inputLayoutStartDate.setVisibility(View.VISIBLE);
                    inputLayoutStartDate.setClickable(true);
                    inputLayoutYear.setVisibility(View.VISIBLE);
                    inputLayoutYear.setClickable(true);
                    inputLayoutRegNum.setVisibility(View.VISIBLE);
                    inputLayoutRegNum.setClickable(true);
                    inputLayoutChasisNum.setVisibility(View.VISIBLE);
                    inputLayoutChasisNum.setClickable(true);
                    inputLayoutEngNum.setVisibility(View.VISIBLE);
                    inputLayoutEngNum.setClickable(true);
                    inputLayoutMotorCyValue.setVisibility(View.VISIBLE);
                    inputLayoutMotorCyValue.setClickable(true);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                poly_type_spinner.getItemAtPosition(0);
                //De-Visualizing the individual form
                private_type_spinner.setVisibility(View.GONE);
                private_type_spinner.setClickable(false);
                vehicle_body_type_spinner.setVisibility(View.GONE);
                vehicle_body_type_spinner.setClickable(false);
                motor_cycle_type_spinner.setVisibility(View.GONE);
                motor_cycle_type_spinner.setClickable(false);
                inputLayoutMotorCyValue.setVisibility(View.GONE);
                inputLayoutMotorCyValue.setClickable(false);
                commercial_type_spinner.setVisibility(View.GONE);
                commercial_type_spinner.setClickable(false);

                //Visualizing the individual form
                inputLayoutStartDate.setVisibility(View.VISIBLE);
                inputLayoutStartDate.setClickable(true);
                poly_type_spinner.setVisibility(View.VISIBLE);
                poly_type_spinner.setClickable(true);
                vehicle_make_spinner.setVisibility(View.VISIBLE);
                vehicle_make_spinner.setClickable(true);
                vehicle_type_spinner.setVisibility(View.VISIBLE);
                vehicle_type_spinner.setClickable(true);
                inputLayoutYear.setVisibility(View.VISIBLE);
                inputLayoutYear.setClickable(true);
                inputLayoutRegNum.setVisibility(View.VISIBLE);
                inputLayoutRegNum.setClickable(true);
                inputLayoutChasisNum.setVisibility(View.VISIBLE);
                inputLayoutChasisNum.setClickable(true);
                inputLayoutEngNum.setVisibility(View.VISIBLE);
                inputLayoutEngNum.setClickable(true);
                inputLayoutVehicleValue.setVisibility(View.VISIBLE);
                inputLayoutVehicleValue.setClickable(true);


            }
        });

    }

    private void privatetypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.private_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        private_type_spinner.setAdapter(staticAdapter);

        private_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String privateTypeString = (String) parent.getItemAtPosition(position);
                if(privateTypeString.equals("Enhanced 3rd Party")){
                    prEnhance_type_spinner.setVisibility(View.VISIBLE);
                    prEnhance_type_spinner.setClickable(true);
                }else{
                    prEnhance_type_spinner.setVisibility(View.GONE);
                    prEnhance_type_spinner.setClickable(false);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                private_type_spinner.getItemAtPosition(0);
            }
        });

    }


    private void pEnhancetypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.pEnhance_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        prEnhance_type_spinner.setAdapter(staticAdapter);

        prEnhance_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String pEnhanceTypeString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                prEnhance_type_spinner.getItemAtPosition(0);
            }
        });

    }


    private void commercialtypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.commercial_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        commercial_type_spinner.setAdapter(staticAdapter);

        commercial_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String commercialTypeString = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                commercial_type_spinner.getItemAtPosition(0);
            }
        });

    }


    private void motoCycletypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.motor_cycle_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        motor_cycle_type_spinner.setAdapter(staticAdapter);

        motor_cycle_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String motorCycleTypeString = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                motor_cycle_type_spinner.getItemAtPosition(0);
            }
        });

    }


    private void vehicleBodySpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.vehicleBody_type_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        vehicle_body_type_spinner.setAdapter(staticAdapter);

        vehicle_body_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String vehicleBodyString = (String) parent.getItemAtPosition(position);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vehicle_body_type_spinner.getItemAtPosition(0);
            }
        });

    }







    //seting onclicks listeners
    private void setViewActions() {

        v_next_btn.setOnClickListener(this);
        v_back_btn.setOnClickListener(this);
        start_date.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn1:
//                validate user input
                validateUserInputs();
                break;

            case R.id.start_date:
//                show date picker
                datePickerDialog1.show();

                break;

            case R.id.v_back_btn1:
                //do to previous fragment
                if (currentStep > 0) {
                    currentStep--;
                }
                stepView.done(false);
                stepView.go(currentStep, true);

                Fragment quoteBuyFragment1 = new MotorInsureFragment1();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_motor_form_container, quoteBuyFragment1);
                ft.commit();

                break;
        }
    }

    private void validateUserInputs() {


        boolean isValid = true;

        if (start_date.getText().toString().isEmpty()&&inputLayoutStartDate.isClickable()) {
            inputLayoutStartDate.setError("Your Start Date is required!");

            isValid = false;
        } else if (vehicle_year.getText().toString().isEmpty()&&inputLayoutYear.isClickable()) {
            inputLayoutYear.setError("Vehicle Year is required!");

            isValid = false;
        } else if (vehicle_reg_num.getText().toString().isEmpty()&&inputLayoutRegNum.isClickable()) {
            inputLayoutRegNum.setError("Vehicle Registration Number is required!");

            isValid = false;
        } else if (vehicle_chasis_num.getText().toString().isEmpty()&&inputLayoutChasisNum.isClickable()) {
            inputLayoutChasisNum.setError("Chasis Number is required!");

            isValid = false;
        } else if (vehicle_engine_num.getText().toString().isEmpty()&&inputLayoutEngNum.isClickable()) {
            inputLayoutEngNum.setError("Your Engine Number is required!");

            isValid = false;
        }else if (vehicle_value.getText().toString().isEmpty()&&inputLayoutVehicleValue.isClickable()) {
            inputLayoutVehicleValue.setError("Vehicle value Number is required!");

            isValid = false;
        }else {
            inputLayoutStartDate.setErrorEnabled(false);
            inputLayoutYear.setErrorEnabled(false);
            inputLayoutRegNum.setErrorEnabled(false);
            inputLayoutChasisNum.setErrorEnabled(false);
            inputLayoutEngNum.setErrorEnabled(false);
            inputLayoutVehicleValue.setErrorEnabled(false);
        }

        if (motor_cycle_value.getText().toString().isEmpty()&&inputLayoutMotorCyValue.isClickable()) {
            inputLayoutMotorCyValue.setError("Motor Cycle Value Number is required!");
            String b=String.valueOf(motor_cycle_value.isClickable());

            isValid = false;
        }else {
            inputLayoutMotorCyValue.setErrorEnabled(false);
        }


        // Spinner Validations
        //policyType validation
        polyTypeString = poly_type_spinner.getSelectedItem().toString();
        if (polyTypeString.equals("Select Policy Type")&&poly_type_spinner.isClickable()) {
            showMessage("Select Policy Type");
            isValid = false;
        }
        //Private Spinner
        prEnhanceString = prEnhance_type_spinner.getSelectedItem().toString();
        if (prEnhanceString.equals("Select Enhanced 3rd Party")&&prEnhance_type_spinner.isClickable()) {
            showMessage("Select your Enhance Party Category");
            isValid = false;
        }

        //Private Spinner
        privateTypeString = private_type_spinner.getSelectedItem().toString();
        if (privateTypeString.equals("Select Cover")&&private_type_spinner.isClickable()) {
            showMessage("Select your Private Category");
            isValid = false;
        }

        //Commercial Spinner
        commerTypeString = commercial_type_spinner.getSelectedItem().toString();
        if (commerTypeString.equals("Select Cover")&&commercial_type_spinner.isClickable()) {
            showMessage("Select your Commercial Category");
            isValid = false;
        }

        //Motor Cycle Spinner
        motorCycleTypeString = motor_cycle_type_spinner.getSelectedItem().toString();
        if (motorCycleTypeString.equals("Select Motor Cycle Policy")&&motor_cycle_type_spinner.isClickable()) {
            showMessage("Select your Motor Cycle Category");
            isValid = false;
        }

        //VehincleMaker Spinner
        vehicleMakeString = vehicle_make_spinner.getSelectedItem().toString();
        if (vehicleMakeString.equals("Select Vehicle Maker")&&vehicle_make_spinner.isClickable()) {
            showMessage("Select your Motor Vehicle Maker");
            isValid = false;
        }

        //VehicleType Spinner
        vehicleTypeString = vehicle_type_spinner.getSelectedItem().toString();
        if (vehicleTypeString.equals("Select Brand")&&vehicle_type_spinner.isClickable()) {
            showMessage("Select your Motor Brand");
            isValid = false;
        }

        //VehicleBody Spinner
        vehincleBodyString = vehicle_body_type_spinner.getSelectedItem().toString();
        if (vehincleBodyString.equals("Select Body Type")&&vehicle_body_type_spinner.isClickable()) {
            showMessage("Select your Vehicle Body Type");
            isValid = false;
        }


        if (isValid) {
//            send inputs to next next page
//            Goto to the next Registration step
            initFragment();
        }

    }

    private void initFragment() {
        btn_layout2.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);

        try {
            UserPreferences userPreferences = new UserPreferences(getContext());

            //Temporal save and go to next Operation

            userPreferences.setMotorStartDate(start_date.getText().toString());
            userPreferences.setMotorPolicyType(polyTypeString);
            userPreferences.setMotorPrivateType(privateTypeString);
            userPreferences.setMotorPEnhanceType(prEnhanceString);
            userPreferences.setMotorCommercialType(commerTypeString);
            userPreferences.setMotorCycleType(motorCycleTypeString);
            userPreferences.setMotorVehicleMake(vehicleMakeString);
            userPreferences.setMotorVehicleType(vehicleTypeString);
            userPreferences.setMotorVehicleBody(vehincleBodyString);
            userPreferences.setMotorVehicleYear(vehicle_year.getText().toString());
            userPreferences.setMotorVehicleRegNum(vehicle_reg_num.getText().toString());
            userPreferences.setMotorVehicleChasisNum(vehicle_chasis_num.getText().toString());
            userPreferences.setMotorVehicleEngNum(vehicle_engine_num.getText().toString());
            userPreferences.setMotorVehicleValue(vehicle_value.getText().toString());
            userPreferences.setMotorCycleValue(motor_cycle_value.getText().toString());




           // Fragment quoteBuyFragment3 = new MotorInsureFragment3();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_motor_form_container, MotorInsureFragment3.newInstance(userPreferences.getMotorVehicleMake(),"8000"), MotorInsureFragment3.class.getSimpleName());
            ft.commit();

        }catch (Exception e){
            Log.i("Form Error",e.getMessage());
            progressbar.setVisibility(View.GONE);
            v_next_btn.setVisibility(View.VISIBLE);
            showMessage("Error: " + e.getMessage());
        }
    }


    private void showMessage(String s) {
        Snackbar.make(qb_form_layout2, s, Snackbar.LENGTH_LONG).show();
    }



    private void showDatePicker() {
        //Get current date
        Calendar calendar = Calendar.getInstance();

        //Create datePickerDialog with initial date which is current and decide what happens when a date is selected.
        datePickerDialog1 = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //When a date is selected, it comes here.
                //Change birthdayEdittext's text and dismiss dialog.
                if(year<calendar.get(Calendar.YEAR)){

                    showMessage("Invalid Start Date");
                    Log.i("Calendar",year+" "+calendar.get(Calendar.YEAR));
                    return;
                }
                int monthofYear=monthOfYear+1;
                startDateStrg = dayOfMonth + "-" + monthofYear + "-" + year;
                start_date.setText(startDateStrg);
                datePickerDialog1.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void fetchVehicleMaker(){
        //get client and call object for request

        showMessage("Initializing page, please wait...");
        btn_layout2.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);
        Call<Vehicles_Brand> call=client.vehicle_brand();
        call.enqueue(new Callback<Vehicles_Brand>() {
            @Override
            public void onResponse(Call<Vehicles_Brand> call, Response<Vehicles_Brand> response) {
                try {
                    if (!response.isSuccessful()) {

                        try{
                            APIError apiError= ErrorUtils.parseError(response);

                            showMessage("Fetch Failed: "+apiError.getErrors());
                            Log.i("Fetch Failed",apiError.getErrors().toString());
                            Log.i("Fetch Failed",response.errorBody().toString());

                        }catch (Exception e){
                            Log.i("Fetch Failed",e.getMessage());
                            showMessage("Fetch Failed");

                        }
                        btn_layout2.setVisibility(View.VISIBLE);
                        progressbar.setVisibility(View.GONE);
                        return;
                    }

                    vehicleDataList = response.body().getVehicleData();

                    vehiclesMakerSpinnerList.add("Select Vehicle Maker");
                    for(int i=0; i<vehicleDataList.size();i++){
                        vehiclesMakerSpinnerList.add(vehicleDataList.get(i).getName());
                    }
                    vehicleMakerSpinner();

                    btn_layout2.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.GONE);
                    showMessage("You can now select you policy..");

                }catch (Exception e){
                    showMessage("Fetch Error: " + e.getMessage());
                }



            }

            @Override
            public void onFailure(Call<Vehicles_Brand> call, Throwable t) {
                showMessage("Fetch Failed "+t.getMessage());
                Log.i("GEtError",t.getMessage());
                btn_layout2.setVisibility(View.VISIBLE);
                progressbar.setVisibility(View.GONE);
            }
        });


    }
    private void vehicleMakerSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        vehicle_make_spinner
                .setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        vehiclesMakerSpinnerList));

        vehicle_make_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String VehicleMakerString = (String) parent.getItemAtPosition(position);
                vehicleId=position+1;
                //get client and call object for request

                showMessage("Please wait...");
                btn_layout2.setVisibility(View.GONE);
                progressbar.setVisibility(View.VISIBLE);
                Call<VehicleBrandType> call=client.brand_type(vehicleId);

                call.enqueue(new Callback<VehicleBrandType>() {
                    @Override
                    public void onResponse(Call<VehicleBrandType> call, Response<VehicleBrandType> response) {
                        try {
                            if (!response.isSuccessful()) {

                                try{
                                    APIError apiError= ErrorUtils.parseError(response);

                                    showMessage("Fetch Failed: "+apiError.getErrors());
                                    Log.i("Fetch Failed",apiError.getErrors().toString());
                                    Log.i("Fetch Failed",response.errorBody().toString());

                                }catch (Exception e){
                                    Log.i("Fetch Failed",e.getMessage());
                                    showMessage("Fetch Failed");

                                }
                                btn_layout2.setVisibility(View.VISIBLE);
                                progressbar.setVisibility(View.GONE);
                                return;
                            }

                            vehicleBrandTypeList = response.body().getData();
                            vehiclesBrandSpinnerList.clear();
                            vehiclesBrandSpinnerList.add("Select Brand");
                            for(int i=0; i<vehicleBrandTypeList.size();i++){


                                vehiclesBrandSpinnerList.add(vehicleBrandTypeList.get(i).getName());
                            }

                            vehicleTypeSpinner();

                            btn_layout2.setVisibility(View.VISIBLE);
                            progressbar.setVisibility(View.GONE);
                            showMessage("Pick your Brand Type...");

                        }catch (Exception e){
                            showMessage("Fetch Error: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<VehicleBrandType> call, Throwable t) {
                        showMessage("Fetch Failed "+t.getMessage());
                        Log.i("GEtError",t.getMessage());
                        btn_layout2.setVisibility(View.VISIBLE);
                        progressbar.setVisibility(View.GONE);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vehicle_make_spinner.getItemAtPosition(0);
            }
        });

    }

    private void vehicleTypeSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        vehicle_type_spinner
                .setAdapter(new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        vehiclesBrandSpinnerList));

        vehicle_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String VehicleBraandTypeString = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vehicle_type_spinner.getItemAtPosition(0);
            }
        });

    }

}
