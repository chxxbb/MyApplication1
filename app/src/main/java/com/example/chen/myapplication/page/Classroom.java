package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.utils.Http_Bitmap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.*;

/**
 * Created by Chen on 2016/7/18.
 */
public class Classroom extends Fragment {

    ListView listView;
    ListAdapter listAdapter;
    List<ListItem> list;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    List<Map<String, String>> list_http_data;
    List<Map<String, Object>> list_data;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    list_http_data = (List<Map<String, String>>) msg.obj;
                    listAdapter = new ListAdapter(getActivity());
                    listView.setAdapter(listAdapter);
                    list = new ArrayList<ListItem>();

                    list_data = new ArrayList<Map<String, Object>>();
                    int x = 0;
                    Map<String, Object> map;

                    for (int i = 0; i < list_http_data.size(); i++) {

                        map = new HashMap<String, Object>();
                        map.put("title", list_http_data.get(i).get("title"));

                        if ((i + 1) < list_http_data.size()) {
                            map.put("title2", list_http_data.get(i + 1).get("title"));
                        }

                        list_data.add(map);
                        ListItem item = new ListItem(7, list_data.get(x));
                        x++;
                        list.add(item);
                        i++;
                    }

                    listAdapter.setList(list);
                    listAdapter.notifyDataSetChanged();

                    break;
                case 2:

                    List<Bitmap> list_bitmap = (List<Bitmap>) msg.obj;
                    list = new ArrayList<ListItem>();
                    int y = 0;
                    for (int i = 0; i < list_bitmap.size(); i++) {
                        list_data.get(y).put("cover", list_bitmap.get(i));

                        if ((i + 1) < list_bitmap.size()) {
                            list_data.get(y).put("cover2", list_bitmap.get(i + 1));
                        }
                        ListItem item = new ListItem(7, list_data.get(y));
                        y++;
                        list.add(item);
                        i++;
                    }

                    listAdapter.setList(list);
                    listAdapter.notifyDataSetChanged();

                    break;
            }


        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.classroom, container, false);

        listView = (ListView) view.findViewById(R.id.classroom_listview);
        init_http_data();


        return view;
    }

    private void init_http_data() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = RequestBody.create(JSON, "请求视频缩略图图片地址");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findKnowledgeLectureList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();

                        List<Map<String, String>> list_http = new ArrayList<Map<String, String>>();

                        list_http = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 1;
                        message.obj = list_http;
                        handler.sendMessage(message);

                        Http_Bitmap http_bitmap = new Http_Bitmap();
                        Bitmap bitmap;
                        List<Bitmap> list_bitmap = new ArrayList<Bitmap>();
                        for (int i = 0; i < list_http.size(); i++) {
                            bitmap = http_bitmap.GetLocalOrNetBitmap(list_http.get(i).get("cover"));
                            list_bitmap.add(bitmap);
                        }

                        Message message2 = new Message();
                        message2.what = 2;
                        message2.obj = list_bitmap;
                        handler.sendMessage(message2);
                    }

                });

            }
        }).start();

    }

}
