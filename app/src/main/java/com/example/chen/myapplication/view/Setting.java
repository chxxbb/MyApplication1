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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        setting_update = (RelativeLayout) findViewById(R.id.setting_update);
        setting_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Setting_update_page.class);
                Setting.this.startActivity(intent);
            }
        });

    }
}
