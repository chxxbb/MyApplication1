package com.example.chen.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/13.
 */
public class Doctor_details extends Activity {

    SpannableString spanString = null;
    TextView doctor_details_introduction = null;
    TextView doctor_details_specialty = null;

    Button doctor_details_bottom_menu_botton_convention = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_details);

        doctor_details_introduction = (TextView) findViewById(R.id.doctor_details_introduction);
        doctor_details_specialty = (TextView) findViewById(R.id.doctor_details_specialty);

        spanString = new SpannableString("简介 : 的发送打飞机阿拉款到即发拉客敬佛i为此秒的vmaiomdaoi没法哦is的马佛is的没法哦的矛盾发生大幅阿斯蒂芬发给阿飞");
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
        spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色

        doctor_details_introduction.setText(spanString);

        spanString = new SpannableString("擅长 : 的发送打飞机阿拉款到即发拉客敬佛i为此秒的的说法打发没法哦is的马佛is的没法哦的矛盾发生大幅阿斯蒂芬发给阿飞");
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
        spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色
        doctor_details_specialty.setText(spanString);


        doctor_details_bottom_menu_botton_convention = (Button) findViewById(R.id.doctor_details_bottom_menu_botton_convention);
        doctor_details_bottom_menu_botton_convention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_details.this, Doctor_scheduling.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.doctor_details_listview);
        ListAdapter listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);
        List<ListItem> list = new ArrayList<ListItem>();

        //设定该窗口类型,并发送一个数据(该数据可自定义)
        ListItem item = new ListItem(4, "wori");
        list.add(item);

        item = new ListItem(4, "wori");
        list.add(item);

        item = new ListItem(4, "wori");
        list.add(item);

        item = new ListItem(4, "wori");
        list.add(item);

        item = new ListItem(4, "wori");
        list.add(item);

        item = new ListItem(4, "wori");
        list.add(item);

        item = new ListItem(4, "wori");
        list.add(item);

        //将List发送给自定义适配器
        listAdapter.setList(list);
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();


    }
}
