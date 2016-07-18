package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/7/18.
 */
public class Wikipedia extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wikipedia, container, false);

        ListView listview = (ListView) view.findViewById(R.id.wikipedia_news_listview);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.news_item,
                new String[]{"img", "title", "text"},
                new int[]{R.id.news_item_image, R.id.news_item_title_text,
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

        //这里是要填入的图片,转成Bitmap格式
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfddddddddddddddddddddddddddddddddddddddddddddddddddddda");
        map.put("text", "adfadddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddf");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfddddddddddddddddddddddddddddddddddddddddddddddddddddda");
        map.put("text", "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddf" +
                "ddddddddddddddddddddddddddddddddddddddddddf");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfa");
        map.put("text", "adfadf");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfddddddddddddddddddddddddddddddddddddddddddddddddddddda");
        map.put("text", "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddf" +
                "ddddddddddddddddddddddddddddddddddddddddddf");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfddddddddddddddddddddddddddddddddddddddddddddddddddddda");
        map.put("text", "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddf" +
                "ddddddddddddddddddddddddddddddddddddddddddf");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfddddddddddddddddddddddddddddddddddddddddddddddddddddda");
        map.put("text", "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddf" +
                "ddddddddddddddddddddddddddddddddddddddddddf");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img", bitmap);
        map.put("title", "sdfdsafadfddddddddddddddddddddddddddddddddddddddddddddddddddddda");
        map.put("text", "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddf" +
                "ddddddddddddddddddddddddddddddddddddddddddf");
        list.add(map);

        return list;
    }
}
