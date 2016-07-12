package com.example.chen.myapplication.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen on 2016/6/28.
 */
public class Patient_management extends Activity {

    private ListView listView = null;
    private List<String> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_management);

        listView = (ListView) findViewById(R.id.patient_management_listview);

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.patient_management_item, getData()));


    }

    public List<String> getData() {

        List<String> data = new ArrayList<String>();
        data.add("尧舜禹");
        data.add("诸葛孔明");
        data.add("张飞");
        data.add("关羽关二爷");

        return data;
    }
}
