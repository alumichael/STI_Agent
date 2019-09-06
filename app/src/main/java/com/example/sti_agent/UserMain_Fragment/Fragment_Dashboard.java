package com.example.sti_agent.UserMain_Fragment;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sti_agent.GridSpacingItemDecoration;
import com.example.sti_agent.Model.Card;
import com.example.sti_agent.Model.Errors.APIError;
import com.example.sti_agent.Model.Errors.ErrorUtils;
import com.example.sti_agent.Model.Etic.Travel_Info;
import com.example.sti_agent.Model.ServiceGenerator;
import com.example.sti_agent.Model.WalletModel.WalletObj;
import com.example.sti_agent.Model.WalletModel.Wallet_History;
import com.example.sti_agent.NetworkConnection;
import com.example.sti_agent.R;
import com.example.sti_agent.UserPreferences;
import com.example.sti_agent.adapter.CardAdapter;
import com.example.sti_agent.adapter.WalletHistoryAdapter;
import com.example.sti_agent.adapter.travel_infoListAdapter;
import com.example.sti_agent.retrofit_interface.ApiInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Dashboard extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.dash_layout)
    CoordinatorLayout dash_layout;
    @BindView(R.id.wallet_balance)
    TextView wallet_balance;
    @BindView(R.id.wallet_kobo)
    TextView wallet_kobo;
    @BindView(R.id.fund_wallet_txt)
    TextView fund_wallet_txt;
    @BindView(R.id.wallet_blance_card)
    MaterialCardView wallet_blance_card;
    @BindView(R.id.fund_wallet_card)
    MaterialCardView fund_wallet_card;
    @BindView(R.id.progressbar)
    AVLoadingIndicatorView progressbar;
    private CardAdapter cardAdapter;
    private List<Card> cardList;
    UserPreferences userPreferences;
    List<Wallet_History> wallet_histories;
    Fragment fragment;
    WalletHistoryAdapter walletHistoryAdapter;



    public Fragment_Dashboard() {
        // Required empty public constructor
    }
    NetworkConnection networkConnection=new NetworkConnection();
    ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Dashboard newInstance(String param1, String param2) {
        Fragment_Dashboard fragment = new Fragment_Dashboard();
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
        View view=inflater.inflate(R.layout.fragment_fragment__dashboard, container, false);
        ButterKnife.bind(this,view);
        userPreferences=new UserPreferences(getActivity());

        setWallet_balance();
        getWalletHistroy();
        cardList = new ArrayList<>();
        cardAdapter = new CardAdapter(getContext(), cardList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);

//        populating the card
        insertElement();
        setAction();


        return  view;
    }

    private void setAction(){
       // wallet_blance_card.setOnClickListener(this);
        fund_wallet_card.setOnClickListener(this);
    }
    private  void setWallet_balance(){
        String balance=userPreferences.getWalletBalance();
        int dotIndex=balance.indexOf(".");
        int len_balance=balance.length()-1;
        String kobo=balance.substring(dotIndex,len_balance);
        String naira=balance.substring(0,dotIndex);

        wallet_balance.setText(naira);
        wallet_kobo.setText(kobo);
    }

    private void insertElement() {
//        referencing drawable for the logo
        int[] icons = new int[]{
                R.drawable.ic_quote_buy_img,
                R.drawable.ic_make_claim,
                R.drawable.ic_recent_actors_black_24dp,
                R.drawable.ic_rotate_90_degrees_ccw_black_24dp,
                R.drawable.ic_person_add_black_24dp
        };

        Card m = new Card("Quote and Buy Insurance Policy", icons[0]);
        cardList.add(m);

        m = new Card("Make a Claim", icons[1]);
        cardList.add(m);

        m = new Card("Customer Management", icons[2]);
        cardList.add(m);

        m = new Card("Renewal of Insurance Policy", icons[3]);
        cardList.add(m);

        m = new Card("Register New Customer", icons[4]);
        cardList.add(m);

    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private void getWalletHistroy(){
        if(networkConnection.isNetworkConnected(getContext())) {

            progressbar.setVisibility(View.VISIBLE);
            Call<WalletObj> call = client.wallet_history("Token " + userPreferences.getUserToken());

            call.enqueue(new Callback<WalletObj>() {
                @Override
                public void onResponse(Call<WalletObj> call, Response<WalletObj> response) {
                    if (!response.isSuccessful()) {

                        try {
                            APIError apiError = ErrorUtils.parseError(response);

                            showMessage("Invalid Fetch: " + apiError.getErrors());
                            Log.i("Invalid Fetch", apiError.getErrors().toString());
                            Log.i("Invalid FetchErrorBody", response.errorBody().toString());

                        } catch (Exception e) {
                            Log.i("InvalidEntry", e.getMessage());
                            showMessage("Failed to fetch wallet history");

                        }
                        progressbar.setVisibility(View.GONE);
                        showMessage("Failed to fetch wallet history");

                    }

                    wallet_histories=response.body().getWallet_History();
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<WalletObj> call, Throwable t) {
                    showMessage("Failed to fetch wallet history " + t.getMessage());
                    Log.i("GEtError", t.getMessage());

                    progressbar.setVisibility(View.GONE);
                }
            });
        }else{
            showMessage("No Internet Connection");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fund_wallet_card:

                fragment =new Fragment_FundWallet();
                showFragment(fragment);

                break;
           /* case R.id.wallet_blance_card:
                break;*/
        }
    }



    private void showMessage(String s) {
        Snackbar.make(dash_layout, s, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.wallet_blance_card)
    void showWalletHistory() {
        try {
            View view = getLayoutInflater().inflate(R.layout.fragment_bottom_wallet, null);
            final TextView textView = (TextView) view.findViewById(R.id.detail);
            final RecyclerView recycler_wallet_history = (RecyclerView) view.findViewById(R.id.recycler_wallet_history);
            final ImageView close = (ImageView) view.findViewById(R.id.close);
            BottomSheetDialog dialog = new BottomSheetDialog(getContext());

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


            walletHistoryAdapter = new WalletHistoryAdapter(getContext(), wallet_histories);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), RecyclerView.VERTICAL, false);
            recycler_wallet_history.setLayoutManager(linearLayoutManager);
            recycler_wallet_history.setItemAnimator(new DefaultItemAnimator());
            recycler_wallet_history.setAdapter(walletHistoryAdapter);

            dialog.setContentView(view);
            dialog.show();
        }catch (Exception e){
            showMessage("Something went wrong");
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


}
