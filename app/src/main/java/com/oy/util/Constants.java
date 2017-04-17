package com.oy.util;

/**
 * Created by Lucky on 2016/10/30.
 */
public interface Constants {
     String BASE_URL="http://v3.wufazhuce.com:8000/api/";
    //-----------------首页
    //首页id
     String HOME_URL="hp/idlist/0?version=3.5.0&platform=android";
    //根据id获得内容区
    String HOME_CONTENT_URL="hp/detail/%d?version=3.5.0&platform=android";
    //------------------阅读页
    //---头部viewpager
    String READ_HEAD="reading/carousel/?version=3.5.0&platform=android";
    //---头部图片详情页
    String READ_HEAD_DETAIL="reading/carousel/%d?version=3.5.0&platform=android";
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
    String KEY = "89f7ab1d0d29cd785bd83dc6c9c2e788";
    String BASE_Weather_URL = "http://v.juhe.cn/weather/";
    String CITY_WETHER = "index?format=2&cityname=%s&key="+KEY;
    String CITYS="citys?key="+KEY;
}
