package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.chen.myapplication.data.Comment;
import com.example.chen.myapplication.data.Doctor;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.page.ListAdapter;
import com.example.chen.myapplication.page.ListItem;
import com.example.chen.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Chen on 2016/6/13.
 */
public class Doctor_details extends Activity {

    SpannableString spanString = null;
    TextView doctor_details_introduction = null;
    TextView doctor_details_specialty = null;
    TextView doctor_details_people_size = null, doctor_details_name = null, doctor_details_level = null, doctor_details_department = null;
    TextView doctor_details_hospital = null;
    ImageView doctor_details_portrait = null;
    ListView listView = null;

    Button doctor_details_bottom_menu_botton_convention = null;

    Doctor doctor;
    ListAdapter listAdapter = null;
    List<ListItem> list = null;
    ListItem item = null;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    List<Comment> comment_list;

    int xc = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    comment_list = (List<Comment>) msg.obj;
                    doctor_details_people_size.setText("" + comment_list.size());

                    for (int i = 0; i < comment_list.size(); i++) {
                        item = new ListItem(4, comment_list.get(i));
                        list.add(item);
                    }

                    break;
                case 2:
                    doctor_details_portrait.setImageBitmap(doctor.getIcon_bitmap());
                    xc = 1;
                    break;
            }

            //将List发送给自定义适配器
            listAdapter.setList(list);
            //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
            listAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_details);

        doctor_details_people_size = (TextView) findViewById(R.id.doctor_details_people_size);


        init_data();

        init_http_data();

        doctor_details_introduction = (TextView) findViewById(R.id.doctor_details_introduction);
        doctor_details_specialty = (TextView) findViewById(R.id.doctor_details_specialty);
        doctor_details_name = (TextView) findViewById(R.id.doctor_details_name);
        doctor_details_level = (TextView) findViewById(R.id.doctor_details_level);
        doctor_details_department = (TextView) findViewById(R.id.doctor_details_department);
        doctor_details_hospital = (TextView) findViewById(R.id.doctor_details_hospital);

        doctor_details_portrait = (ImageView) findViewById(R.id.doctor_details_portrait);


        if (doctor != null) {
            spanString = new SpannableString(doctor.getBio());
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
            spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色
            doctor_details_introduction.setText(spanString);

            spanString = new SpannableString(doctor.getAdept());
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
            spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色
            doctor_details_specialty.setText(spanString);

            doctor_details_name.setText(doctor.getName());
            doctor_details_level.setText(doctor.getTitle());
            doctor_details_department.setText(doctor.getSection());
            doctor_details_hospital.setText(doctor.getHospital());

            if (doctor.getIcon_bitmap() != null) {
                doctor_details_portrait.setImageBitmap(doctor.getIcon_bitmap());
            } else {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (xc == 0) {
                            try {
                                Thread.sleep(5000);
                                if (doctor.getIcon_bitmap() != null) {
                                    Message msg = new Message();
                                    msg.what = 2;
                                    handler.sendMessage(msg);
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                System.out.println("thread error...");
                            }
                        }
                    }
                }).start();

            }


        }

        doctor_details_bottom_menu_botton_convention = (Button) findViewById(R.id.doctor_details_bottom_menu_botton_convention);
        doctor_details_bottom_menu_botton_convention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTTP_data.doctor = doctor;
                Intent intent = new Intent(Doctor_details.this, Doctor_scheduling.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.doctor_details_listview);

        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);
        list = new ArrayList<ListItem>();

    }

    private void init_http_data() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = RequestBody.create(JSON, gson.toJson(doctor.getId()));
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findCommentList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        System.out.println(str);

                        List<Comment> comment_list = gson.fromJson(str, new TypeToken<List<Comment>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 1;
                        message.obj = comment_list;
                        handler.sendMessage(message);
                    }

                });

            }
        }).start();
    }

    private void init_data() {
        doctor = HTTP_data.doctor;
    }
}
