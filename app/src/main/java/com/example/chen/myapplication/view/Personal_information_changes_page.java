package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.R;

/**
 * Created by Chen on 2016/7/8.
 */
public class Personal_information_changes_page extends Activity {

    RelativeLayout personal_information_changes_page_phone_relativelayout = null;
    RelativeLayout personal_information_changes_page_name_relativelayout = null;
    RelativeLayout personal_information_changes_page_sex_relativelayout = null;
    RelativeLayout personal_information_changes_page_Head_portrait = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_changes_page);

        personal_information_changes_page_phone_relativelayout = (RelativeLayout) findViewById(R.id.personal_information_changes_page_phone_relativelayout);

        personal_information_changes_page_phone_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personal_information_changes_page.this, Binding_mobile_phone_page.class);
                startActivity(intent);
            }
        });

        personal_information_changes_page_name_relativelayout = (RelativeLayout) findViewById(R.id.personal_information_changes_page_name_relativelayout);

        personal_information_changes_page_name_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personal_information_changes_page.this, Personal_settings_name_change_page.class);
                startActivity(intent);
            }
        });

        personal_information_changes_page_sex_relativelayout = (RelativeLayout) findViewById(R.id.personal_information_changes_page_sex_relativelayout);

        personal_information_changes_page_sex_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personal_information_changes_page.this, Personal_settings_sex_change_page.class);
                startActivity(intent);
            }
        });

        personal_information_changes_page_Head_portrait = (RelativeLayout) findViewById(R.id.personal_information_changes_page_Head_portrait);

        personal_information_changes_page_Head_portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personal_information_changes_page.this, Personal_settings_head_portrait_change_page.class);
                startActivity(intent);
            }
        });

    }
}
