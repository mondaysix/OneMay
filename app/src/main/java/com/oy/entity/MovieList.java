package com.oy.entity;

import java.io.Serializable;

/**
 * Created by Lucky on 2016/11/1.
 */
public class MovieList implements Serializable{

    /**
     * id : 149
     * title : 驴得水
     * verse :
     * verse_en :
     * score : 82
     * revisedscore : 0
     * releasetime : 2016-10-28 00:00:00
     * scoretime : 2016-10-28 00:00:00
     * cover : http://image.wufazhuce.com/Fl5fGQatJVhtUDcvUep_kC-uIpbi
     * servertime : 1477963653
     */
    private String id;
    private String title;
    private String score;
    private String releasetime;
    private String scoretime;
    private String cover;
    private int servertime;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getScoretime() {
        return scoretime;
    }

    public void setScoretime(String scoretime) {
        this.scoretime = scoretime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getServertime() {
        return servertime;
    }

    public void setServertime(int servertime) {
        this.servertime = servertime;
    }
}
