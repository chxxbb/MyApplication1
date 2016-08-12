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

    User usera = null;

    RelativeLayout personal_information_changes_page_phone_relativelayout = null;
    RelativeLayout personal_information_changes_page_name_relativelayout = null;
    RelativeLayout personal_information_changes_page_sex_relativelayout = null;
    RelativeLayout personal_information_changes_page_Head_portrait = null;

    ImageView personal_information_changes_page_Head_portrait_image = null;

    TextView personal_information_changes_page_name = null, personal_information_changes_page_sex = null, personal_information_changes_page_phone = null;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    personal_information_changes_page_Head_portrait_image.setImageBitmap(bitmap);
                    break;
                case 2:
                    break;
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_changes_page);

        usera = new User(User_data.user);

        init_relativelayout();

        init_data();

        init_change_UI();

    }

    public void init_change_UI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (User_data.user.getName().equals(personal_information_changes_page_name.getText().toString())) {

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                personal_information_changes_page_name.setText(User_data.user.getName());
                            }
                        });
                    }

                    if (usera.getBitmap_icon() != null) {
                        if (!usera.getBitmap_icon().equals(User_data.user.getBitmap_icon())) {
                            System.out.println("fuck!?");
                            usera.setIcon(User_data.user.getIcon());
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    usera.setBitmap_icon(User_data.user.getBitmap_icon());

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            personal_information_changes_page_Head_portrait_image.setImageBitmap(usera.getBitmap_icon());
                                        }
                                    });

                                }
                            }).start();
                        }
                    }
                }
            }

        }).start();
    }

    private void init_data() {
        personal_information_changes_page_Head_portrait_image = (ImageView) findViewById(R.id.personal_information_changes_page_Head_portrait_image);
        personal_information_changes_page_name = (TextView) findViewById(R.id.personal_information_changes_page_name);
        personal_information_changes_page_sex = (TextView) findViewById(R.id.personal_information_changes_page_sex);
        personal_information_changes_page_phone = (TextView) findViewById(R.id.personal_information_changes_page_phone);

        personal_information_changes_page_name.setText(usera.getName());
        personal_information_changes_page_sex.setText(usera.getSex());

        if (usera.getPhone() != null) {
            personal_information_changes_page_phone.setText(usera.getPhone());
        } else {
            personal_information_changes_page_phone.setText("未验证");
            //如果没验证手机,则可以进入修改绑定手机页面,如果已经绑定了手机,则不能进入
            personal_information_changes_page_phone_relativelayout = (RelativeLayout) findViewById(R.id.personal_information_changes_page_phone_relativelayout);

            personal_information_changes_page_phone_relativelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Personal_information_changes_page.this, Binding_mobile_phone_page.class);
                    startActivity(intent);
                }
            });
        }

        if (usera.getBitmap_icon() != null) {
            personal_information_changes_page_Head_portrait_image.setImageBitmap(usera.getBitmap_icon());
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
                bitmap = http_bitmap.GetLocalOrNetBitmap(usera.getIcon());

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
