package com.example.chen.myapplication.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;

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

    //用于保存用户点击过的item,已完成点击其他item后,已变化的item能够恢复原样
    ImageView image_item_xxx = null;
    RelativeLayout disease_library_left_item_relativelayout_xxx = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_library);

        listview1 = (ListView) findViewById(R.id.disease_library_listview_left);
        listview2 = (ListView) findViewById(R.id.disease_library_listview_right);

        //加载右边listview的内容
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.disease_library_left_item, new String[]{"image_one", "image_tow", "text_one"},
                new int[]{R.id.disease_library_left_item_image1, R.id.disease_library_left_item_image2, R.id.disease_library_left_item_text1});

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
                    listview2.setAdapter(new ArrayAdapter<String>(Disease_library.this, R.layout.disease_library_right_item, getDatatow()));

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
                    listview2.setAdapter(new ArrayAdapter<String>(Disease_library.this, R.layout.disease_library_right_item, getDatatow()));

                }


            }
        });

        listview1.setAdapter(adapter);

    }

    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "Fuck");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "dDD");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "CCC");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "BBB");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "AAA");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image_tow", R.mipmap.ic_launcher);
        map.put("text_one", "EEE");
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
}
