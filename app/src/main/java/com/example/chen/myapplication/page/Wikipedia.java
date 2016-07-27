package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.HealthPedia;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.data.User_data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.commons.io.IOUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                RequestBody requestBody = RequestBody.create(JSON, gson.toJson(User_data.user));
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findHealthPediaList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        String str = response.body().string();

                        System.out.println(str);

                        List list_http = new ArrayList<HealthPedia>();

                        list_http = gson.fromJson(str, new TypeToken<List<HealthPedia>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 1;
                        message.obj = list_http;
                        handler.sendMessage(message);

//                        Http_Bitmap_Data(list_http);

                        list_bitmap_message = list_http;
                        List<Bitmap> list_bitmap = new ArrayList<Bitmap>();
                        for (int i = 0; i < list_bitmap_message.size(); i++) {
                            HealthPedia healthPedia = (HealthPedia) list_bitmap_message.get(i);

                            Bitmap bitmap = GetLocalOrNetBitmap(healthPedia.getCover());

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

    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     * <p/>
     * A.网络路径: url=&quot;http://blog.foreverlove.us/girl2.png&quot; ;
     * <p/>
     * B.本地路径:url=&quot;file://mnt/sdcard/photo/image.png&quot;;
     * <p/>
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @param url
     * @return
     */
    public static Bitmap GetLocalOrNetBitmap(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new URL(url).openStream(), 2 * 1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, 2 * 1024);
            IOUtil.copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = null;
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
