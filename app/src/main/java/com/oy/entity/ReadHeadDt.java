package com.oy.entity;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHeadDt {

    /**
     * item_id : 866
     * title : 与妻
     * introduction : 郁结，唯一让我感到欣慰的是，十天之后，我回到北京，看见下班之后，在公共汽车站等车的你。我一定要绕过买菜回家的大妈，绕过地下道卖唱的青年，绕过西装革履的白领，冲过去抱住你。
     * author : mlln
     * web_url :
     * number : 0
     * type : 1
     */

    private String item_id;
    private String title;
    private String introduction;
    private String author;
    private String web_url;
    private int number;
    private String type;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
