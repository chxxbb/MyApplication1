package com.example.chen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/14.
 */
public class Doctor_scheduling extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_scheduling);

        ListView listView = (ListView) findViewById(R.id.doctor_scheduling_listview);
        ListAdapter listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);
        List<ListItem> list = new ArrayList<ListItem>();

        //设定该窗口类型,并发送一个数据(该数据可自定义)
        ListItem item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        item = new ListItem(5, "wori");
        list.add(item);

        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();

    }
}
