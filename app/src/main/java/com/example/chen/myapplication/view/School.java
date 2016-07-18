package com.example.chen.myapplication.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.page.Classroom;
import com.example.chen.myapplication.page.Home;
import com.example.chen.myapplication.page.Personal_settings;
import com.example.chen.myapplication.page.Wikipedia;

/**
 * Created by Chen on 2016/7/18.
 */
public class School extends Activity implements View.OnClickListener {
    FragmentManager mFragmentManager = null;

    RelativeLayout activity_title_tag1, activity_title_tag2;
    View school_title_curriculum_view, school_title_classroom_view;

    Classroom fg1, fg2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school);

        mFragmentManager = getFragmentManager();

        bindViews();

        activity_title_tag1.performClick();

    }

    private void bindViews() {

        activity_title_tag1 = (RelativeLayout) findViewById(R.id.school_title_curriculum_relative);
        activity_title_tag2 = (RelativeLayout) findViewById(R.id.school_title_classroom_relative);

        activity_title_tag1.setOnClickListener(this);
        activity_title_tag2.setOnClickListener(this);

        school_title_curriculum_view = findViewById(R.id.school_title_curriculum_view);
        school_title_classroom_view = findViewById(R.id.school_title_classroom_view);

    }

    @Override
    public void onClick(View v) {

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(mFragmentTransaction);

        switch (v.getId()) {
            case R.id.school_title_curriculum_relative:

                activity_title_tag1.setSelected(true);
                school_title_curriculum_view.setVisibility(View.VISIBLE);

                if (fg1 == null) {
                    fg1 = new Classroom();
                    mFragmentTransaction.add(R.id.school_framelayout, fg1);
                } else {
                    mFragmentTransaction.show(fg1);
                }
                break;

            case R.id.school_title_classroom_relative:

                activity_title_tag2.setSelected(true);
                school_title_classroom_view.setVisibility(View.VISIBLE);

                if (fg2 == null) {
                    fg2 = new Classroom();
                    mFragmentTransaction.add(R.id.school_framelayout, fg2);
                } else {
                    mFragmentTransaction.show(fg2);
                }
                break;


        }

        mFragmentTransaction.commit();


    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
        if (fg2 != null) fragmentTransaction.hide(fg2);

        school_title_curriculum_view.setVisibility(View.INVISIBLE);
        school_title_classroom_view.setVisibility(View.INVISIBLE);

    }

    private void setSelected() {

        activity_title_tag1.setSelected(false);
        activity_title_tag2.setSelected(false);
    }
}
