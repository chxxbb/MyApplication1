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
 * Created by Chen on 2016/6/27.
 */
public class Diagnosis_management extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis_management);

        //获取ListView控件,并创建自定义适配器
        ListView listView = (ListView) findViewById(R.id.diagnosis_management_listview);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.diagnosis_management_item,
                new String[]{"time", "department"}, new int[]{R.id.diagnosis_management_item_time_text,
                R.id.diagnosis_management_item_department_text});

        listView.setAdapter(adapter);

    }

    private List<Map<String, String>> getData() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("time", "2016-6-27");
        map.put("department", "儿童外科");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("time", "2016-6-22");
        map.put("department", "儿童外科");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("time", "2016-6-12");
        map.put("department", "儿童外科");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("time", "2016-6-1");
        map.put("department", "儿童外科");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("time", "2016-12-22");
        map.put("department", "儿童外科");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("time", "2016-6-22");
        map.put("department", "儿童外科");
        list.add(map);


        return list;
    }
}
