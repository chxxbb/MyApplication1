package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.data.User_data;
import com.example.chen.myapplication.utils.Http_Bitmap;

/**
 * Created by Chen on 2016/7/8.
 */
public class Personal_information_changes_page extends Activity {

    User user = null;

    RelativeLayout personal_information_changes_page_phone_relativelayout = null;
    RelativeLayout personal_information_changes_page_name_relativelayout = null;
    RelativeLayout personal_information_changes_page_sex_relativelayout = null;
    RelativeLayout personal_information_changes_page_Head_portrait = null;

    ImageView personal_information_changes_page_Head_portrait_image = null;

    TextView personal_information_changes_page_name = null, personal_information_changes_page_sex = null, personal_information_changes_page_phone = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Bitmap bitmap = (Bitmap) msg.obj;
            personal_information_changes_page_Head_portrait_image.setImageBitmap(bitmap);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_changes_page);

        user = User_data.user;

        init_relativelayout();

        init_data();

    }

    private void init_data() {
        personal_information_changes_page_Head_portrait_image = (ImageView) findViewById(R.id.personal_information_changes_page_Head_portrait_image);
        personal_information_changes_page_name = (TextView) findViewById(R.id.personal_information_changes_page_name);
        personal_information_changes_page_sex = (TextView) findViewById(R.id.personal_information_changes_page_sex);
        personal_information_changes_page_phone = (TextView) findViewById(R.id.personal_information_changes_page_phone);

        personal_information_changes_page_name.setText(user.getName());
        personal_information_changes_page_sex.setText(user.getSex());

        if (user.getPhone() != null) {
            personal_information_changes_page_phone.setText(user.getPhone());
        } else {
            personal_information_changes_page_phone.setText("未验证");
        }

        if (user.getBitmap_icon() != null) {
            personal_information_changes_page_Head_portrait_image.setImageBitmap(user.getBitmap_icon());
        } else {
            init_http_bitmap();
        }


    }

    private void init_http_bitmap() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http_Bitmap http_bitmap = new Http_Bitmap();
                Bitmap bitmap;
                bitmap = http_bitmap.GetLocalOrNetBitmap_NoCompression(user.getIcon());

                if (bitmap != null) {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = bitmap;
                    handler.sendMessage(message);
                }

            }
        }).start();

    }

    private void init_relativelayout() {
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
