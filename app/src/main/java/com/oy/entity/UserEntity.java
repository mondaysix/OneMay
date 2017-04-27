package com.oy.entity;

/**
 * Created by Meyki on 2017/4/19.
 */

public class UserEntity {
    public String username;
    public String pwd;
    public String img;

    public UserEntity() {
    }

    public UserEntity(String username, String pwd, String img) {
        this.username = username;
        this.pwd = pwd;
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
