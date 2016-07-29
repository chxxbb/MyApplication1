package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chen.myapplication.data.Doctor;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.page.ListAdapter;
import com.example.chen.myapplication.page.ListItem;
import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/14.
 */
public class Doctor_scheduling extends Activity {

    Doctor doctor = null;

    ImageView doctor_scheduling_portrait = null;
    TextView doctor_scheduling_name = null, doctor_scheduling_level = null, doctor_scheduling_department = null, doctor_scheduling_hospital = null;

    int xc = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what = 1) {
                case 1:
                    doctor_scheduling_portrait.setImageBitmap(doctor.getIcon_bitmap());
                    xc = 1;
                    break;
                case 2:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_scheduling);

        doctor = HTTP_data.doctor;

        doctor_scheduling_portrait = (ImageView) findViewById(R.id.doctor_scheduling_portrait);

        doctor_scheduling_name = (TextView) findViewById(R.id.doctor_scheduling_name);
        doctor_scheduling_level = (TextView) findViewById(R.id.doctor_scheduling_level);
        doctor_scheduling_department = (TextView) findViewById(R.id.doctor_scheduling_department);
        doctor_scheduling_hospital = (TextView) findViewById(R.id.doctor_scheduling_hospital);

        doctor_scheduling_name.setText(doctor.getName());
        doctor_scheduling_level.setText(doctor.getTitle());
        doctor_scheduling_department.setText(doctor.getSection());
        doctor_scheduling_hospital.setText(doctor.getHospital());

        if (doctor.getIcon_bitmap() != null) {
            doctor_scheduling_portrait.setImageBitmap(doctor.getIcon_bitmap());
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (xc == 0) {
                        try {
                            Thread.sleep(5000);
                            if (doctor.getIcon_bitmap() != null) {
                                Message msg = new Message();
                                msg.what = 1;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            System.out.println("thread error...");
                        }
                    }
                }
            }).start();
        }


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
