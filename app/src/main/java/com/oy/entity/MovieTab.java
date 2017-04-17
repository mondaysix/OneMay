package com.oy.entity;

import java.util.List;

/**
 * Created by Lucky on 2016/11/1.
 */
public class MovieTab {

    private String id;
    private String title;
    private String detailcover;
    private String video;
    private String keywords;
    private String movie_id;
    private String info;
    private List<String> photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailcover() {
        return detailcover;
    }

    public void setDetailcover(String detailcover) {
        this.detailcover = detailcover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }
}
