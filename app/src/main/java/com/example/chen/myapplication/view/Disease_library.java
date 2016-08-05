package com.example.chen.myapplication.view;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.Disease;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.utils.Http_Bitmap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


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
 * Created by Chen on 2016/7/5.
 */
public class Disease_library extends Activity {
    ListView listview1, listview2;
    ImageView imageview_item_1;
    RelativeLayout disease_library_left_item_relativelayout = null;

    TextView http_data = null;

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

                    listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            http_data = (TextView) view.findViewById(R.id.disease_library_right_item_text);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    OkHttpUtils.postString()
                                            .url(HTTP_data.http_data + "/findDiseaseName")
                                            .content(http_data.getText().toString())
                                            .build()
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e, int id) {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(Disease_library.this, "获取病种失败,请重试.", Toast.LENGTH_LONG).show();
                                                        }
                                                    });

                                                }

                                                @Override
                                                public void onResponse(String response, int id) {
                                                    System.out.println("连接成功!!!!!");
                                                    Disease disease = gson.fromJson(response, Disease.class);

                                                    HTTP_data.disease = disease;

                                                    Intent intent = new Intent(Disease_library.this, Details_disease.class);
                                                    startActivity(intent);

                                                }
                                            });
                                }
                            }).start();
                        }
                    });

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
        map.put("text_one", "加载中");
        list.add(map);

        return list;
    }

    public List<String> getDatatow() {
        List<String> data = new ArrayList<String>();
        data.add("加载中...");

        return data;
    }

    private void init_http_data() {
        //网络交互开始
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = RequestBody.create(JSON, "");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findSectionList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
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
            public void run() {
                RequestBody requestBody = RequestBody.create(JSON, gson.toJson(position));
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findDiseaseList").post(requestBody).build();

                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();

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
