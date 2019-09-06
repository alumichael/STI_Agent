package com.example.sti_agent.retrofit_interface;

import com.example.sti_agent.Model.Auth.AgentDataHead;
import com.example.sti_agent.Model.Auth.ChangePassPost;
import com.example.sti_agent.Model.Auth.LoginModel.UserGetObj;
import com.example.sti_agent.Model.Auth.LoginModel.UserPostObj;
import com.example.sti_agent.Model.Auth.RegisterObj;
import com.example.sti_agent.Model.Vehicle.BrandType.VehicleBrandType;
import com.example.sti_agent.Model.Vehicle.VehicleBrand.Vehicles_Brand;
import com.example.sti_agent.Model.WalletModel.FundWallet;
import com.example.sti_agent.Model.WalletModel.GetWalletFunded;
import com.example.sti_agent.Model.WalletModel.WalletObj;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {
    @POST("users/agent")
    Call<AgentDataHead> register(@Body RegisterObj regPostData , @HeaderMap HashMap<String,String> headerMap);
    @POST("users/login")
    Call<UserGetObj> login(@Body UserPostObj userPostObj , @HeaderMap HashMap<String,String> headerMap);
    @POST("fund-wallet")
    Call<GetWalletFunded> fund_wallet(@Header ("Authorization") String token, @Body FundWallet fundWallet );

    @GET("wallet-history")
    Call<WalletObj> wallet_history(@Header ("Authorization") String token );

    @GET("vehicle-brands")
    Call<Vehicles_Brand> vehicle_brand();

    @GET("vehicle-brand-types/{brand_id}")
    Call<VehicleBrandType> brand_type(@Path("brand_id") int brand_id);

    @POST("change-password")
    Call<ResponseBody> change_password(@Header ("Authorization") String token, @Body ChangePassPost changePassPost);





}
