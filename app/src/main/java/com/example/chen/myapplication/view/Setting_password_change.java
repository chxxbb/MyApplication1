package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User_data;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Chen on 2016/7/13.
 */
public class Setting_password_change extends Activity {

    EditText setting_password_change_old_password_edittext, setting_password_change_new_password_edittext;

    Button setting_password_change_submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_password_change);

        setting_password_change_old_password_edittext = (EditText) findViewById(R.id.setting_password_change_old_password_edittext);

        setting_password_change_new_password_edittext = (EditText) findViewById(R.id.setting_password_change_new_password_edittext);

        setting_password_change_submit_button = (Button) findViewById(R.id.setting_password_change_submit_button);

        setting_password_change_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setting_password_change_old_password_edittext.getText() != null && setting_password_change_old_password_edittext.getText().toString().length() >= 6
                        && setting_password_change_new_password_edittext.getText() != null && setting_password_change_new_password_edittext.getText().toString().length() >= 6) {
                    OkHttpUtils
                            .post()
                            .url(HTTP_data.http_data + "/changeP" + "?" + User_data.user.getId())
                            .addParams("old", setting_password_change_old_password_edittext.getText().toString())
                            .addParams("new", setting_password_change_new_password_edittext.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    if (response.equals("1")) {
                                        Toast.makeText(Setting_password_change.this, "修改成功", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(Setting_password_change.this, "修改失败", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Setting_password_change.this, "请输入正确的密码", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
