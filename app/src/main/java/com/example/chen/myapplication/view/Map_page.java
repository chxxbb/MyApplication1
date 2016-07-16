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
 * Created by Chen on 2016/7/16.
 */
public class Map_page extends Activity {

    ListView map_listview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        map_listview = (ListView) findViewById(R.id.map_listview);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.map_item,
                new String[]{"hospital", "address", "phone"},
                new int[]{R.id.map_hospital_name_text, R.id.map_hospital_address_text, R.id.map_hospital_phone_text});

        map_listview.setAdapter(adapter);

    }


    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hospital", "深圳天使儿童医院");
        map.put("address", "深圳市龙华新区工业西路97号");
        map.put("phone", "13547955233");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("hospital", "成都天使儿童医院");
        map.put("address", "成都市人民南路上善国际");
        map.put("phone", "12345678910");
        list.add(map);


        return list;
    }

}
