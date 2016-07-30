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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User_data;
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
public class Curriculum extends Fragment {

    ListView listView;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            List<Map<String, String>> list_data;

            list_data = (List<Map<String, String>>) msg.obj;

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), list_data, R.layout.curriculum_item,
                    new String[]{"title", "doctorName", "lectureTime"},
                    new int[]{R.id.curriculum_item_text, R.id.curriculum_item_name,
                            R.id.curriculum_item_time});
            listView.setAdapter(adapter);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.curriculum, container, false);
        listView = (ListView) view.findViewById(R.id.curriculum_listview);

        init_http_data();


//        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.curriculum_item,
//                new String[]{"title", "doctorName", "lectureTime"},
//                new int[]{R.id.curriculum_item_text, R.id.curriculum_item_name,
//                        R.id.curriculum_item_time});
//
//        listView.setAdapter(adapter);

        return view;
    }


    public List<Map<String, String>> getData() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "儿童多动症的危害（一）/");
        map.put("doctorName", "付师婷教授");
        map.put("lectureTime", "2016-6-10 14：20");
        list.add(map);

        return list;
    }

    private void init_http_data() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(JSON, "请求课程表");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findSyllabusList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();

                        List<Map<String, String>> http_list = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
                        }.getType());

                        Message message = new Message();
                        message.obj = http_list;
                        handler.sendMessage(message);
                    }

                });

            }
        }).start();

    }

}
