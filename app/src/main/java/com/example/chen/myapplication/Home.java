package com.example.chen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/3.
 */
public class Home extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        //获取ListView控件,并创建自定义适配器
        ListView listView = (ListView) findViewById(R.id.list_main);
        ListAdapter listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);

        List<ListItem> list = new ArrayList<ListItem>();

        //设定该窗口类型,并发送一个数据(该数据可自定义)
        ListItem item = new ListItem(0, "wori");
        list.add(item);
        //初始化
        item = null;

        item = new ListItem(1, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(1, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(1, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(1, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(1, "ccc");
        list.add(item);
        item = null;

        item = new ListItem(1, "ccc");
        list.add(item);
        item = null;

        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();

    }


}
