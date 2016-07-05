package com.example.chen.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.myapplication.data.User;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
    Button registered_VerificationCode_button = null;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);

        init();

    }

    private void init() {
        registered_exit = (ImageView) findViewById(R.id.registered_exit);
        registered_exit.setOnClickListener(this);


        //用户填写信息的Edittext控件绑定
        registered_user = (EditText) findViewById(R.id.registered_user_edittext);
        registered_password = (EditText) findViewById(R.id.registered_password_edittext);
        registered_password_2 = (EditText) findViewById(R.id.registered_password_edittext_2);
        registered_VerificationCode = (EditText) findViewById(R.id.registered_VerificationCode_edittext);

        registered_user.setOnClickListener(this);
        registered_password.setOnClickListener(this);
        registered_password_2.setOnClickListener(this);
        registered_VerificationCode.setOnClickListener(this);

        //按钮控件绑定
        registered_button = (Button) findViewById(R.id.registered_button);
        registered_button.setOnClickListener(this);

        registered_VerificationCode_button = (Button) findViewById(R.id.registered_VerificationCode_button);
        registered_VerificationCode_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {    //若点击退出按钮,则关闭activity
            case R.id.registered_exit:
                finish();
                break;

            case R.id.registered_button:    //点击注册按钮
                if (TextUtils.isEmpty(registered_user.getText()) || TextUtils.isEmpty(registered_password.getText())) {   //为当必填信息为空的时候

                    Toast.makeText(this, "请填写账户和密码", Toast.LENGTH_LONG).show();

                } else if (registered_user.getText().length() < 11 || registered_password.getText().length() < 6) {   //判断帐号密码位数是否正确

                    Toast.makeText(this, "请填写正确的手机号及不少于6位密码", Toast.LENGTH_LONG).show();

                } else if (!(new String(registered_password.getText().toString()).equals(new String(registered_password_2.getText().toString())))) {   //判断两次密码是否相同

                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(registered_VerificationCode.getText())) {    //判断是否输入了验证码
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "判断验证码是否正确", Toast.LENGTH_LONG).show();


                    user = new User();
                    user.setPhone(registered_user.getText().toString());
                    user.setPassword(registered_password.getText().toString());
                    user.setSex("男");
                    user.setName("fuck");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestBody requestBody = RequestBody.create(JSON, gson.toJson(user));
                            Request request = new Request.Builder().url("http://192.168.1.35:8080/ApplicationService/register").post(requestBody).build();

                            Call call = client.newCall(request);

                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(Response response) throws IOException {
                                    String str = response.body().string();
                                    System.out.println(str);
                                    Intent intent = new Intent(registered.this, activity_fragment.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    }).start();


                }

                break;

            case R.id.registered_VerificationCode_button://点击验证码按钮发送验证码


                if (TextUtils.isEmpty(registered_user.getText())) {   //为当必填信息为空的时候

                    Toast.makeText(this, "请填写手机号", Toast.LENGTH_LONG).show();

                } else if (registered_user.getText().length() < 11) {   //判断是否是11位手机号
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "已发送验证码请查收", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
