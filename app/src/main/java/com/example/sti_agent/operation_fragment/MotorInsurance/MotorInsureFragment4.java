package com.example.sti_agent.operation_fragment.MotorInsurance;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.sti_agent.BuildConfig;
import com.example.sti_agent.Model.Vehicle.Personal_detail;
import com.example.sti_agent.Model.Vehicle.VehicleDetails;
import com.example.sti_agent.Model.Vehicle.VehiclePictures;
import com.example.sti_agent.Model.Vehicle.VehiclePolicy;
import com.example.sti_agent.NetworkConnection;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shuhart.stepview.StepView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

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
    String cameraFilePath;

    int PICK_IMAGE_FRONTVIEW = 1;
    int PICK_IMAGE_BACKVIEW = 2;
    int PICK_IMAGE_LEFTVIEW = 3;
    int PICK_IMAGE_RIGHTVIEW = 4;

    int CAM_IMAGE_FRONTVIEW = 11;
    int CAM_IMAGE_BACKVIEW = 22;
    int CAM_IMAGE_LEFTVIEW = 33;
    int CAM_IMAGE_RIGHTVIEW = 44;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri frontview_img_uri;
    String frontview_img_url;

    Uri backview_img_uri;
    String backview_img_url;

    Uri leftview_img_uri;
    String leftview_img_url;

    Uri rightview_img_uri;
    String rightview_img_url;






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
        front_img_btn.setOnClickListener(this);
        back_img_btn.setOnClickListener(this);
        left_img_btn.setOnClickListener(this);
        right_img_btn1.setOnClickListener(this);


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //This is the directory in which the file will be created. This is the default location of Camera photos
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for using again
        cameraFilePath = "file://" + image.getAbsolutePath();
        return image;
    }



    private void chooseIdFront_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_FRONTVIEW);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }
    }

    private void chooseIdBack_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_BACKVIEW);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }
    }
    private void chooseIdLeft_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_LEFTVIEW);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }
    }
    private void chooseIdRight_camera() {

        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", createImageFile()));
            startActivityForResult(intent, CAM_IMAGE_RIGHTVIEW);
        } catch (IOException ex) {
            ex.printStackTrace();
            showMessage("Invalid Entry");
            Log.i("Invalid_Cam_Entry",ex.getMessage());
        }
    }

    private void chooseImageFront() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_FRONTVIEW);
    }

    private void chooseImageBack() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_BACKVIEW);
    }

    private void chooseImageRight() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_RIGHTVIEW);
    }

    private void chooseImageLeft() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_LEFTVIEW);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 ) {
            showMessage("No image is selected, try again");
            return;
        }


        showMessage("Uploading...");
        if (networkConnection.isNetworkConnected(getContext())) {

            if (requestCode == 1) {
                frontview_img_uri = data.getData();

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (frontview_img_uri != null) {
                        String name = "frontview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(frontview_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            frontview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }
            if (requestCode == 11) {
                frontview_img_uri = Uri.parse(cameraFilePath);

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (frontview_img_uri != null) {
                        String name = "frontview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(frontview_img_uri)
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            frontview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if (requestCode == 2){

                backview_img_uri = data.getData();

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (backview_img_uri != null) {
                        String name = "backview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(backview_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            backview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if (requestCode == 22){

                backview_img_uri = Uri.parse(cameraFilePath);

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (backview_img_uri != null) {
                        String name = "backview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(backview_img_uri)
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            backview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if(requestCode == 3){

                rightview_img_uri = data.getData();

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (rightview_img_uri != null) {
                        String name = "rightview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(rightview_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            rightview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if(requestCode == 33){

                rightview_img_uri = Uri.parse(cameraFilePath);

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (rightview_img_uri != null) {
                        String name = "rightview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(rightview_img_uri)
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            rightview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if(requestCode == 4){

                leftview_img_uri = data.getData();

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (leftview_img_uri != null) {
                        String name = "leftview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(Uri.parse(leftview_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            leftview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }else if(requestCode == 44){

                leftview_img_uri =Uri.parse(cameraFilePath);

                Random random=new Random();
                String rand= String.valueOf(random.nextInt());

                try {
                    if (leftview_img_uri != null) {
                        String name = "leftview"+rand;
                        if (name.equals("")) {
                            showMessage("Please try again");

                        } else {

                            String imageId = MediaManager.get().upload(leftview_img_uri)
                                    .option("public_id", "user_registration/profile_photos/vehicle_image" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            btn_layout3.setVisibility(View.GONE);
                                            progressbar.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            progressbar.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            progressbar.setVisibility(View.GONE);
                                            btn_layout3.setVisibility(View.VISIBLE);
                                            leftview_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            btn_layout3.setVisibility(View.VISIBLE);
                                            progressbar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onReschedule(String requestId, ErrorInfo error) {
                                            // your code here
                                        }
                                    })
                                    .dispatch();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Please Check your Image");

                }

            }
            return;
        }
        showMessage("No Internet connection discovered!");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_next_btn2:
//                send quote to client and sti
                mSummary();
                break;

            case R.id.front_img_btn:
                // setup the alert builder
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                builder2.setTitle("Choose Mode of Entry");

// add a list
                String[] entry2 = {"Camera", "Gallery"};
                builder2.setItems(entry2, (dialog2, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdFront_camera();
                            dialog2.dismiss();
                            break;

                        case 1: // export

                            chooseImageFront();
                            dialog2.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                front_img_btn.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));


                break;

            case R.id.back_img_btn:
                // setup the alert builder
                AlertDialog.Builder builder3 = new AlertDialog.Builder(getContext());
                builder3.setTitle("Choose Mode of Entry");

// add a list
                String[] entry3 = {"Camera", "Gallery"};
                builder3.setItems(entry3, (dialog3, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdBack_camera();
                            dialog3.dismiss();
                            break;

                        case 1: // export

                            chooseImageBack();
                            dialog3.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog3 = builder3.create();
                dialog3.show();
                back_img_btn.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));

                break;

            case R.id.left_img_btn:
                // setup the alert builder
                AlertDialog.Builder builder4 = new AlertDialog.Builder(getContext());
                builder4.setTitle("Choose Mode of Entry");

// add a list
                String[] entry4 = {"Camera", "Gallery"};
                builder4.setItems(entry4, (dialog4, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdLeft_camera();
                            dialog4.dismiss();
                            break;

                        case 1: // export

                            chooseImageLeft();
                            dialog4.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog4 = builder4.create();
                dialog4.show();
                left_img_btn.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                 

                break;

            case R.id.right_img_btn:
                // setup the alert builder
                AlertDialog.Builder builder5 = new AlertDialog.Builder(getContext());
                builder5.setTitle("Choose Mode of Entry");

// add a list
                String[] entry5 = {"Camera", "Gallery"};
                builder5.setItems(entry5, (dialog5, option) -> {
                    switch (option) {
                        case 0:
                            // direct entry
                            chooseIdRight_camera();
                            dialog5.dismiss();
                            break;

                        case 1: // export

                            chooseImageRight();
                            dialog5.dismiss();

                            break;

                    }
                });
// create and show the alert dialog
                AlertDialog dialog5 = builder5.create();
                dialog5.show();
                right_img_btn1.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                  

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

                if(frontview_img_url!=null&&leftview_img_url!=null&&rightview_img_url!=null&&backview_img_url!=null) {

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
                            vehiclePictures.setFront_view(frontview_img_url);
                            vehiclePictures.setBack_view(backview_img_url);
                            vehiclePictures.setLeft_view(leftview_img_url);
                            vehiclePictures.setRight_view(rightview_img_url);

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
                            vehicleDetails.setVehicle_value(userPreferences.getMotorVehicleValue());
                            //Vehicle Picture List
                            VehiclePictures vehiclePictures=new VehiclePictures();

                                vehiclePictures.setFront_view(frontview_img_url);
                                vehiclePictures.setBack_view(backview_img_url);
                                vehiclePictures.setLeft_view(leftview_img_url);
                                vehiclePictures.setRight_view(rightview_img_url);

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

                }else{
                    showMessage("Invalid Entry please upload your image");
                    return;
                }

                break;
        }
    }

    private void mSummary() {

        if(frontview_img_url!=null&&leftview_img_url!=null&&rightview_img_url!=null&&backview_img_url!=null) {


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
            vehiclePictures.setFront_view(frontview_img_url);
            vehiclePictures.setBack_view(backview_img_url);
            vehiclePictures.setLeft_view(leftview_img_url);
            vehiclePictures.setRight_view(rightview_img_url);

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

        }else{
            showMessage("Invalid Entry please upload your image");
            return;
        }




    }




    private void showMessage(String s) {
        Snackbar.make(qb_form_layout4, s, Snackbar.LENGTH_LONG).show();
    }




}
