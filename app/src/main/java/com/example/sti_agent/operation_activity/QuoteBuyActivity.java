package com.example.sti_agent.operation_activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sti_agent.Constant;
import com.example.sti_agent.R;
import com.example.sti_agent.operation_fragment.QuoteBuyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuoteBuyActivity extends AppCompatActivity {



    @BindView(R.id.quotebuytoolbar)
    Toolbar toolBar;

   /* @BindView(R.id.message)
    TextView mTextMessage;*/


    Fragment fragment;

    String title="";
    String sub_title="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoute_buy);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        title=intent.getStringExtra(Constant.CARD_OPTION_TITLE);
        sub_title=intent.getStringExtra(Constant.CARD_OPTION_SUBTITLE);
        applyToolbarChildren(title,sub_title);


        fragment = new QuoteBuyFragment();
        showFragment(fragment);


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
        ft.replace(R.id.fragment_quote_container, fragment);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
