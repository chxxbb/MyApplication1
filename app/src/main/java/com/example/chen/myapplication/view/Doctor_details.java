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
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/13.
 */
public class Doctor_details extends Activity {

    SpannableString spanString = null;
    TextView doctor_details_introduction = null;
    TextView doctor_details_specialty = null;
    TextView doctor_details_people_size = null;
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

        if (doctor != null) {
            spanString = new SpannableString(doctor.getBio());
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
            spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色
            doctor_details_introduction.setText(spanString);

            spanString = new SpannableString(doctor.getAdept());
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
            spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色
            doctor_details_specialty.setText(spanString);
        }

        doctor_details_bottom_menu_botton_convention = (Button) findViewById(R.id.doctor_details_bottom_menu_botton_convention);
        doctor_details_bottom_menu_botton_convention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_details.this, Doctor_scheduling.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.doctor_details_listview);

        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);
        list = new ArrayList<ListItem>();
//
//        //设定该窗口类型,并发送一个数据(该数据可自定义)
//        item = new ListItem(4, "wori");
//        list.add(item);
//
//        //将List发送给自定义适配器
//        listAdapter.setList(list);
//        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
//        listAdapter.notifyDataSetChanged();


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
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
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
