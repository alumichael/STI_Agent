package com.example.sti_agent.UserMain_Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sti_agent.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.wang.avi.AVLoadingIndicatorView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public  class ProfileFragment extends Fragment {

    String address;
    @BindView(R.id.addr_editxt)
    EditText addr_editxt;
    String firstname;
    @BindView(R.id.firstname_editxt)
    EditText firstname_editxt;
    private Uri imageUri;
    @BindView(R.id.inputLayoutAddr)
    TextInputLayout inputLayoutAddr;
    @BindView(R.id.inputLayoutFirstnameP)
    TextInputLayout inputLayoutFirstnameP;
    @BindView(R.id.inputLayoutLastnameP)
    TextInputLayout inputLayoutLastnameP;
    @BindView(R.id.inputLayoutPhone_NumP)
    TextInputLayout inputLayoutPhone_NumP;

    String phone_num;
    @BindView(R.id.phone_num_editxt)
    EditText phone_num_editxt;
    @BindView(R.id.profile_photo)
    CircleImageView profile_photo;
    @BindView(R.id.update_btn)
    Button update_btn;




    String lastname;
    @BindView(R.id.lastname_editxt)
    EditText lastname_editxt;

    @BindView(R.id.profile_lay)
    LinearLayout profile_lay;

    @BindView(R.id.edit_prof)
    ImageView edit_prof;
    @BindView(R.id.avi1)
    AVLoadingIndicatorView progressBar_profile;

    @BindView(R.id.expanded_list_profile)
    ExpandableListView expanded_list_profile;

    private String[] groups;
    private String[][] children;
    Activity activity;

    ExpandableListAdapter expandableListAdapter;


    public ProfileFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, fragmentView);
        getUserProfile();

        activity=getActivity();


        // preparing list data
        prepareListData();

        expandableListAdapter=new ExpandableListAdapter(activity,groups, children);

        expanded_list_profile.setAdapter(expandableListAdapter);
        expanded_list_profile.setGroupIndicator(null);



        return fragmentView;
    }



    @OnClick(R.id.edit_prof)
    public void showEditProfile() {

        editProfile();



    }

    private void editProfile() {
        expanded_list_profile.setVisibility(View.GONE);
        edit_prof.setVisibility(View.GONE);

        profile_photo.setClickable(true);

        inputLayoutAddr.setVisibility(View.VISIBLE);
        inputLayoutFirstnameP.setVisibility(View.VISIBLE);
        inputLayoutLastnameP.setVisibility(View.VISIBLE);
        inputLayoutPhone_NumP.setVisibility(View.VISIBLE);
        update_btn.setVisibility(View.VISIBLE);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Request for Post Request for Profile Update


                showMessage("Updated Successfully");



                profile_photo.setClickable(false);

                inputLayoutAddr.setVisibility(View.GONE);
                inputLayoutFirstnameP.setVisibility(View.GONE);
                inputLayoutLastnameP.setVisibility(View.GONE);
                inputLayoutPhone_NumP.setVisibility(View.GONE);
                update_btn.setVisibility(View.GONE);

                expanded_list_profile.setVisibility(View.VISIBLE);
                edit_prof.setVisibility(View.VISIBLE);

            }
        });






    }


    private void showMessage(String s) {
        Snackbar.make(profile_lay, s, Snackbar.LENGTH_SHORT).show();
    }

    private void getUserProfile() {




        /*progressBar_profile.setVisibility(View.VISIBLE);
        //Fetch from API
        //Try to get the USername from Shared Pref and do a Post Request for it
        //auth = FirebaseAuth.getInstance().getCurrentUser();
        if (auth != null) {

            return;
        }
        Snackbar.make(this.profile_lay, "Null User, Try to Re-Login", Snackbar.LENGTH_SHORT).setAction((CharSequence) "Action", null).show();
    */

    }




    private void prepareListData() {

        groups = new String[] { "First Name: "+"Michael", "Last Name: "+"Boluwaji Oluwa Damilola", "Agent Address: "+"No 1, Ajefemi Street, Ketu Lagos State Nigeria", "Agent Phone Number: "+"+2349012345678" };

        children = new String [][] {
                { "Michael" },
                { "Boluwaji Oluwadamilola" },
                { "No 1, Ajefemi Street, Ketu Lagos State Nigeria" },
                { "+2349087744857" }

        };


    }


}

class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final LayoutInflater inf;
    private String[] groups;
    private String[][] children;
    private Context context;
    public ExpandableListAdapter(Context context,String[] groups, String[][] children) {
        this.groups = groups;
        this.children = children;

        inf = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inf.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getChild(groupPosition, childPosition).toString());

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inf.inflate(R.layout.list_group, parent, false);

            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolder {
        TextView text;
    }
}