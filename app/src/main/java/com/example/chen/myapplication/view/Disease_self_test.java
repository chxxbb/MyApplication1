package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.chen.myapplication.page.ListAdapter;
import com.example.chen.myapplication.page.ListItem;
import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/10.
 */
public class Disease_self_test extends Activity {

    ListView disease_self_test_listview = null;
    ListItem item = null;
    ListView listView = null;
    ListAdapter listAdapter = null;
    List<ListItem> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_self_test);

        listView = (ListView) findViewById(R.id.disease_self_test_listview);
        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);

        initData();

    }

    private void initData() {
        list = null;

        list = new ArrayList<ListItem>();

        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(3, "头部");
        list.add(item);
        //初始化
        item = null;

        item = new ListItem(3, "颈部");
        list.add(item);
        item = null;

        item = new ListItem(3, "胸部");
        list.add(item);
        item = null;

        item = new ListItem(3, "背部");
        list.add(item);
        item = null;

        item = new ListItem(3, "上肢");
        list.add(item);
        item = null;

        item = new ListItem(3, "腹部");
        list.add(item);
        item = null;

        item = new ListItem(3, "盆骨部位");
        list.add(item);
        item = null;

        item = new ListItem(3, "腰部");
        list.add(item);
        item = null;

        item = new ListItem(3, "下肢");
        list.add(item);
        item = null;

        item = new ListItem(3, "其他");
        list.add(item);
        item = null;

        listView.setDividerHeight(0);

        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();
    }
}
