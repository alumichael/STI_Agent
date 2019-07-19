package com.example.sti_agent.Import;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import com.example.sti_agent.Model.Vehicle.Personal_detail;
import com.example.sti_agent.Model.Vehicle.VehicleDetails;
import com.example.sti_agent.Model.Vehicle.VehiclePictures;
import com.example.sti_agent.Model.Vehicle.VehiclePolicy;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.VehiclesListAdapter;
import com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment2;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

class MotorInsureImportFragment2 extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.step_view)
    StepView stepView;

    @BindView(R.id.v_next_btn2)
    Button v_next_btn;

    @BindView(R.id.multiple_img_btn)
    Button multiple_img_btn;

    @BindView(R.id.v_back_btn2)
    Button v_back_btn;

    @BindView(R.id.personal_info)
    TextView personal_info;

    @BindView(R.id.inputLayoutPin_m5)
    TextInputLayout inputLayoutPin_m5;

    @BindView(R.id.pin_txt_m5)
    EditText pin_txt_m4;

    @BindView(R.id.modeOfPayment_spinner_m5)
    Spinner modeOfPayment_spinner_m5;

    @BindView(R.id.qb_form_import_layout2)
    FrameLayout qb_form_import_layout2;

    @BindView(R.id.btn_layout3)
    LinearLayout btn_layout3;

    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;


    private  int currentStep=1;
    Realm realm;
    VehiclesListAdapter vehiclesListAdapter;
    public int PICK_FILE = 5;
    Uri file_uri;

    String primaryKey;



    ArrayList<ArrayList<String>> note= new ArrayList<>();


    public MotorInsureImportFragment2() {
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
    public static MotorInsureImportFragment2 newInstance(String param1, String param2) {
        MotorInsureImportFragment2 fragment = new MotorInsureImportFragment2();
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
        View view=inflater.inflate(R.layout.fragment_motor_import_insured2, container, false);
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

        if(userPreferences.getMotorPtype().equals("Corporate")){
            String corperate="Comapany Name: "+userPreferences.getMotorICompanyName()+"\n"+"TIN Number: "+userPreferences.getMotorITinNumber()+"\n"+
                    "\n"+"Phone Number: "+userPreferences.getMotorIPhoneNum()+"\n"+
                    "Office Address: "+userPreferences.getMotorIOff_addr()+"\n"+"Contact Person: "+userPreferences.getMotorIContPerson()+"\n"+
                    "\n"+"Email Address: "+userPreferences.getMotorIEmail();
            personal_info.setText(corperate);

        }else if (userPreferences.getMotorPtype().equals("Individual")){
            String individual="Prefix: "+userPreferences.getMotorIPrefix()+"\n"+"First Name: "+userPreferences.getMotorIFirstName()+"\n"+
                    "Last Name: "+userPreferences.getMotorILastName()+"\n"+"Phone Number: "+userPreferences.getMotorIPhoneNum()+"\n"+
                    "Gender: "+userPreferences.getMotorIGender()+"\n"+"Mailing Address: "+userPreferences.getMotorIMailingAddr();
            personal_info.setText(individual);

        }

        chooseExcelFile();


    }



    //seting onclicks listeners
    private void setViewActions() {

        v_next_btn.setOnClickListener(this);
        v_back_btn.setOnClickListener(this);
        multiple_img_btn.setOnClickListener(this);

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
                ft.replace(R.id.fragment_import_form_container, quoteBuyFragment2);
                ft.commit();
                break;

            case R.id.multiple_img_btn:
//                send quote to client and sti
                mSubmit();
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
        }else if(results.size()==0){
            showMessage("REsult size is zero");
            Log.i("Size", String.valueOf(results.size()));
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


    public void chooseExcelFile() {
        Intent intent = new Intent();
        intent.setType("application/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 || data == null || data.getData() == null) {
            showMessage("No file is selected, try again");

            return;
        }

        file_uri=data.getData();





        try {
/*
            progressbar.setVisibility(View.VISIBLE);
            import_file_txt.setVisibility(View.INVISIBLE);*/
            InputStream file =   getContext().getContentResolver().openInputStream(file_uri);
            // FileInputStream file = new FileInputStream(new File(file_uri.getPath()));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            int TotalRowNum=sheet.getPhysicalNumberOfRows();



            Log.i("TotalRowNum ", String.valueOf(TotalRowNum));
            // Iterate through each rows one by one
            for (Row row : sheet) {
                // For each row, iterate through all the columns
                //int row_num = row.getRowNum();
                ArrayList<String> datas=new ArrayList<>();
                Log.i("noteSizeInit ", String.valueOf(note.size()));

                    Log.i("RowNumber ", String.valueOf(row.getRowNum()));
                    Log.i("RowData", String.valueOf(row.getCell(0)));
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        // Check the cell type and format accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                datas.add(String.valueOf(cell.getNumericCellValue()));
                                Log.i("DataNumeric ", String.valueOf(cell.getNumericCellValue()));
                                //Log.i("FileCheck ",note.concat(String.valueOf(cell.getNumericCellValue())));
                                break;
                            case Cell.CELL_TYPE_STRING:
                                datas.add(cell.getStringCellValue());
                                Log.i("DataString", String.valueOf(cell.getStringCellValue()));
                                // Log.i("FileCheck ",note.concat(cell.getStringCellValue()));
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                datas.add(" ");
                                break;
                        }
                    }

                    note.add(datas);
                    Log.i("noteSizeFinal ", String.valueOf(note.size()));


            }

          /*  progressbar.setVisibility(View.GONE);
            import_file_txt.setVisibility(View.VISIBLE);*/
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            showMessage("File Error: "+e.getMessage());
            Log.i("DataError", String.valueOf(e.getMessage()));

        }

        UserPreferences userPreferences = new UserPreferences(getContext());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.i("RowSizes", String.valueOf(note.size()));
                for (int i = 1; i < note.size(); i++) {

                        VehiclePolicy id = new VehiclePolicy();
                        primaryKey = id.getId();

                        Personal_detail personal_detail = new Personal_detail();

                        //Vehicle List
                        VehicleDetails vehicleDetails = new VehicleDetails();
                        vehicleDetails.setPeriod("");
                        vehicleDetails.setStartDate(note.get(i).get(0));
                        Log.d("DataMine", note.get(i).get(0));
                        showMessage(note.get(i).get(0));
                        vehicleDetails.setPolicy_type(note.get(i).get(1));
                        vehicleDetails.setEnhanced_third_party(note.get(i).get(3));
                        vehicleDetails.setPrivate_policy(note.get(i).get(2));
                        vehicleDetails.setCommercial_policy(note.get(i).get(4));
                        vehicleDetails.setMotor_cycle_policy(note.get(i).get(5));
                        vehicleDetails.setVehicle_make(note.get(i).get(6));
                        vehicleDetails.setVehicle_type(note.get(i).get(7));
                        vehicleDetails.setBody_type(note.get(i).get(8));
                        vehicleDetails.setYear(note.get(i).get(9));
                        Log.d("DataMine9", note.get(i).get(9));
                        vehicleDetails.setRegistration_number(note.get(i).get(10));
                        Log.d("DataMine10", note.get(i).get(10));
                        vehicleDetails.setChasis_number(note.get(i).get(11));
                        Log.d("DataMine11", note.get(i).get(11));
                        vehicleDetails.setEngine_number(note.get(i).get(12));
                        vehicleDetails.setVehicle_value(note.get(i).get(13));
                        vehicleDetails.setMotorcylce_value(note.get(i).get(14));
                        Log.d("DataMine14", note.get(i).get(14));
                        //Vehicle Picture List
                        VehiclePictures vehiclePictures = new VehiclePictures();
                        vehiclePictures.setFront_view("Front Link");
                        vehiclePictures.setBack_view("Back Link");
                        vehiclePictures.setLeft_view("Left Link");
                        vehiclePictures.setRight_view("Right Link");

                        RealmList<VehiclePictures> vehiclePicturesList = new RealmList<>();
                        vehiclePicturesList.add(vehiclePictures);
                        vehicleDetails.setVehiclePictures(vehiclePicturesList);

                        RealmList<VehicleDetails> vehicleDetailsList = new RealmList<>();
                        vehicleDetailsList.add(vehicleDetails);
                        personal_detail.setVehicle_info(vehicleDetailsList);

                        final Personal_detail personal_detail2 = realm.copyToRealm(personal_detail);
                        VehiclePolicy vehiclePolicy = realm.createObject(VehiclePolicy.class, primaryKey);
                        vehiclePolicy.setQuote_price(String.valueOf(userPreferences.getTempQuotePrice()));

                        vehiclePolicy.getPersonal_info().add(personal_detail2);

                        showMessage(primaryKey);


                }

            }

        });


        //import_file_txt.setText(note.toString());

       // showMessage("Imported");

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
        Snackbar.make(qb_form_import_layout2, s, Snackbar.LENGTH_SHORT).show();
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
