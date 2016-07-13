package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chen.myapplication.R;

/**
 * Created by Chen on 2016/7/9.
 */
public class Personal_settings_head_portrait_change_page extends Activity {

    ImageView personal_information_changes_page_head_portrait_change_menu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_settings_head_portrait_change_page);

        personal_information_changes_page_head_portrait_change_menu = (ImageView) findViewById(R.id.personal_information_changes_page_head_portrait_change_menu);

        personal_information_changes_page_head_portrait_change_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personal_settings_head_portrait_change_page.this.openOptionsMenu();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "选择图片");

        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "取消");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                Toast.makeText(this, "ss", Toast.LENGTH_LONG).show();
                break;
            case Menu.FIRST + 2:
                Toast.makeText(this, "ddd", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}

