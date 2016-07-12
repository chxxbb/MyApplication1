package com.example.chen.myapplication.page;

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
    //设置一共有多少种布局
    public static final int TYPE_COUNT = 7;

    private String name;
    private int type;

    public ListItem(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
