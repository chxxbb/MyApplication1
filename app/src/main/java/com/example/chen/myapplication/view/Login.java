package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.data.User_data;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.*;

public class Login extends Activity implements View.OnClickListener {

    ImageView login_password_logo_right = null;
    int login_password_logo_right_i = 0;

    EditText login_password_EditText = null;

    Button login_button = null;

    TextView login_registered = null;

    EditText login_username = null;
    EditText login_password = null;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    User user = null;

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

        login_registered = (TextView) findViewById(R.id.login_new_user_text);
        login_registered.setOnClickListener(this);

        //账户名密码控件绑定
        login_username = (EditText) findViewById(R.id.login_user_edittext);
        login_password = (EditText) findViewById(R.id.login_password_edittext);
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
            case R.id.login_new_user_text:  //点击新用户按钮则跳转到注册页面

                Intent intent = new Intent(this, registered.class);
                startActivity(intent);  //跳转到注册页面

                break;

            case R.id.login_button: //用户点击登录,先判断必填信息是否为空

                if (TextUtils.isEmpty(login_username.getText()) || TextUtils.isEmpty(login_password.getText())) {   //为当必填信息为空的时候

                    Toast.makeText(this, "请填写账户和密码", Toast.LENGTH_LONG).show();

                } else if (login_username.getText().length() < 11 || login_password.getText().length() < 6) {   //判断帐号密码位数是否正确

                    Toast.makeText(this, "请填写正确的账户和密码", Toast.LENGTH_LONG).show();

                } else {
                    String userName = login_username.getText().toString();
                    String password = login_password.getText().toString();

                    user = new User();

                    user.setPhone(userName);
                    user.setPassword(password);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestBody requestBody = RequestBody.create(JSON, gson.toJson(user));
                            Request request = new Request.Builder().url(HTTP_data.http_data + "/login").post(requestBody).build();


                            Call call = client.newCall(request);

                            call.enqueue(new Callback() {

                                @Override
                                public void onFailure(Call call, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Login.this, "网络连接失败", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String str = response.body().string();

                                    if (str.equals("1")) {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(Login.this, "用户名密码错误", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    } else if (str.equals("2")) {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(Login.this, "没有该用户", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    } else {
                                        user = null;
                                        System.out.println(str);
                                        user = gson.fromJson(str, User.class);
//                                        User_data.user = user;
                                        User_data.user.setUser(user);
                                        gohome();
                                    }
                                }
                            });

                        }
                    }).start();


                }


                break;
        }
    }

    private void gohome() {
        Intent intent_login = new Intent(this, activity_fragment.class);
        startActivity(intent_login);
        finish();
    }

}
