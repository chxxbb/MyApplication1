package com.example.chen.myapplication.data;

import android.graphics.Bitmap;

public class Doctor {

    private Integer id;

    private String name;

    private String username;

    private String password;

    private String title;

    private String section;

    private String hospital;

    private String adept;

    private String bio;

    private String icon;

    private Bitmap icon_bitmap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAdept() {
        return adept;
    }

    public void setAdept(String adept) {
        this.adept = adept;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Bitmap getIcon_bitmap() {
        return icon_bitmap;
    }

    public void setIcon_bitmap(Bitmap icon_bitmap) {
        this.icon_bitmap = icon_bitmap;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", title="
                + title + ", section=" + section + ", hospital=" + hospital + ", adept=" + adept + ", bio=" + bio
                + ", icon=" + icon + "]";
    }

}
