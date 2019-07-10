package com.example.sti_agent;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.sti_agent.UserMain_Fragment.Fragment_Customers;
import com.example.sti_agent.UserMain_Fragment.Fragment_Dashboard;
import com.example.sti_agent.UserMain_Fragment.Fragment_Transactions;
import com.example.sti_agent.UserMain_Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolBar;

   /* @BindView(R.id.message)
    TextView mTextMessage;*/


    Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    applyToolbar("Dashboard","Supporting your clients");
                    fragment = new Fragment_Dashboard();
                    showFragment(fragment);
                    return true;
                case R.id.navigation_customer:
                   // mTextMessage.setText(R.string.title_customer);
                    applyToolbarChildren("Customers Board","Manage your clients");
                    fragment = new Fragment_Customers();
                    showFragment(fragment);

                    return true;
                case R.id.navigation_transaction:
                   // mTextMessage.setText(R.string.title_transaction);
                    applyToolbarChildren("Transactions","Check previous transactions");
                    fragment = new Fragment_Transactions();
                    showFragment(fragment);
                    return true;

                case R.id.navigation_profile:
                   // mTextMessage.setText(R.string.title_profile);
                    applyToolbarChildren("Profile","Agent information");
                    fragment = new ProfileFragment();
                    showFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ButterKnife.bind(this);


        applyToolbar("Dashboard","Supporting your clients");


        fragment = new Fragment_Dashboard();
        showFragment(fragment);
    }



    private void applyToolbar(String title,String subtitle) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
        getSupportActionBar().setElevation(0);

    }

    private void applyToolbarChildren(String title,String subtitle) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);

        //setting Elevation for > API 21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolBar.setElevation(10f);
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_about) {


            return true;
        } else if (itemId == R.id.profile) {

            applyToolbarChildren("Profile","Agent information");
            fragment = new ProfileFragment();
            showFragment(fragment);


        } else if(itemId==R.id.action_faq){

            return true;
        }else if(itemId==R.id.action_share){

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");

            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "STI Agent Mobile");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Reaching to customers is our duty!");

            startActivity(Intent.createChooser(sharingIntent, "Share via"));



            return true;
        }else if(itemId==R.id.action_update){
            goPlayStore();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goPlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/"));
        startActivity(intent);
    }

}
