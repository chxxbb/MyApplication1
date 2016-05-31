package com.example.chen.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends Activity implements View.OnClickListener {

    ImageView login_password_logo_right = null;
    int login_password_logo_right_i = 0;

    EditText login_password_EditText = null;

    Button login_button = null;

    Button a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        init();

    }

    private void init() {
        login_password_logo_right = (ImageView) findViewById(R.id.login_password_logo_right_imageview);
        login_password_logo_right.setOnClickListener(this);

        login_password_EditText = (EditText) findViewById(R.id.login_password_edittext);

        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_password_logo_right_imageview:  //当用户点击显示密码按钮后,判断当前状态并更换图标/更换状态/执行显示或隐藏密码操作

                if (login_password_logo_right_i == 0) { //当前状态为隐藏,执行显示密码操作
                    login_password_logo_right.setImageResource(R.mipmap.login_password_logo_right_no);
                    login_password_logo_right_i = 1;
                    login_password_EditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable str = login_password_EditText.getText();
                    login_password_EditText.setSelection(str.length());
                } else {    //当前状态为显示,执行隐藏密码操作
                    login_password_logo_right.setImageResource(R.mipmap.login_password_logo_right_off);
                    login_password_logo_right_i = 0;
                    login_password_EditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable str = login_password_EditText.getText();
                    login_password_EditText.setSelection(str.length());
                }

                break;
            case R.id.login_button:

                Intent intent = new Intent(this, registered.class);
                startActivity(intent);

                break;
        }
    }
}
