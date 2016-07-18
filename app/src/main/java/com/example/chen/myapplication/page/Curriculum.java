package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class Curriculum extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.curriculum, container, false);

        ListView listView = (ListView) view.findViewById(R.id.curriculum_listview);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.curriculum_item,
                new String[]{"text", "name", "time"},
                new int[]{R.id.curriculum_item_text, R.id.curriculum_item_name,
                        R.id.curriculum_item_time});

        listView.setAdapter(adapter);

        return view;
    }

    public List<Map<String, Object>> getData() {

        //这里是要填入的图片,转成Bitmap格式
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("text", "儿童多动症的危害（一）/");
        map.put("name", "付师婷教授");
        map.put("time", "2016-6-10 14：20");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("text", "儿童多动症的危害（一）/");
        map.put("name", "付师婷教授");
        map.put("time", "2016-6-10 14：20");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("text", "儿童多动症的危害（一）/");
        map.put("name", "付师婷教授");
        map.put("time", "2016-6-10 14：20");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("text", "儿童多动症的危害（一）/");
        map.put("name", "付师婷教授");
        map.put("time", "2016-6-10 14：20");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("text", "儿童多动症的危害（一）/");
        map.put("name", "付师婷教授");
        map.put("time", "2016-6-10 14：20");
        list.add(map);


        return list;
    }

}
