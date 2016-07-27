package com.example.chen.myapplication.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.HealthPedia;
import com.example.chen.myapplication.data.User_data;
import com.example.chen.myapplication.utils.Http_Bitmap;
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
 * Created by Chen on 2016/7/5.
 */
public class Disease_library extends Activity {
    ListView listview1, listview2;
    ImageView imageview_item_1;
    RelativeLayout disease_library_left_item_relativelayout = null;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    List<Map<String, String>> list_left_http;
    List<Map<String, String>> list_left;
    List<Map<String, Object>> list;


    //用于保存用户点击过的item,已完成点击其他item后,已变化的item能够恢复原样
    ImageView image_item_xxx = null;
    RelativeLayout disease_library_left_item_relativelayout_xxx = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Map<String, Object> map;

            switch (msg.what) {
                case 1:
                    list = new ArrayList<Map<String, Object>>();
                    list_left = (List<Map<String, String>>) msg.obj;

                    for (int i = 0; i < list_left.size(); i++) {
                        map = new HashMap<String, Object>();

                        map.put("image_tow", R.mipmap.ic_launcher);
                        map.put("text_one", list_left.get(i).get("name"));
                        list.add(map);
                    }

                    SimpleAdapter adapter = new SimpleAdapter(Disease_library.this, list, R.layout.disease_library_left_item, new String[]{"text_one"},
                            new int[]{R.id.disease_library_left_item_text1});

                    listview1.setAdapter(adapter);

                    break;


                case 2:

                    for (int i = 0; i < list_left.size(); i++) {
                        map = new HashMap<String, Object>();

                        List<Bitmap> list_http_bitmap = (List<Bitmap>) msg.obj;

                        list.get(i).put("image_tow", list_http_bitmap.get(i));

                    }


                    SimpleAdapter adapter1 = new SimpleAdapter(Disease_library.this, list, R.layout.disease_library_left_item, new String[]{"image_tow", "text_one"},
                            new int[]{R.id.disease_library_left_item_image2, R.id.disease_library_left_item_text1});

                    //帮助我们设置SimpleAdapter可以传Bitmap图像
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

                    listview1.setAdapter(adapter1);

                    break;

                case 3:
                    List<String> data = (List<String>) msg.obj;

                    listview2.setAdapter(new ArrayAdapter<String>(Disease_library.this, R.layout.disease_library_right_item, data));

                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_library);

        listview1 = (ListView) findViewById(R.id.disease_library_listview_left);
        listview2 = (ListView) findViewById(R.id.disease_library_listview_right);

        init_http_data();

        //加载左边listview的内容
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.disease_library_left_item, new String[]{"image_tow", "text_one"},
                new int[]{R.id.disease_library_left_item_image2, R.id.disease_library_left_item_text1});


        initClick();

        listview1.setAdapter(adapter);

    }


    private void initClick() {
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (image_item_xxx == null) {   //判断是否是第一次点击item(因为第一次点击之前,没有需要恢复原样的item)

                    imageview_item_1 = (ImageView) view.findViewById(R.id.disease_library_left_item_image1);
                    imageview_item_1.setImageResource(R.mipmap.disease_library_item_left_image);
                    disease_library_left_item_relativelayout = (RelativeLayout) view.findViewById(R.id.disease_library_left_item_relativelayout);
                    disease_library_left_item_relativelayout.setBackgroundColor(Color.WHITE);

                    disease_library_left_item_relativelayout_xxx = disease_library_left_item_relativelayout;
                    image_item_xxx = imageview_item_1;
                    init_tow_data(position);

                } else {    //如果不是第一次点击,先恢复上一次改变的item,再改变本次点击的item样式
                    image_item_xxx.setVisibility(View.INVISIBLE);
                    disease_library_left_item_relativelayout_xxx.setBackgroundColor(0xf4f4f4);
                    imageview_item_1 = (ImageView) view.findViewById(R.id.disease_library_left_item_image1);
                    imageview_item_1.setImageResource(R.mipmap.disease_library_item_left_image);
                    imageview_item_1.setVisibility(View.VISIBLE);
                    disease_library_left_item_relativelayout = (RelativeLayout) view.findViewById(R.id.disease_library_left_item_relativelayout);
                    disease_library_left_item_relativelayout.setBackgroundColor(Color.WHITE);

                    disease_library_left_item_relativelayout_xxx = disease_library_left_item_relativelayout;
                    image_item_xxx = imageview_item_1;

                    //每一次点击item,根据点击的item的不同,加载右边的listview
                    init_tow_data(position);
                }


            }
        });
    }


    public List<Map<String, Object>> getData() {

        list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "Fuck");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "dDD");
        list.add(map);

        return list;
    }

    public List<String> getDatatow() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }

    private void init_http_data() {
        //网络交互开始
        new Thread(new Runnable() {
            @Override
            public void run() {     //准备Banner图片地址
                RequestBody requestBody = RequestBody.create(JSON, "");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findSectionList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        String str = response.body().string();

                        list_left_http = new ArrayList<Map<String, String>>();

                        list_left_http = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 1;
                        message.obj = list_left_http;
                        handler.sendMessage(message);

                        List<Bitmap> list_bitmap = new ArrayList<Bitmap>();
                        for (int i = 0; i < list_left_http.size(); i++) {

                            Http_Bitmap utils_bitmap = new Http_Bitmap();

                            Bitmap bitmap = utils_bitmap.GetLocalOrNetBitmap_NoCompression(list_left_http.get(i).get("icon"));

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

    private void init_tow_data(final int position) {
//        listview2.setAdapter(new ArrayAdapter<String>(Disease_library.this, R.layout.disease_library_right_item, getDatatow()));

        new Thread(new Runnable() {
            @Override
            public void run() {     //准备Banner图片地址
                RequestBody requestBody = RequestBody.create(JSON, gson.toJson(position));
                System.out.println(gson.toJson(position));
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findDiseaseList").post(requestBody).build();

                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String str = response.body().string();
                        System.out.println(str);

                        List<String> list_right = new ArrayList<String>();

                        list_right = gson.fromJson(str, new TypeToken<List<String>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 3;
                        message.arg1 = position;
                        message.obj = list_right;
                        handler.sendMessage(message);

                    }
                });
            }
        }).start();

    }

}
