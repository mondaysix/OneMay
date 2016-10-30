package com.oy.entity;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ContentEntity {
    private String hpcontent_id;
    private String hp_title;
    private String author_id;
    private String hp_img_url;
    private String hp_author;
    private String hp_content;
    private String hp_makettime;
    private String last_update_date;
    private int praisenum;
    private int sharenum;
    private int commentnum;

    public ContentEntity(String hpcontent_id, String hp_title, String author_id, String hp_img_url, String hp_author, String hp_content, String hp_makettime, String last_update_date, int praisenum, int sharenum, int commentnum) {
        this.hpcontent_id = hpcontent_id;
        this.hp_title = hp_title;
        this.author_id = author_id;
        this.hp_img_url = hp_img_url;
        this.hp_author = hp_author;
        this.hp_content = hp_content;
        this.hp_makettime = hp_makettime;
        this.last_update_date = last_update_date;
        this.praisenum = praisenum;
        this.sharenum = sharenum;
        this.commentnum = commentnum;
    }

    public String getHpcontent_id() {
        return hpcontent_id;
    }

    public void setHpcontent_id(String hpcontent_id) {
        this.hpcontent_id = hpcontent_id;
    }

    public String getHp_title() {
        return hp_title;
    }

    public void setHp_title(String hp_title) {
        this.hp_title = hp_title;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getHp_img_url() {
        return hp_img_url;
    }

    public void setHp_img_url(String hp_img_url) {
        this.hp_img_url = hp_img_url;
    }

    public String getHp_author() {
        return hp_author;
    }

    public void setHp_author(String hp_author) {
        this.hp_author = hp_author;
    }

    public String getHp_content() {
        return hp_content;
    }

    public void setHp_content(String hp_content) {
        this.hp_content = hp_content;
    }

    public String getHp_makettime() {
        return hp_makettime;
    }

    public void setHp_makettime(String hp_makettime) {
        this.hp_makettime = hp_makettime;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(int praisenum) {
        this.praisenum = praisenum;
    }

    public int getSharenum() {
        return sharenum;
    }

    public void setSharenum(int sharenum) {
        this.sharenum = sharenum;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }
}
