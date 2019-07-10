package com.example.sti_agent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Model.Swiss.AdditionInsured;
import com.example.sti_agent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class AddInsuredListAdapter extends RealmRecyclerViewAdapter<AdditionInsured, AddInsuredListAdapter.MyViewHolder> {

private Context context;

    Realm realm;
   // int count=0;





    public AddInsuredListAdapter(RealmResults<AdditionInsured> list , Context context) {
        super(list,true,true);
        this.context=context;
        realm=Realm.getDefaultInstance();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.addinsure_list,parent,false);
        ButterKnife.bind(this, view);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final AdditionInsured temp=getItem(position);



      if(temp==null){
          Log.i("Database ","NUll");
      }

        assert temp != null;

        holder.firstnameList_txt.setText(temp.getFirst_name());
        holder.lastnameList_txt.setText(temp.getLast_name());
        holder.phoneList_txt.setText(temp.getPhone());
        holder.disableList_txt.setText(temp.getDisability());
        holder.maritalList_txt.setText(temp.getMarital_status());




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



          @BindView(R.id.firstnameList_txt)
          TextView firstnameList_txt;
          @BindView(R.id.lastnameList_txt)
          TextView lastnameList_txt;
          @BindView(R.id.phoneList_txt)
          TextView phoneList_txt;
          @BindView(R.id.disableList_txt)
          TextView disableList_txt;
          @BindView(R.id.maritalList_txt)
          TextView maritalList_txt;



        //private Context context;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
