package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.chen.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Chen on 2016/7/9.
 */
public class Personal_settings_name_change_page extends Activity {

    private int editStart;
    private int editEnd;
    private int maxLen = 10; // the max byte

    EditText personal_settings_name_change_page_edittext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_settings_name_change_page);

        personal_settings_name_change_page_edittext = (EditText) findViewById(R.id.personal_settings_name_change_page_edittext);

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


    }
}
