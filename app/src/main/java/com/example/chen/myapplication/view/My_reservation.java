package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/7/14.
 */
public class My_reservation extends Activity {

    ListView my_reservation_listview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reservation);

        my_reservation_listview = (ListView) findViewById(R.id.my_reservation_listview);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.my_reservation_item,
                new String[]{"type", "number", "time"},
                new int[]{R.id.my_reservation_item_top_type_text, R.id.my_reservation_item_botton_number_text,
                        R.id.my_reservation_item_botton_time_text});

        my_reservation_listview.setAdapter(adapter);

    }


    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "已过期");
        map.put("number", "订单号 : 123456789630258");
        map.put("time", "有效期 : 2016-7-5");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("type", "已完成");
        map.put("number", "订单号 : 123456789630258");
        map.put("time", "有效期 : 2016-7-5");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("type", "已预约");
        map.put("number", "订单号 : 123456789630258");
        map.put("time", "有效期 : 2016-7-5");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("type", "已取消");
        map.put("number", "订单号 : 123456789630258");
        map.put("time", "有效期 : 2016-7-5");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("type", "已完成");
        map.put("number", "订单号 : 123456789630258");
        map.put("time", "有效期 : 2016-7-5");
        list.add(map);


        return list;
    }

}
