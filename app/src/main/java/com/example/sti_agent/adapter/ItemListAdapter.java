package com.example.sti_agent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Model.AllRisk.ItemDetail;
import com.example.sti_agent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ItemListAdapter extends RealmRecyclerViewAdapter<ItemDetail, ItemListAdapter.MyViewHolder> {

private Context context;

    Realm realm;
   // int count=0;





    public ItemListAdapter(RealmResults<ItemDetail> list , Context context) {
        super(list,true,true);
        this.context=context;
        realm=Realm.getDefaultInstance();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.itemisure_list,parent,false);
        ButterKnife.bind(this, view);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ItemDetail temp=getItem(position);



      if(temp==null){
          Log.i("Database ","NUll");
      }

        assert temp != null;

        holder.selectItemList_txt.setText(temp.getItem());
        holder.descList_txt.setText(temp.getDesc_item());
        holder.startDateList_txt.setText(temp.getStartDate());
        holder.serialList_txt.setText(temp.getSerial());
        holder.itemvalueList_txt.setText(temp.getValue());




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



          @BindView(R.id.selectItemList_txt)
          TextView selectItemList_txt;
          @BindView(R.id.descList_txt)
          TextView descList_txt;
          @BindView(R.id.startDateList_txt)
          TextView startDateList_txt;
          @BindView(R.id.serialList_txt)
          TextView serialList_txt;
          @BindView(R.id.itemvalueList_txt)
          TextView itemvalueList_txt;



        //private Context context;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
