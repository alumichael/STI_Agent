package com.example.sti_agent.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sti_agent.Model.WalletModel.Wallet_History;
import com.example.sti_agent.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletHistoryAdapter extends RecyclerView.Adapter<WalletHistoryAdapter.MyViewHolder> {

    private Context context;
    private List<Wallet_History> walletList;
    String currency="₦";


     String[] COLORS = new String[]{"#236301", "#730292", "#eb880e",
             "#8F041F","#0288D1","#689F38","#0F0F0E",
             "#272c50"};

    public WalletHistoryAdapter(Context context, List<Wallet_History> walletList) {
        this.context = context;
        this.walletList = walletList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_history_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Wallet_History walletOption = walletList.get(i);

        //Color randomize
        Random random=new Random();
        int rand = random.nextInt(walletList.size())+1;


        holder.header.setBackgroundColor(Color.parseColor(COLORS[rand]));

//        bind data to view

        String fundedTxt="Funded Amount :"+currency+walletOption.getAmount();
        holder.fundedAmount.setText(fundedTxt);
        String balanceTxt="Balance :"+currency+walletOption.getNewBalance();
        holder.balanceAmount.setText(balanceTxt);
        holder.descriptn.setText(walletOption.getDescription());
        String dateTimext="Date/Time: ";
        holder.dateTime.setText(String.format("%s%s", dateTimext, walletOption.getCreatedAt()));
        String typetxt="Type: ";
        holder.type.setText(String.format("%s%s",typetxt,walletOption.getType()));



    }


    @Override
    public int getItemCount() {
        return walletList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.funded_amount)
        TextView fundedAmount;
        @BindView(R.id.balance_amount)
        TextView balanceAmount;
        @BindView(R.id.descriptn)
        TextView descriptn;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.date_time)
        TextView dateTime;
        @BindView(R.id.header)
        LinearLayout header;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
