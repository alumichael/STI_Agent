package com.example.sti_agent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Model.Other.insureProduct;
import com.example.sti_agent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class otherInsureListAdapter extends RealmRecyclerViewAdapter<insureProduct, otherInsureListAdapter.MyViewHolder> {

private Context context;

    Realm realm;
   // int count=0;





    public otherInsureListAdapter(RealmResults<insureProduct> list , Context context) {
        super(list,true,true);
        this.context=context;
        realm=Realm.getDefaultInstance();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.otherinsure_list,parent,false);
        ButterKnife.bind(this, view);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final insureProduct temp=getItem(position);



      if(temp==null){
          Log.i("Database ","NUll");
      }

        assert temp != null;

        holder.descriptionList_txt.setText(temp.getDescription());
        holder.selectItemList_txt.setText(temp.getProduct());

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
          @BindView(R.id.descriptionList_txt)
          TextView descriptionList_txt;



        //private Context context;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
