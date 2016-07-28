package com.example.chen.myapplication.page;

import com.example.chen.myapplication.data.Doctor;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/6/4.
 */
public class ListItem {

    //设定List的布局状态
    public static final int TYPE_TOP = 0;
    public static final int TYPE_BOTTON = 1;
    public static final int TYPE_DOCTOR_WAREHOUSE = 2;
    public static final int TYPE_DISEASE_SELF_TEST = 3;
    public static final int TYPE_DOCTOR_DETAILS_COMMENTS = 4;
    public static final int TYPE_DOCTOR_SCHEDULING = 5;
    public static final int TYPE_MESSAGE = 6;
    public static final int TYPE_CLASSROOM = 7;
    //设置一共有多少种布局
    public static final int TYPE_COUNT = 8;

    private String name;
    private int type;
    private Doctor doctor;
    private Map<String, Object> map;

    public ListItem(int type) {
        this.type = type;
    }

    public ListItem(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public ListItem(int type, Doctor doctor) {
        this.type = type;
        this.doctor = doctor;
    }

    public ListItem(int type, Map<String, Object> map) {
        this.type = type;
        this.map = map;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
