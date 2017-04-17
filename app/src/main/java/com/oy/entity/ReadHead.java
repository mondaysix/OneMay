package com.oy.entity;

import java.io.Serializable;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHead implements Serializable {

    /**
     * id : 111
     * title : 昨日重现
     * cover : http://image.wufazhuce.com/FgeoLpcgvKMRoA3e_jYjEuiv5CNu
     * bottom_text : 在近未来时代，人的记忆可以转让出售，并成为一个庞大的产业……
     * bgcolor : #4d4d4d
     * pv_url : http://v3.wufazhuce.com:8000/api/reading/carousel/pv/111
     */

    private String id;
    private String title;
    private String cover;
    private String bottom_text;
    private String bgcolor;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBottom_text() {
        return bottom_text;
    }

    public void setBottom_text(String bottom_text) {
        this.bottom_text = bottom_text;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }
}
