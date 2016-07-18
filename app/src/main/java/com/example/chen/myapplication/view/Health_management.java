package com.example.chen.myapplication.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.page.Health_management_page;

/**
 * Created by Chen on 2016/7/18.
 */
public class Health_management extends Activity implements View.OnClickListener {
    FragmentManager mFragmentManager = null;

    RelativeLayout activity_title_tag1, activity_title_tag2, activity_title_tag3;

    Health_management_page fg1, fg2, fg3;

    View health_management_table_health_management_view, health_management_table_health_record_view, health_management_table_doctor_advised_view;

    TextView health_management_table_health_management_text, health_management_table_health_record_text, health_management_table_doctor_advised_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_management);

        mFragmentManager = getFragmentManager();

        bindViews();

        activity_title_tag1.performClick();

    }

    private void bindViews() {

        activity_title_tag1 = (RelativeLayout) findViewById(R.id.health_management_table_health_management);
        activity_title_tag2 = (RelativeLayout) findViewById(R.id.health_management_table_health_record);
        activity_title_tag3 = (RelativeLayout) findViewById(R.id.health_management_table_doctor_advised);

        activity_title_tag1.setOnClickListener(this);
        activity_title_tag2.setOnClickListener(this);
        activity_title_tag3.setOnClickListener(this);

        health_management_table_health_management_view = findViewById(R.id.health_management_table_health_management_view);
        health_management_table_health_record_view = findViewById(R.id.health_management_table_health_record_view);
        health_management_table_doctor_advised_view = findViewById(R.id.health_management_table_doctor_advised_view);

        health_management_table_health_management_text = (TextView) findViewById(R.id.health_management_table_health_management_text);
        health_management_table_health_record_text = (TextView) findViewById(R.id.health_management_table_health_record_text);
        health_management_table_doctor_advised_text = (TextView) findViewById(R.id.health_management_table_doctor_advised_text);

    }

    @Override
    public void onClick(View v) {

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(mFragmentTransaction);

        switch (v.getId()) {
            case R.id.health_management_table_health_management:

                activity_title_tag1.setSelected(true);
                health_management_table_health_management_view.setVisibility(View.VISIBLE);
                health_management_table_health_management_text.setTextColor(0xFF3496d3);

                if (fg1 == null) {
                    fg1 = new Health_management_page();
                    mFragmentTransaction.add(R.id.health_management_framelayout, fg1);
                } else {
                    mFragmentTransaction.show(fg1);
                }
                break;

            case R.id.health_management_table_health_record:

                activity_title_tag2.setSelected(true);
                health_management_table_health_record_view.setVisibility(View.VISIBLE);
                health_management_table_health_record_text.setTextColor(0xFF3496d3);

                if (fg2 == null) {
                    fg2 = new Health_management_page();
                    mFragmentTransaction.add(R.id.health_management_framelayout, fg2);
                } else {
                    mFragmentTransaction.show(fg2);
                }
                break;

            case R.id.health_management_table_doctor_advised:

                activity_title_tag3.setSelected(true);
                health_management_table_doctor_advised_view.setVisibility(View.VISIBLE);
                health_management_table_doctor_advised_text.setTextColor(0xFF3496d3);

                if (fg3 == null) {
                    fg3 = new Health_management_page();
                    mFragmentTransaction.add(R.id.health_management_framelayout, fg3);
                } else {
                    mFragmentTransaction.show(fg3);
                }
                break;

        }

        mFragmentTransaction.commit();


    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);
        if (fg3 != null) fragmentTransaction.hide(fg3);

        health_management_table_health_management_view.setVisibility(View.INVISIBLE);
        health_management_table_health_record_view.setVisibility(View.INVISIBLE);
        health_management_table_doctor_advised_view.setVisibility(View.INVISIBLE);

        health_management_table_health_management_text.setTextColor(0xFF666666);
        health_management_table_health_record_text.setTextColor(0xFF666666);
        health_management_table_doctor_advised_text.setTextColor(0xFF666666);

    }

    private void setSelected() {

        activity_title_tag1.setSelected(false);
        activity_title_tag2.setSelected(false);
        activity_title_tag3.setSelected(false);
    }
}
