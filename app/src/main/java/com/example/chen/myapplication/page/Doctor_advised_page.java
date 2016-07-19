package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Chen on 2016/7/19.
 */
public class Doctor_advised_page extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_advised_page, container, false);
        //获取ListView控件,并创建自定义适配器
        ListView listView = (ListView) view.findViewById(R.id.doctor_advised_page_listview);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.doctor_advised_page_item,
                new String[]{"time", "content"},
                new int[]{R.id.doctor_advised_page_item_time, R.id.doctor_advised_page_item_content_text});

        listView.setAdapter(adapter);


        return view;
    }

    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", "2016年7月19日 上午11:06");
        map.put("content", "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本 ");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("time", "2016年7月19日 上午11:06");
        map.put("content", "睡眠 ： ");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("time", "2016年7月19日 上午11:06");
        map.put("content", "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本 ");
        list.add(map);


        return list;
    }
}
