package com.example.chen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chen on 2016/5/30.
 */
public class registered extends Activity implements View.OnClickListener {

    ImageView registered_exit = null;

    EditText registered_user = null;
    EditText registered_password = null;
    EditText registered_password_2 = null;
    EditText registered_VerificationCode = null;
    Button registered_button = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);

        init();

    }

    private void init() {
        registered_exit = (ImageView) findViewById(R.id.registered_exit);
        registered_exit.setOnClickListener(this);

        registered_user = (EditText) findViewById(R.id.registered_user_edittext);
        registered_password = (EditText) findViewById(R.id.registered_password_edittext);
        registered_password_2 = (EditText) findViewById(R.id.registered_password_edittext_2);
        registered_VerificationCode = (EditText) findViewById(R.id.registered_VerificationCode_edittext);

        registered_user.setOnClickListener(this);
        registered_password.setOnClickListener(this);
        registered_password_2.setOnClickListener(this);
        registered_VerificationCode.setOnClickListener(this);

        registered_button = (Button) findViewById(R.id.registered_button);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {    //若点击推出按钮,则关闭activity
            case R.id.registered_exit:
                finish();
                break;

            case R.id.registered_button:    //点击注册按钮


                break;

        }
    }
}
