package com.example.sti_agent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Model.Etic.Travel_Info;
import com.example.sti_agent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class travel_infoListAdapter extends RealmRecyclerViewAdapter<Travel_Info, travel_infoListAdapter.MyViewHolder> {

private Context context;

    Realm realm;
   // int count=0;





    public travel_infoListAdapter(RealmResults<Travel_Info> list , Context context) {
        super(list,true,true);
        this.context=context;
        realm=Realm.getDefaultInstance();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.travelinsure_list,parent,false);
        ButterKnife.bind(this, view);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Travel_Info temp=getItem(position);



      if(temp==null){
          Log.i("Database ","NUll");
      }

        assert temp != null;

        holder.travelmodeList_txt.setText(temp.getTravel_mode());
        holder.tripdurationList_txt.setText(temp.getTrip_duration());
        holder.deptList_txt.setText(temp.getPlace_departure());
        holder.arrivalList_txt.setText(temp.getPlace_arrival());
        holder.countryList_txt.setText(temp.getAddress_country_of_visit());

    }



  /*  //To Delete Record
    private void asyncRemoveRecord(final String id){
        AsyncTask<Void,Void,Void>remoteItem =new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                realm=Realm.getDefaultInstance();

                Monthly monthly=realm.where(Monthly.class).equalTo("id",id).findFirst();
                if(monthly !=null){
                    realm.beginTransaction();
                    monthly.deleteFromRealm();
                    realm.commitTransaction();
                }
                realm.close();
                return null;
            }
        };
        remoteItem.execute();
    }
*/
    public class MyViewHolder extends
            RecyclerView.ViewHolder {



          @BindView(R.id.tripdurationList_txt)
          TextView tripdurationList_txt;
          @BindView(R.id.travelmodeList_txt)
          TextView travelmodeList_txt;
          @BindView(R.id.deptList_txt)
          TextView deptList_txt;
          @BindView(R.id.arrivalList_txt)
          TextView arrivalList_txt;
          @BindView(R.id.countryList_txt)
          TextView countryList_txt;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
