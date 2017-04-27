package com.oy.util;




/**
 * Created by Lucky on 2016/10/30.
 */
public interface Constants {
     String BASE_URL="http://v3.wufazhuce.com:8000/api/";
    //new url
    String DOM_URL = "http://192.168.1.115:1883/";
    //192.168.43.172----mm
    //192.168.6.106-----xiaomm
    //192.168.1.104----wzry 105
    //192.168.1.113----smanos_cc 115
    //读取本地相册照片
    String TYPE = "image/*";
    int REQUESTCODE = 111;
    //qq登录appid
    String APPID = "1106106814";
    //img url
    String IMG_URL = DOM_URL+"uploads/";
    //song url
    String SONG_URL = DOM_URL + "musics/";
    //-----------------首页
    //首页id
     String HOME_URL="hp/idlist/0?version=3.5.0&platform=android";
    //根据id获得内容区
    String HOME_CONTENT_URL="hp/detail/%d?version=3.5.0&platform=android";
    //首页点赞数更新
    String LAUD_UPDATE = "hp/lauds/";
    //------------------阅读页
    //---头部viewpager
    String READ_HEAD2="reading/head/?version=3.5.0&platform=android";
    //---头部图片详情页
    String READ_HEAD_DETAIL2="reading/headDetail/%d?version=3.5.0&platform=android";
    //内容详情页
    String READ_CONTENT="reading/index/?version=3.5.0&platform=android";
    //------------------音乐页
    //音乐id
    String MUSIC_URL="music/idlist/0?version=3.5.0&platform=android";
    //音乐详情
    String MUSIC_DETAIL="music/detail/%d?version=3.5.0&platform=android";
    //音乐评论区详情
    String MUSIC_COMMENT="comment/praiseandtime/music/%d/0?version=3.5.0&platform=android";
    //------------------电影页
    //电影列表显示
    String MOVIE_HOME="movie/list/0?version=3.5.0&platform=android";
    //电影简单介绍
    String MOVIE_DETAIL="movie/%d/story/1/0?version=3.5.0&platform=android";
    //电影表
    String MOVIE_TABLE="movie/detail/%d?version=3.5.0&platform=android";
    //评论详情
    String MOVIE_COMMENT="comment/praiseandtime/movie/%d/0?version=3.5.0&platform=android";
    //------全国一周内天气----
    String KEY = "89f7ab1d0d29cd785bd83dc6c9c2e788";
    String BASE_Weather_URL = "http://v.juhe.cn/weather/";
    String CITY_WETHER = "index?format=2&cityname=%s&key="+KEY;
    String CITYS="citys?key="+KEY;

    //------------登录注册---
    String USER_CODE = "user/regcode/";
    String REGISTER = "user/register/";
    int[] QOS_VALUES = {0,1};
    String[] TOPICS = {
            "user/getPic",
            "userpic"
    };


}
