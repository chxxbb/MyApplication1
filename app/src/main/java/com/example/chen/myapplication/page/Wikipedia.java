package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.HealthPedia;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.utils.Http_Bitmap;
import com.example.chen.myapplication.view.Health_information_page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Chen on 2016/7/18.
 */
public class Wikipedia extends Fragment {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    User user = null;
    List<HealthPedia> list_message = null;
    List<HealthPedia> list_bitmap_message = null;
    List<Map<String, Object>> list;
    Map<String, Object> map;
    ListView listview;
    SimpleAdapter adapter;
    Bitmap bitmap;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    list_message = (List<HealthPedia>) msg.obj;

                    for (int i = 0; i < list_message.size(); i++) {
                        HealthPedia healthPedia = (HealthPedia) list_message.get(i);

                        map = new HashMap<String, Object>();
                        map.put("title", healthPedia.getTitle());
                        map.put("text", healthPedia.getContent());
                        list.add(map);

                    }

                    SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.news_item,
                            new String[]{"title", "text"},
                            new int[]{R.id.news_item_title_text, R.id.news_item_text});

                    listview.setAdapter(adapter);

                    break;
                case 2:

                    List<Bitmap> bitmap_list = (List<Bitmap>) msg.obj;
                    list = new ArrayList<Map<String, Object>>();

                    for (int i = 0; i < list_message.size(); i++) {
                        HealthPedia healthPedia = (HealthPedia) list_message.get(i);


                        Bitmap bitmap = bitmap_list.get(i);

                        map = new HashMap<String, Object>();
                        map.put("img", bitmap);
                        map.put("title", healthPedia.getTitle());
                        map.put("text", healthPedia.getContent());
                        list.add(map);


                    }

                    SimpleAdapter adapter1 = new SimpleAdapter(getActivity(), list, R.layout.news_item,
                            new String[]{"img", "title", "text"},
                            new int[]{R.id.news_item_image, R.id.news_item_title_text,
                                    R.id.news_item_text});

                    adapter1.setViewBinder(new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {

                            //判断是否为我们要处理的对象
                            if (view instanceof ImageView && data instanceof Bitmap) {
                                ImageView iv = (ImageView) view;
                                iv.setImageBitmap((Bitmap) data);
                                return true;
                            } else

                                return false;
                        }
                    });
                    listview.setAdapter(adapter1);

                    break;

            }


        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wikipedia, container, false);

        listview = (ListView) view.findViewById(R.id.wikipedia_news_listview);

        init_http_data();

        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.news_item,
                new String[]{"title", "text"},
                new int[]{R.id.news_item_title_text,
                        R.id.news_item_text});

        listview.setAdapter(adapter);

        //这段是让SimpleAdapter可以放图片进去
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {

                //判断是否为我们要处理的对象
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView iv = (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;
                } else

                    return false;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int position_http = position;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(position_http));
                        Request request = new Request.Builder().url(HTTP_data.http_data + "/findHealthPediaId").post(requestBody).build();

                        Call call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String str = response.body().string();
                                System.out.println(str);

                                HealthPedia wikipedia = gson.fromJson(str, HealthPedia.class);

                                HTTP_data.healthPedia = wikipedia;

                                Intent intent = new Intent(getActivity(), Health_information_page.class);
                                getActivity().startActivity(intent);
                            }

                        });
                    }
                }).start();
            }
        });

        return view;
    }

    public List<Map<String, Object>> getData() {
//        这里是要填入的图片,转成Bitmap格式
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        list = new ArrayList<Map<String, Object>>();
        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "正在加载");
        map.put("text", "正在加载");
        list.add(map);

        return list;
    }

    public void init_http_data() {
        //网络交互开始
        new Thread(new Runnable() {
            @Override
            public void run() {     //准备Banner图片地址
                RequestBody requestBody = RequestBody.create(JSON, "请求热门资讯列表");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findHealthPediaList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();

                        List list_http = new ArrayList<HealthPedia>();

                        list_http = gson.fromJson(str, new TypeToken<List<HealthPedia>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 1;
                        message.obj = list_http;
                        handler.sendMessage(message);

                        //从服务器返回的图片地址提取Bitmap列表
                        list_bitmap_message = list_http;
                        List<Bitmap> list_bitmap = new ArrayList<Bitmap>();
                        for (int i = 0; i < list_bitmap_message.size(); i++) {

                            HealthPedia healthPedia = (HealthPedia) list_bitmap_message.get(i);

                            Http_Bitmap utils_bitmap = new Http_Bitmap();
                            Bitmap bitmap = utils_bitmap.GetLocalOrNetBitmap_NoCompression(healthPedia.getCover());

                            list_bitmap.add(bitmap);

                        }

                        Message message_bitmap = new Message();
                        message_bitmap.what = 2;
                        message_bitmap.obj = list_bitmap;
                        handler.sendMessage(message_bitmap);
                    }

                });

            }
        }).start();
    }

    public void Http_Bitmap_Data(List<HealthPedia> list_bitmap_message) {
        this.list_bitmap_message = list_bitmap_message;

    }

}
