package com.example.chen.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Chen on 2016/6/1.
 */
public class Medical_registration extends Activity {

    Spinner medical_registration_region_spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_registration);

        init();
    }

    private void init() {

        medical_registration_region_spinner = (Spinner) findViewById(R.id.medical_registration_spinner1);

        //显示的数组
        final String arr[] = new String[]{
                "深圳",
                "成都",
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.medical_registration_sinner_item, arr);
        medical_registration_region_spinner.setAdapter(arrayAdapter);

    }
}
