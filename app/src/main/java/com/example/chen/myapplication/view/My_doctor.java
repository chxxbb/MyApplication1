package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/7/13.
 */
public class My_doctor extends Activity {

    ListView my_doctor_listview = null;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_doctor);

        my_doctor_listview = (ListView) findViewById(R.id.my_doctor_listview);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.my_doctor_page,
                new String[]{"name"},
                new int[]{R.id.my_doctor_page_name_text});
        my_doctor_listview.setAdapter(adapter);

    }

    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "G1");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "G2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "G3");
        list.add(map);

        return list;
    }
}
