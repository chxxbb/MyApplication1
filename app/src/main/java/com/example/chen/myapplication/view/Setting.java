package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.R;

/**
 * Created by Chen on 2016/7/13.
 */
public class Setting extends Activity {

    RelativeLayout setting_update = null;
    RelativeLayout setting_password_change = null;
    RelativeLayout setting_feedback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        setting_update = (RelativeLayout) findViewById(R.id.setting_update);
        setting_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Setting_update_page.class);
                startActivity(intent);
            }
        });

        setting_password_change = (RelativeLayout) findViewById(R.id.setting_password_change);
        setting_password_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Setting_password_change.class);
                startActivity(intent);
            }
        });

        setting_feedback = (RelativeLayout) findViewById(R.id.setting_feedback);
        setting_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Setting_feedback.class);
                startActivity(intent);
            }
        });
    }
}
