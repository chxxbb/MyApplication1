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
 * Created by Chen on 2016/7/18.
 */
public class Classroom extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.classroom, container, false);

        ListView listView = (ListView) view.findViewById(R.id.classroom_listview);
        ListAdapter listAdapter = new ListAdapter(getActivity());
        listView.setAdapter(listAdapter);


        List<ListItem> list = new ArrayList<ListItem>();

        //设定该窗口类型,并发送一个数据(该数据可自定义,在该页面可以用于判断用户点击的是哪个视频)
        ListItem item = new ListItem(7, "wori");
        list.add(item);
        //初始化
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(7, "ccc");
        list.add(item);
        item = null;


        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();

        return view;


    }

    public List<Map<String, Object>> getData() {

        //这里是要填入的图片,转成Bitmap格式
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img1", bitmap);
        map.put("img2", bitmap);
        map.put("text1", "fkjgsklafjgkladjfklajdflkajdsfkljasdlkfjalkdsfjkasdf");
        map.put("text2", "健康童年");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img1", bitmap);
        map.put("img2", bitmap);
        map.put("text1", "fkjgsklafjgkladjfklajdflkajdsfkljasdlkfjalkdsfjkasdf");
        map.put("text2", "健康童年");
        list.add(map);

        return list;
    }
}
