package com.example.sti_agent;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sti_agent.Model.Auth.AgentDataHead;
import com.example.sti_agent.Model.Auth.LoginModel.UserGetObj;
import com.example.sti_agent.Model.Auth.LoginModel.UserPostData;
import com.example.sti_agent.Model.Auth.LoginModel.UserPostObj;
import com.example.sti_agent.Model.Auth.RegisterObj;
import com.example.sti_agent.Model.Auth.User;
import com.example.sti_agent.Model.Errors.APIError;
import com.example.sti_agent.Model.Errors.ErrorUtils;
import com.example.sti_agent.Model.ServiceGenerator;
import com.example.sti_agent.retrofit_interface.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.sti_agent.operation_fragment.MotorInsurance.MotorInsureFragment1.isValidEmailAddress;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.signin_lyout)
    FrameLayout signin_lyout;
    @BindView(R.id.inputLayoutAgentEmail)
    TextInputLayout mInputLayoutAgentEmail;
    @BindView(R.id.agent_email_editxt)
    EditText mAgentEmailEditxt;
    @BindView(R.id.inputLayoutPassword)
    TextInputLayout mInputLayoutPassword;
    @BindView(R.id.password_editxt)
    EditText mPasswordEditxt;
    @BindView(R.id.sign_in_btn)
    Button mSignInBtn;
    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;
    UserPreferences userPreferences;


    String agent_email="";
    NetworkConnection networkConnection=new NetworkConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mRegister.setPaintFlags(mRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mRegister.setText("Click to Register");
        userPreferences = new UserPreferences(this);
        Intent intent=getIntent();
        agent_email=intent.getStringExtra(Constant.AGENT_EMAIL);
        setUp();

        mSignInBtn.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    private void setUp(){
        mAgentEmailEditxt.setText(agent_email);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sign_in_btn:
                ValidateForm();

                break;

            case R.id.register:
                startActivity(new Intent(getApplicationContext(),SignUp.class));
                finish();
                break;

        }

    }

    private void ValidateForm() {

        if (networkConnection.isNetworkConnected(this)) {
            boolean isValid = true;

            if (mAgentEmailEditxt.getText().toString().isEmpty()&&mInputLayoutAgentEmail.isClickable()) {
                mInputLayoutAgentEmail.setError("Email is required!");
                isValid = false;
            } else if (!isValidEmailAddress(mAgentEmailEditxt.getText().toString())&&mInputLayoutAgentEmail.isClickable()) {
                mInputLayoutAgentEmail.setError("Valid Email is required!");
                isValid = false;
            } else {
                mInputLayoutAgentEmail.setErrorEnabled(false);
            }
            if (mPasswordEditxt.getText().toString().isEmpty()&&mInputLayoutPassword.isClickable()) {
                mInputLayoutPassword.setError("Password is required!");
                isValid = false;
            } else if (mPasswordEditxt.getText().toString().trim().length()<6 && mInputLayoutPassword.isClickable()) {
                mInputLayoutPassword.setError("Your Password must not less than 6 character");
                isValid = false;
            } else {
                mInputLayoutPassword.setErrorEnabled(false);
            }


            if (isValid) {

                //Post Request to Api

                sendData();
              }

          return;
        }
        showMessage("No Internet connection discovered!");
    }


    private void sendData(){
        mSignInBtn.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);

        UserPostData userPostData=new UserPostData(mAgentEmailEditxt.getText().toString(),
                mPasswordEditxt.getText().toString()

        );
        UserPostObj userPostObj=new UserPostObj(userPostData);

        sentNetworkRequest(userPostObj);

    }

    private  void sentNetworkRequest(UserPostObj userPostObj){
        try {
            //To create retrofit instance
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json;charset=UTF-8");

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<UserGetObj> call = client.login(userPostObj, hashMap);

            call.enqueue(new Callback<UserGetObj>() {
                @Override
                public void onResponse(Call<UserGetObj> call, Response<UserGetObj> response) {
                    try {
                        if (!response.isSuccessful()) {

                            try {
                                APIError apiError = ErrorUtils.parseError(response);

                                showMessage("Invalid Entry: " + apiError.getErrors());
                                Log.i("Invalid EntryK", apiError.getErrors().toString());
                                Log.i("Invalid Entry", response.errorBody().toString());

                            } catch (Exception e) {
                                Log.i("InvalidEntry", e.getMessage());
                                showMessage("Invalid Entry");

                            }
                            mSignInBtn.setVisibility(View.VISIBLE);
                            progressbar.setVisibility(View.GONE);
                            return;
                        }

                        String agent_email = response.body().getUser().getEmail();
                        String username = String.valueOf(response.body().getUser().getUsername());
                        String bio = String.valueOf(response.body().getUser().getBio());
                        String prof_img = response.body().getUser().getImage();
                        String phone_no = response.body().getUser().getPhone();
                        String pin = String.valueOf(response.body().getUser().getPin());
                        String firstname = response.body().getUser().getFirstName();
                        String lastname = response.body().getUser().getLastName();
                        String wallet_balance = response.body().getWalletBalance();
                        String bank = String.valueOf(response.body().getPayoutAccount().getBank());
                        String acct_name = String.valueOf(response.body().getPayoutAccount().getAccountName());
                        String acct_no = String.valueOf(response.body().getPayoutAccount().getAccountNumber());
                        String user_id = String.valueOf(response.body().getUser().getId());
                        String user_token = response.body().getUser().getToken();

                        Log.i("Agent", response.body().getUser().getFirstName());
                        Log.i("AgentEmail", agent_email);
                        Log.i("AgentWallet", response.body().getWalletBalance());
                        Log.i("AgentImage", response.body().getUser().getImage());
                        Log.i("AgentToken", response.body().getUser().getToken());
                        try {
                            userPreferences.setAgentEmail(agent_email);
                            userPreferences.setAgentUsername(username);
                            userPreferences.setAgentProfileImg(prof_img);
                            userPreferences.setAgentPhoneNUM(phone_no);
                            userPreferences.setAgentPin(pin);
                            userPreferences.setAgentFirstName(firstname);
                            userPreferences.setAgentLastName(lastname);
                            userPreferences.setWalletBalance(wallet_balance);
                            userPreferences.setBank(bank);
                            userPreferences.setAccountName(acct_name);
                            userPreferences.setAccountNumber(acct_no);
                            userPreferences.setUserId(user_id);
                            userPreferences.setUserToken(user_token);
                        }catch (Exception e){
                            Log.i("PrefError", e.getMessage());
                            mSignInBtn.setVisibility(View.VISIBLE);
                            progressbar.setVisibility(View.GONE);
                        }

                        mSignInBtn.setVisibility(View.VISIBLE);
                        progressbar.setVisibility(View.GONE);
                        if (agent_email != null) {
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            intent.putExtra(Constant.AGENT_EMAIL, agent_email);
                            startActivity(intent);
                            finish();
                        } else {
                            showMessage("Error: " + response.body());
                        }
                    } catch (Exception e) {
                        showMessage("Login Failed: " + e.getMessage());
                        mSignInBtn.setVisibility(View.VISIBLE);
                        progressbar.setVisibility(View.GONE);
                        Log.i("Login Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<UserGetObj> call, Throwable t) {
                    showMessage("Login Failed " + t.getMessage());
                    Log.i("GEtError", t.getMessage());
                    mSignInBtn.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }


    }

    private void showMessage(String s) {
        Snackbar.make(signin_lyout, s, Snackbar.LENGTH_LONG).show();
    }
}
