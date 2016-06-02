package com.example.chen.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by Chen on 2016/6/1.
 * 一键挂号页面
 */
public class Medical_registration extends Activity implements View.OnFocusChangeListener, View.OnClickListener {

    Spinner medical_registration_region_spinner = null;
    Spinner medical_registration_department_spinner = null;
    Spinner medical_registration_doctor_title_spinner = null;
    Spinner medical_registration_time_spinner = null;
    EditText medical_registration_name_edittext = null;
    EditText medical_registration_age_edittext = null;
    EditText medical_registration_contact_edittext = null;
    EditText medical_registration_describe_edittext = null;
    ImageView medical_registration_exit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_registration);

        //下拉列表的初始化
        init_spinner();

        //edittext和其提示信息的初始化
        init_edit_hint();

        init();
    }

    private void init() {
        medical_registration_exit = (ImageView) findViewById(R.id.medical_registration_exit);
        medical_registration_exit.setOnClickListener(this);
    }


    private void init_spinner() {

        //地区数据填入
        medical_registration_region_spinner = (Spinner) findViewById(R.id.medical_registration_spinner_region);

        //显示的数组
        final String arr_region[] = new String[]{
                "深圳",
                "成都",
        };

        ArrayAdapter<String> arrayAdapter__region = new ArrayAdapter<String>(this, R.layout.medical_registration_sinner_item, arr_region);
        medical_registration_region_spinner.setAdapter(arrayAdapter__region);


        //科室数据填入
        medical_registration_department_spinner = (Spinner) findViewById(R.id.medical_registration_spinner_department);

        //显示的数组
        final String arr_department[] = new String[]{
                "儿科",
                "急诊",
        };

        ArrayAdapter<String> arrayAdapter_department = new ArrayAdapter<String>(this, R.layout.medical_registration_sinner_item, arr_department);
        medical_registration_department_spinner.setAdapter(arrayAdapter_department);


        //职称数据填入
        medical_registration_doctor_title_spinner = (Spinner) findViewById(R.id.medical_registration_spinner_doctor_title);

        //显示的数组
        final String arr_doctor_title[] = new String[]{
                "教授",
                "副教授",
                "主治医师",
        };

        ArrayAdapter<String> arrayAdapter_doctor_title = new ArrayAdapter<String>(this, R.layout.medical_registration_sinner_item, arr_doctor_title);
        medical_registration_doctor_title_spinner.setAdapter(arrayAdapter_doctor_title);

        //时间数据填入
        medical_registration_time_spinner = (Spinner) findViewById(R.id.medical_registration_spinner_time);

        //显示的数组
        final String arr_time[] = new String[]{
                "上午",
                "下午",
                "晚上",
        };

        ArrayAdapter<String> arrayAdapter_time = new ArrayAdapter<String>(this, R.layout.medical_registration_sinner_item, arr_time);
        medical_registration_time_spinner.setAdapter(arrayAdapter_time);


    }

    private void init_edit_hint() {

        //监听edittext的焦点变化
        medical_registration_name_edittext = (EditText) findViewById(R.id.medical_registration_name_edittext);
        medical_registration_name_edittext.setOnFocusChangeListener(this);

        medical_registration_age_edittext = (EditText) findViewById(R.id.medical_registration_age_edittext);
        medical_registration_age_edittext.setOnFocusChangeListener(this);

        medical_registration_contact_edittext = (EditText) findViewById(R.id.medical_registration_contact_edittext);
        medical_registration_contact_edittext.setOnFocusChangeListener(this);

        medical_registration_describe_edittext = (EditText) findViewById(R.id.medical_registration_describe_edittext);
        medical_registration_describe_edittext.setOnFocusChangeListener(this);


    }


    //若焦点变化,则清除hint的显示内容
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText _v = (EditText) v;
        if (!hasFocus) {// 失去焦点
            _v.setHint(_v.getTag().toString());
        } else {
            String hint = _v.getHint().toString();
            _v.setTag(hint);
            _v.setHint("");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.medical_registration_exit:
                finish();
        }
    }
}
