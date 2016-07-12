package com.example.chen.myapplication.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.page.Home;
import com.example.chen.myapplication.page.Personal_settings;
import com.example.chen.myapplication.R;

/**
 * Created by Chen on 2016/6/6.
 */
public class activity_fragment extends Activity implements View.OnClickListener {

    FragmentManager mFragmentManager = null;

    RelativeLayout activity_title_tag1, activity_title_tag2, activity_title_tag3, activity_title_tag4;

    Home fg1, fg2, fg3;

    Personal_settings fg4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        mFragmentManager = getFragmentManager();

        bindViews();

        activity_title_tag1.performClick();

    }

    private void bindViews() {

        activity_title_tag1 = (RelativeLayout) findViewById(R.id.activity_title_tag_relativelayout1);
        activity_title_tag2 = (RelativeLayout) findViewById(R.id.activity_title_tag_relativelayout2);
        activity_title_tag3 = (RelativeLayout) findViewById(R.id.activity_title_tag_relativelayout3);
        activity_title_tag4 = (RelativeLayout) findViewById(R.id.activity_title_tag_relativelayout4);

        activity_title_tag1.setOnClickListener(this);
        activity_title_tag2.setOnClickListener(this);
        activity_title_tag3.setOnClickListener(this);
        activity_title_tag4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(mFragmentTransaction);

        switch (v.getId()) {
            case R.id.activity_title_tag_relativelayout1:

                activity_title_tag1.setSelected(true);

                if (fg1 == null) {
                    fg1 = new Home();
                    mFragmentTransaction.add(R.id.activity_framelayout, fg1);
                } else {
                    mFragmentTransaction.show(fg1);
                }
                break;

            case R.id.activity_title_tag_relativelayout2:

                activity_title_tag2.setSelected(true);

                if (fg2 == null) {
                    fg2 = new Home();
                    mFragmentTransaction.add(R.id.activity_framelayout, fg2);
                } else {
                    mFragmentTransaction.show(fg2);
                }
                break;

            case R.id.activity_title_tag_relativelayout3:

                activity_title_tag3.setSelected(true);

                if (fg3 == null) {
                    fg3 = new Home();
                    mFragmentTransaction.add(R.id.activity_framelayout, fg3);
                } else {
                    mFragmentTransaction.show(fg3);
                }
                break;

            case R.id.activity_title_tag_relativelayout4:

                activity_title_tag4.setSelected(true);

                if (fg4 == null) {
                    fg4 = new Personal_settings();
                    mFragmentTransaction.add(R.id.activity_framelayout, fg4);
                } else {
                    mFragmentTransaction.show(fg4);
                }
                break;
        }

        mFragmentTransaction.commit();


    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);
        if (fg4 != null) fragmentTransaction.hide(fg4);
    }

    private void setSelected() {

        activity_title_tag1.setSelected(false);
        activity_title_tag2.setSelected(false);
        activity_title_tag3.setSelected(false);
        activity_title_tag4.setSelected(false);
    }

}
