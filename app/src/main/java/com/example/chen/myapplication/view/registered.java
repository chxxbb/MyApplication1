package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chen.myapplication.R;
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
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    String VerificationCode = null;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
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

        //设置网络超时
        client.setConnectTimeout(5, TimeUnit.SECONDS);
        client.setWriteTimeout(5, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);

        registered_exit = (ImageView) findViewById(R.id.registered_exit);
        registered_exit.setOnClickListener(this);


        //用户填写信息的Edittext控件绑定
        registered_user = (EditText) findViewById(R.id.registered_user_edittext);
        registered_password = (EditText) findViewById(R.id.registered_password_edittext);
        registered_password_2 = (EditText) findViewById(R.id.registered_password_edittext_2);
        registered_VerificationCode = (EditText) findViewById(R.id.registered_VerificationCode_edittext);

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

                } else if (!(isMobileNO(registered_user.getText().toString()))) {

                    Toast.makeText(this, "请填写正确的手机号码", Toast.LENGTH_LONG).show();

                } else if (registered_password.getText().length() < 6) {   //判断帐号密码位数是否正确

                    Toast.makeText(this, "请填写不少于6位密码", Toast.LENGTH_LONG).show();

                } else if (!isPasswordNo(registered_password.getText().toString())) {

                    Toast.makeText(this, "请填写6-16位的密码,包含大小写子母和数字组成", Toast.LENGTH_LONG).show();

                } else if (!(new String(registered_password.getText().toString()).equals(new String(registered_password_2.getText().toString())))) {   //判断两次密码是否相同

                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(registered_VerificationCode.getText())) {    //判断是否输入了验证码
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show();
                } else if (VerificationCode.equals(registered_VerificationCode.getText().toString())) {

                    user = new User();
                    user.setPhone(registered_user.getText().toString());
                    user.setPassword(registered_password.getText().toString());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestBody requestBody = RequestBody.create(JSON, gson.toJson(user));
                            Request request = new Request.Builder().url("http://192.168.1.35:8080/ApplicationService/register").post(requestBody).build();

                            Call call = client.newCall(request);

                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Request request, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(registered.this, "网络连接失败", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Response response) throws IOException {

                                    String str = response.body().string();
                                    System.out.println(str);

                                    if (str.equals("1")) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(registered.this, "请填写正确的格式", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    } else {

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(registered.this, "注册成功", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                        Intent intent = new Intent(registered.this, Login.class);
                                        startActivity(intent);

                                    }


                                }
                            });

                        }
                    }).start();

                } else {
                    Toast.makeText(this, "验证码错误", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.registered_VerificationCode_button://点击验证码按钮发送验证码


                if (TextUtils.isEmpty(registered_user.getText())) {   //为当必填信息为空的时候

                    Toast.makeText(this, "请填写手机号", Toast.LENGTH_LONG).show();

                } else if (registered_user.getText().length() < 11) {   //判断是否是11位手机号
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show();
                } else if (isMobileNO(registered_user.getText().toString())) {

                    new Thread(new Runnable() { //通知服务器发送验证码
                        @Override
                        public void run() {
                            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, registered_user.getText().toString());
                            Request request = new Request.Builder().url("http://192.168.1.35:8080/ApplicationService/send").post(requestBody).build();

                            Call call = client.newCall(request);

                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Request request, IOException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(registered.this, "网络连接失败", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Response response) throws IOException {

                                    VerificationCode = response.body().string();
                                    System.out.println(VerificationCode);

                                }
                            });

                        }
                    }).start();

                    Toast.makeText(this, "已发送验证码请查收", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public boolean isPasswordNo(String mobiles) {
        Pattern p = Pattern.compile("^[0-9a-zA-Z]{6,16}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
