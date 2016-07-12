package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
                Toast.makeText(Personal_settings_head_portrait_change_page.this, "sss", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, Menu.FIRST + 1, 5, "删除").setIcon(

                android.R.drawable.ic_menu_delete);

        // setIcon()方法为菜单设置图标，这里使用的是系统自带的图标，同学们留意一下,以

        // android.R开头的资源是系统提供的，我们自己提供的资源是以R开头的

        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "保存").setIcon(

                android.R.drawable.ic_menu_edit);

        menu.add(Menu.NONE, Menu.FIRST + 3, 6, "帮助").setIcon(

                android.R.drawable.ic_menu_help);

        menu.add(Menu.NONE, Menu.FIRST + 4, 1, "添加").setIcon(

                android.R.drawable.ic_menu_add);

        menu.add(Menu.NONE, Menu.FIRST + 5, 4, "详细").setIcon(

                android.R.drawable.ic_menu_info_details);

        menu.add(Menu.NONE, Menu.FIRST + 6, 3, "发送").setIcon(

                android.R.drawable.ic_menu_send);

        return true;

    }


}

