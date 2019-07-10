package com.example.sti_agent.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Constant;
import com.example.sti_agent.Forms.AllRiskForm;
import com.example.sti_agent.Forms.EticForm;
import com.example.sti_agent.Forms.MarineForm;
import com.example.sti_agent.Forms.SwissForm;
import com.example.sti_agent.Model.QuoteCard;
import com.example.sti_agent.R;
import com.example.sti_agent.interfaces.ItemClickListener;
import com.example.sti_agent.Forms.MotorInsuredForm;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuoteBuyAdapter extends RecyclerView.Adapter<QuoteBuyAdapter.MyViewHolder> {

private Context context;
private List<QuoteCard> quotecardList;

public QuoteBuyAdapter(Context context, List<QuoteCard> quotecardList) {
        this.context = context;
        this.quotecardList = quotecardList;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qoute_buy_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        QuoteCard quotecardOption = quotecardList.get(i);

//        bind data to view
        holder.optionTitle.setText(quotecardOption.getTitle());
        holder.quotecardLogo.setImageResource(quotecardOption.getThumbnail());


        holder.setItemClickListener(pos -> {
                switch (quotecardList.get(pos).getTitle()) {
                case "Motor Insurance":

                    // setup the alert builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Choose Mode of Entry");
// add a list
                    String[] entry = {"Direct Input", "Import CSV file"};
                    builder.setItems(entry, (dialog, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), MotorInsuredForm.class);
                                dialog.dismiss();

                            case 1: // export
                                dialog.dismiss();
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                         break;
                case "SWIS-F Insurance":
                    // setup the alert builder
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setTitle("Choose Mode of Entry");
// add a list
                    String[] entry1 = {"Direct Input", "Import CSV file"};
                    builder1.setItems(entry1, (dialog1, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), SwissForm.class);
                                dialog1.dismiss();

                            case 1: // export
                                dialog1.dismiss();
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog1 = builder1.create();
                    dialog1.show();
                    
                    
                    break;
                case "Marine Insurance":
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                    builder2.setTitle("Choose Mode of Entry");
// add a list
                    String[] entry2 = {"Direct Input", "Import CSV file"};
                    builder2.setItems(entry2, (dialog2, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), MarineForm.class);
                                dialog2.dismiss();

                            case 1: // export
                                dialog2.dismiss();
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();




                    break;
                case "All Risks Insurance":
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
                    builder3.setTitle("Choose Mode of Entry");
// add a list
                    String[] entry3 = {"Direct Input", "Import CSV file"};
                    builder3.setItems(entry3, (dialog3, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), AllRiskForm.class);
                                dialog3.dismiss();

                            case 1: // export
                                dialog3.dismiss();
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog3 = builder3.create();
                    dialog3.show();

                    
                    break;
                case "ETIC Insurance":
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
                    builder4.setTitle("Choose Mode of Entry");
// add a list
                    String[] entry4 = {"Direct Input", "Import CSV file"};
                    builder4.setItems(entry4, (dialog4, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), EticForm.class);
                                dialog4.dismiss();

                            case 1: // export
                                dialog4.dismiss();
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog4 = builder4.create();
                    dialog4.show();

                    break;

                case "Other Insurance":
                    AlertDialog.Builder builder5 = new AlertDialog.Builder(context);
                    builder5.setTitle("Choose Mode of Entry");
// add a list
                    String[] entry5 = {"Direct Input", "Import CSV file"};
                    builder5.setItems(entry5, (dialog5, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), EticForm.class);
                                dialog5.dismiss();

                            case 1: // export
                                dialog5.dismiss();
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog5 = builder5.create();
                    dialog5.show();
                        break;

                }

                });
        }



private void nextActivity(String title, Class quotecardActivityClass) {
        Intent i = new Intent(context, quotecardActivityClass);
        i.putExtra(Constant.CARD_OPTION_TITLE, title);
        context.startActivity(i);

        }

@Override
public int getItemCount() {
        return quotecardList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.option_title_tv)
    TextView optionTitle;
    @BindView(R.id.option_logo)
    ImageView quotecardLogo;
    

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
