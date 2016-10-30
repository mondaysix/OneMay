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
}
