package com.example.chen.myapplication;

/**
 * Created by Chen on 2016/6/4.
 */
public class ListItem {

    //设定List的布局状态
    public static final int TYPE_TOP = 0;
    public static final int TYPE_BOTTON = 1;
    //设置一共有多少种布局
    public static final int TYPE_COUNT = 2;

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