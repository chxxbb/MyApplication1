package com.example.chen.myapplication.data;

public class Comment {

    private Integer id;

    private String userName;

    private String content;

    private Integer doctorId;

    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", userName=" + userName + ", content=" + content + ", doctorId=" + doctorId
                + ", icon=" + icon + "]";
    }


}
