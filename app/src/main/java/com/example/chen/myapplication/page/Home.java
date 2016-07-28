package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.Doctor;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.data.User_data;
import com.example.chen.myapplication.utils.Http_Bitmap;
import com.example.chen.myapplication.view.activity_fragment;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/6/3.
 */
public class Home extends Fragment {

    View view;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    User user = null;
    List<Doctor> list_doctor = null;

    ListView listView;
    ListAdapter listAdapter;
    ListItem item;
    List<ListItem> list;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    list_doctor = (List<Doctor>) msg.obj;
                    list = new ArrayList<ListItem>();
                    item = new ListItem(0, "wori");
                    list.add(item);
                    if (list_doctor != null) {
                        for (int i = 0; i < list_doctor.size(); i++) {
                            item = new ListItem(1, list_doctor.get(i));
                            list.add(item);
                            item = null;
                        }
                    }
                    break;
                case 2:
                    list_doctor = (List<Doctor>) msg.obj;
                    if (list_doctor != null) {
                        for (int i = 0; i < list_doctor.size(); i++) {
                            item = new ListItem(1, list_doctor.get(i));
                            list.add(item);
                            item = null;
                        }
                    }
                    break;
            }


            //将List发送给自定义适配器
            listAdapter.setList(list);
            //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
            listAdapter.notifyDataSetChanged();

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);

        //准备网络数据
        init_http_data();


        //获取ListView控件,并创建自定义适配器
        listView = (ListView) view.findViewById(R.id.list_main);
        listAdapter = new ListAdapter(getActivity());
        listView.setAdapter(listAdapter);

        list = new ArrayList<ListItem>();

        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(0, "wori");
        list.add(item);

        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();
        return view;
    }

    private void init_http_data() {     //联网准备数据

        //网络交互开始
        new Thread(new Runnable() {
            @Override
            public void run() {     //准备Banner图片地址
                RequestBody requestBody = RequestBody.create(JSON, "请求Banner图片地址");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/slide").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        String str = response.body().string();

                        List list = new ArrayList<>();

                        list = gson.fromJson(str, List.class);

                        String[] http_img = new String[list.size()];

                        for (int i = 0; i < list.size(); i++) {
                            http_img[i] = (String) list.get(i);
                        }

                        HTTP_data.Banner_img = http_img;

                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);

                    }
                });

            }
        }).start();

        new Thread(new Runnable() {     //准备医生数据
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(JSON, "请求首页的医生列表");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findDoctorList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String str = response.body().string();

                        list_doctor = new ArrayList<Doctor>();

                        list_doctor = gson.fromJson(str, new TypeToken<List<Doctor>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 2;
                        message.obj = list_doctor;
                        handler.sendMessage(message);

                        Http_Bitmap http_bitmap = new Http_Bitmap();
                        for (int i = 0; i < list_doctor.size(); i++) {
                            list_doctor.get(i).setIcon_bitmap(http_bitmap.GetLocalOrNetBitmap(list_doctor.get(i).getIcon()));
                        }

                        Message message1 = new Message();
                        message1.what = 1;
                        message1.obj = list_doctor;
                        handler.sendMessage(message1);

                    }
                });

            }
        }).start();
    }

    public View changelist(List<Doctor> list_message) {

        list_doctor = list_message;

        for (int i = 0; i < list_doctor.size(); i++) {
            item = new ListItem(1, list_doctor.get(i));
            list.add(item);
            item = null;
        }

        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();

        return view;
    }

}
