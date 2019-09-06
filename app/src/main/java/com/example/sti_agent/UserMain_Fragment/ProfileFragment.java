package com.example.sti_agent.UserMain_Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.sti_agent.NetworkConnection;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;


import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public  class ProfileFragment extends Fragment {


    @BindView(R.id.profile_lay)
    LinearLayout mProfileLay;
    @BindView(R.id.relative_layout_photo)
    RelativeLayout mRelativeLayoutPhoto;
    @BindView(R.id.profile_photo)
    CircleImageView mProfilePhoto;
    @BindView(R.id.edit_prof)
    ImageView mEditProf;
    @BindView(R.id.username_txt)
    TextView mUsernameTxt;
    @BindView(R.id.progressBar_profile)
    ProgressBar mProgressBarProfile;
    @BindView(R.id.profile_data_layout)
    ScrollView mProfileDataLayout;
    @BindView(R.id.firstname)
    TextView mFirstname;
    @BindView(R.id.lastname)
    TextView mLastname;
    @BindView(R.id.email)
    TextView mEmail;
    @BindView(R.id.phone_num)
    TextView mPhoneNum;
    @BindView(R.id.pin_profile_txt)
    TextView mPinProfileTxt;
    @BindView(R.id.bank)
    TextView mBank;
    @BindView(R.id.account_name)
    TextView mAccountName;
    @BindView(R.id.account_number)
    TextView mAccountNumber;
    @BindView(R.id.edit_layout)
    ScrollView mEditLayout;
    @BindView(R.id.inputLayoutFirstnameP)
    TextInputLayout mInputLayoutFirstnameP;
    @BindView(R.id.firstname_editxt)
    EditText mFirstnameEditxt;
    @BindView(R.id.inputLayoutLastnameP)
    TextInputLayout mInputLayoutLastnameP;
    @BindView(R.id.lastname_editxt)
    EditText mLastnameEditxt;
    @BindView(R.id.inputLayoutUsername)
    TextInputLayout mInputLayoutUsername;
    @BindView(R.id.username_editxt)
    EditText mUsernameEditxt;
    @BindView(R.id.inputLayoutPhone_NumP)
    TextInputLayout mInputLayoutPhoneNumP;
    @BindView(R.id.phone_num_editxt)
    EditText mPhoneNumEditxt;
    @BindView(R.id.inputLayoutPassword)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.password_editxt)
    EditText password_editxt;
    @BindView(R.id.update_btn)
    Button mUpdateBtn;
    @BindView(R.id.avi1)
    AVLoadingIndicatorView mAvi1;

    private Uri imageUri;
    String address,firstname,phone_num,lastname;

    int PICK_IMAGE_PASSPORT = 1;
    NetworkConnection networkConnection=new NetworkConnection();

    Uri profile_photo_img_uri;
    String personal_img_url;
    UserPreferences userPreferences;


    public ProfileFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, fragmentView);
        userPreferences=new UserPreferences(getContext());

        getUserProfile();
        return fragmentView;
    }



    @OnClick(R.id.edit_prof)
    public void showEditProfile() {

        editProfile();



    }


    private void chooseImageFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_PASSPORT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 || data == null || data.getData() == null) {
            showMessage("No image is selected, try again");
            return;
        }




        showMessage(String.valueOf(requestCode));
        if (networkConnection.isNetworkConnected(getContext())) {
            Random random=new Random();
            String rand= String.valueOf(random.nextInt());
            if (requestCode == 1) {
                profile_photo_img_uri = data.getData();

                try {
                    if (profile_photo_img_uri != null) {
                        String name = "profile_photo"+rand;
                        if (name.equals("")) {
                            showMessage("Try again");

                        } else {
                            mProfilePhoto.setImageBitmap(MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), profile_photo_img_uri));


                            String imageId = MediaManager.get().upload(Uri.parse(profile_photo_img_uri.toString()))
                                    .option("public_id", "user_registration/profile_photos/user_passport" + name)
                                    .unsigned("xbiscrhh").callback(new UploadCallback() {
                                        @Override
                                        public void onStart(String requestId) {
                                            // your code here
                                            mUpdateBtn.setVisibility(View.GONE);
                                            mAvi1.setVisibility(View.VISIBLE);

                                        }

                                        @Override
                                        public void onProgress(String requestId, long bytes, long totalBytes) {
                                            // example code starts here
                                            Double progress = (double) bytes / totalBytes;
                                            // post progress to app UI (e.g. progress bar, notification)
                                            // example code ends here
                                            mAvi1.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onSuccess(String requestId, Map resultData) {
                                            // your code here

                                            showMessage("Image Uploaded Successfully");
                                            Log.i("ImageRequestId ", requestId);
                                            Log.i("ImageUrl ", String.valueOf(resultData.get("url")));
                                            mAvi1.setVisibility(View.GONE);
                                            mUpdateBtn.setVisibility(View.VISIBLE);
                                            personal_img_url = String.valueOf(resultData.get("url"));


                                        }

                                        @Override
                                        public void onError(String requestId, ErrorInfo error) {
                                            // your code here
                                            showMessage("Error: " + error.toString());
                                            Log.i("Error: ", error.toString());

                                            mUpdateBtn.setVisibility(View.VISIBLE);
                                            mAvi1.setVisibility(View.GONE);
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

    private void editProfile() {
        mProfileDataLayout.setVisibility(View.GONE);
        mEditProf.setVisibility(View.GONE);
        mProfilePhoto.setClickable(true);
        mEditLayout.setVisibility(View.VISIBLE);


            mPhoneNumEditxt.setText(userPreferences.getAgentEmail());
            mUsernameEditxt.setText(userPreferences.getAgentUsername());
            mFirstnameEditxt.setText(userPreferences.getAgentFirstName());
            mLastnameEditxt.setText(userPreferences.getAgentLastName());


        mProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImageFile();
            }
        });




        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Request for Post Request for Profile Update


                showMessage("Updated Successfully");

                mProfilePhoto.setClickable(false);
                mEditLayout.setVisibility(View.GONE);


                mProfileDataLayout.setVisibility(View.VISIBLE);
                mEditProf.setVisibility(View.VISIBLE);

            }
        });
    }


    private void showMessage(String s) {
        Snackbar.make(mProfileLay, s, Snackbar.LENGTH_SHORT).show();
    }

    private void getUserProfile() {
        //Getting profile from Pref
        mFirstname.setText("FirstName: "+userPreferences.getAgentFirstName());
        mLastname.setText("LastName: "+userPreferences.getAgentLastName());
        mUsernameTxt.setText(userPreferences.getAgentUsername());
        mEmail.setText("Email: "+userPreferences.getAgentEmail());
        mPhoneNum.setText("Phone No: "+userPreferences.getAgentPhoneNUM());
        mPinProfileTxt.setText("Pin: "+userPreferences.getAgentPin());
        mBank.setText("Bank Name: "+userPreferences.getBank());
        mAccountName.setText("Acct Name: "+userPreferences.getAccountName());
        mAccountNumber.setText("Acct No: "+userPreferences.getAccountNumber());

        mProgressBarProfile.setVisibility(View.VISIBLE);
        if(personal_img_url==null) {
            Glide.with(getContext()).load(userPreferences.getAgentProfileImg()).apply(new RequestOptions().fitCenter().circleCrop()).into(mProfilePhoto);
        }else{
            Glide.with(getContext()).load(personal_img_url).apply(new RequestOptions().fitCenter().circleCrop()).into(mProfilePhoto);

        }
        mProgressBarProfile.setVisibility(View.GONE);



    }



}
