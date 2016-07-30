package com.example.chen.myapplication.data;

import android.graphics.Bitmap;

public class User {

    private int id;

    private String phone = null;

    private String name;

    private String password;

    private String sex;

    private int age;

    private String icon;

    private Bitmap bitmap_icon = null;

    public Bitmap getBitmap_icon() {
        return bitmap_icon;
    }

    public void setBitmap_icon(Bitmap bitmap_icon) {
        this.bitmap_icon = bitmap_icon;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
