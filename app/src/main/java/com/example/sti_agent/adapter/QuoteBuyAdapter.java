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
import com.example.sti_agent.Forms.OtherInsuredForm;
import com.example.sti_agent.Forms.SwissForm;
import com.example.sti_agent.Import.ImportingForm;
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
                    String[] entry = {"Direct Input", "Import Excel file"};
                    builder.setItems(entry, (dialog, option) -> {
                        switch (option) {
                            case 0:
                                // direct entry
                                nextActivity(quotecardList.get(pos).getTitle(), MotorInsuredForm.class);
                                dialog.dismiss();
                                break;

                            case 1: // export

                                nextActivity("Import Motor Detail", ImportingForm.class);
                                dialog.dismiss();
                                
                                break;
                                
                        }
                    });
// create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                         break;
                case "SWIS-F Insurance":

                    nextActivity(quotecardList.get(pos).getTitle(), SwissForm.class);

                   break;
                case "Marine Insurance":
                    nextActivity(quotecardList.get(pos).getTitle(), MarineForm.class);

                    break;
                case "All Risks Insurance":
                    nextActivity(quotecardList.get(pos).getTitle(), AllRiskForm.class);
                    
                    break;
                case "ETIC Insurance":
                    nextActivity(quotecardList.get(pos).getTitle(), EticForm.class);
                    break;

                case "Other Insurance":
                    nextActivity(quotecardList.get(pos).getTitle(), OtherInsuredForm.class);
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
