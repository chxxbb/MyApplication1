package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User_data;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Chen on 2016/7/9.
 */
public class Personal_settings_sex_change_page extends Activity {

    RelativeLayout personal_settings_sex_change_page_boy, personal_settings_sex_change_page_girl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_settings_sex_change_page);

        personal_settings_sex_change_page_boy = (RelativeLayout) findViewById(R.id.personal_settings_sex_change_page_boy);

        personal_settings_sex_change_page_girl = (RelativeLayout) findViewById(R.id.personal_settings_sex_change_page_girl);

        personal_settings_sex_change_page_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils
                                .postString()
                                .content("男")
                                .url(HTTP_data.http_data + "/changeSex" + "?" + User_data.user.getId())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        if (response.equals("1")) {
                                            User_data.user.setSex("男");
                                            finish();
                                        }
                                    }
                                });
                    }
                }).start();
            }
        });

        personal_settings_sex_change_page_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpUtils
                                .postString()
                                .content("女")
                                .url(HTTP_data.http_data + "/changeSex" + "?" + User_data.user.getId())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        if (response.equals("1")) {
                                            User_data.user.setSex("女");
                                            finish();
                                        }
                                    }
                                });
                    }
                }).start();
            }
        });

    }
}
