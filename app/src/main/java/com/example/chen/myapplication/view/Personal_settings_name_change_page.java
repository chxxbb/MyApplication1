package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User_data;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Chen on 2016/7/9.
 */
public class Personal_settings_name_change_page extends Activity {

    private int editStart;
    private int editEnd;
    private int maxLen = 10; // the max byte

    EditText personal_settings_name_change_page_edittext = null;
    TextView personal_settings_name_change_page_save_button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_settings_name_change_page);

        personal_settings_name_change_page_edittext = (EditText) findViewById(R.id.personal_settings_name_change_page_edittext);
        if (User_data.user.getName() != null) {
            personal_settings_name_change_page_edittext.setText(User_data.user.getName());
        }
        personal_settings_name_change_page_save_button = (TextView) findViewById(R.id.personal_settings_name_change_page_save_button);

        //获取焦点
        personal_settings_name_change_page_edittext.setFocusable(true);
        personal_settings_name_change_page_edittext.setFocusableInTouchMode(true);
        personal_settings_name_change_page_edittext.requestFocus();


        //中文限制6个字,英文限制12个
        personal_settings_name_change_page_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = personal_settings_name_change_page_edittext.getSelectionStart();
                editEnd = personal_settings_name_change_page_edittext.getSelectionEnd();

                if (!TextUtils.isEmpty(personal_settings_name_change_page_edittext.getText())) {
                    int varlength = 0;
                    int size = 0;
                    String etstring = personal_settings_name_change_page_edittext.getText().toString().trim();
                    char[] ch = etstring.toCharArray();
                    for (int i = 0; i < ch.length; i++) {
                        size++;
                        if (ch[i] >= 0x4e00 && ch[i] <= 0x9fbb) {
                            varlength = varlength + 2;
                        } else
                            varlength++;
                        if (varlength > 12) {
                            break;
                        }
                    }
                    if (varlength > 12) {
                        s.delete(size - 1, etstring.length());
                    }
                }
            }
        });

        //打开默认输入软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


        personal_settings_name_change_page_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User_data.user.setName(personal_settings_name_change_page_edittext.getText().toString());

                OkHttpUtils
                        .postString()
                        .url(HTTP_data.http_data + "/changeName" + "?" + User_data.user.getId())
                        .content(personal_settings_name_change_page_edittext.getText().toString())
                        .build()
                        .execute(new Callback() {
                            @Override
                            public Object parseNetworkResponse(Response response, int id) throws Exception {
                                return response.body().string();
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(Object response, int id) {
                                if (response.equals("1")) {
                                    System.out.println("Fuck!居然成功了?");
                                } else if (response.equals("0")) {
                                    System.out.println("Good!意料之中的失败.");
                                }
                            }
                        });

                finish();
            }
        });


    }
}
