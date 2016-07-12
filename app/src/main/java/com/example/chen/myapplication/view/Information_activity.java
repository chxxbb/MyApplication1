package com.example.chen.myapplication.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.page.Information_school;
import com.example.chen.myapplication.R;

/**
 * Created by Chen on 2016/6/28.
 */
public class Information_activity extends Activity implements View.OnClickListener {

    FragmentManager mFragmentManager = null;

    RelativeLayout activity_title_tag1, activity_title_tag2;

    Information_school fg1, fg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_activity);

        mFragmentManager = getFragmentManager();

        bindViews();

        activity_title_tag1.performClick();

    }

    private void bindViews() {

        activity_title_tag1 = (RelativeLayout) findViewById(R.id.information_activity_title_relativelayout1);
        activity_title_tag2 = (RelativeLayout) findViewById(R.id.information_activity_title_relativelayout2);

        activity_title_tag1.setOnClickListener(this);
        activity_title_tag2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(mFragmentTransaction);

        switch (v.getId()) {
            case R.id.information_activity_title_relativelayout1:
                activity_title_tag1.setSelected(true);

                if (fg1 == null) {
                    fg1 = new Information_school();
                    mFragmentTransaction.add(R.id.information_activity_framelayout, fg1);
                } else {
                    mFragmentTransaction.show(fg1);
                }
                break;

            case R.id.information_activity_title_relativelayout2:

                activity_title_tag2.setSelected(true);

                if (fg2 == null) {
                    fg2 = new Information_school();
                    mFragmentTransaction.add(R.id.information_activity_framelayout, fg2);
                } else {
                    mFragmentTransaction.show(fg2);
                }
                break;
        }

        mFragmentTransaction.commit();

    }

    private void hideAllFragment(FragmentTransaction mFragmentTransaction) {
        if (fg1 != null) mFragmentTransaction.hide(fg1);
        if (fg2 != null) mFragmentTransaction.hide(fg2);
    }
}
