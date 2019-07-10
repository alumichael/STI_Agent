package com.example.sti_agent.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Constant;
import com.example.sti_agent.Model.Card;
import com.example.sti_agent.R;
import com.example.sti_agent.interfaces.ItemClickListener;
import com.example.sti_agent.operation_activity.MakeClaimActivity;
import com.example.sti_agent.operation_activity.PolicyRenewalActivity;
import com.example.sti_agent.operation_activity.QuoteBuyActivity;
import com.example.sti_agent.operation_fragment.Claim.ClaimFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

private Context context;
private List<Card> cardList;

public CardAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Card cardOption = cardList.get(i);

//        bind data to view
        holder.optionTitle.setText(cardOption.getTitle());
        holder.cardLogo.setImageResource(cardOption.getThumbnail());


        holder.setItemClickListener(pos -> {
                switch (cardList.get(pos).getTitle()) {
                case "Quote and Buy Insurance Policy":
                        nextActivity("Quote and Buy", "Select insurance product", QuoteBuyActivity.class);
                        break;
                case "Make a Claim":
                    nextActivity("Claim", "Making claims for clients", MakeClaimActivity.class);
                        break;
                case "Customer Management":
                        //nextActivity(cardList.get(pos).getTitle(), cardList.get(pos).getDesc(), AlarmActivity.class);
                        break;
                case "Renewal of Insurance Policy":
                    nextActivity("Renew Policy", "Don't neglect the policy", PolicyRenewalActivity.class);
                        break;
                case "Register New Customer":
                        //nextActivity(cardList.get(pos).getTitle(), cardList.get(pos).getDesc(), NoteActivity.class);
                        break;

                }

                });
        }

private void nextActivity(String title,String subTitle, Class cardActivityClass) {
        Intent i = new Intent(context, cardActivityClass);
        i.putExtra(Constant.CARD_OPTION_TITLE, title);
        i.putExtra(Constant.CARD_OPTION_SUBTITLE, subTitle);
        context.startActivity(i);

        }

@Override
public int getItemCount() {
        return cardList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.option_title_tv)
    TextView optionTitle;
    @BindView(R.id.option_logo)
    ImageView cardLogo;
    

    ItemClickListener itemClickListener;

    MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
}
