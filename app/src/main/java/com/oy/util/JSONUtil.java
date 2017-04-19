package com.oy.util;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oy.entity.CityEntity;
import com.oy.entity.ContentEntity;
import com.oy.entity.MovieComment;
import com.oy.entity.MovieList;
import com.oy.entity.MovieStory;
import com.oy.entity.MovieTab;
import com.oy.entity.MusicComment;
import com.oy.entity.MusicContent;
import com.oy.entity.ReadContent;
import com.oy.entity.ReadHead;
import com.oy.entity.ReadHeadDt;
import com.oy.entity.WeatherEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/30.
 */
public class JSONUtil {
    //首页page个数
    public static List<Integer> getHomePageNum(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            TypeToken<List<Integer>> tt = new TypeToken<List<Integer>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //首页内容区
    public static ContentEntity getContent(String json){
        ContentEntity contentEntity;
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.getJSONObject("data");
            contentEntity = new ContentEntity(
                    jsonObject.getInt("hpcontent_id"),
                    jsonObject.getString("hp_title"),
                    jsonObject.getString("author_id"),
                    jsonObject.getString("hp_img_url"),
                    jsonObject.getString("hp_author"),
                    jsonObject.getString("hp_content"),
                    jsonObject.getString("hp_maketime"),
                    jsonObject.getString("last_update_date"),
                    jsonObject.getInt("praisenum"),
                    jsonObject.getInt("sharenum"),
                    jsonObject.getInt("commentnum")
            );
           return contentEntity;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //阅读页头部viewpager
    public static List<ReadHead> getReadHs(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<ReadHead>> tt = new TypeToken<List<ReadHead>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //阅读页面头部详情
    public static List<ReadHeadDt> getReadHDetail(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<ReadHeadDt>> tt = new TypeToken<List<ReadHeadDt>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType()) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //阅读页面内容区
    public static ReadContent getReadContent(String json){
        ReadContent readContent = new ReadContent();
        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject("data");

            JSONArray ja1 = jsonObject.getJSONArray("essay");
            TypeToken<List<ReadContent.EssayContent>> tt1 = new TypeToken<List<ReadContent.EssayContent>>(){};
            readContent.setEssay((List<ReadContent.EssayContent>) new Gson().fromJson(ja1.toString(),tt1.getType()));

            JSONArray ja2 =jsonObject.getJSONArray("serial");
            TypeToken<List<ReadContent.Serial>> tt2 = new TypeToken<List<ReadContent.Serial>>(){};
            readContent.setSerial((List<ReadContent.Serial>) new Gson().fromJson(ja2.toString(),tt2.getType()));

            JSONArray ja3 =jsonObject.getJSONArray("question");
            TypeToken<List<ReadContent.Question>> tt3 = new TypeToken<List<ReadContent.Question>>(){};
            readContent.setQuestion((List<ReadContent.Question>) new Gson().fromJson(ja3.toString(),tt3.getType()));
            return readContent;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //音乐页----音乐id
    public static List<Integer> getMusicIds(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<Integer>> tt = new TypeToken<List<Integer>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //音乐页----音乐内容
    public static MusicContent getMusicCon(String json){
        MusicContent musicContent = new MusicContent();
        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject("data");
            musicContent.setId(jsonObject.getString("id"));
            musicContent.setTitle(jsonObject.getString("title"));
            musicContent.setCover(jsonObject.getString("cover"));
            musicContent.setStory_title(jsonObject.getString("story_title"));
            musicContent.setStory(jsonObject.getString("story"));
            musicContent.setLyric(jsonObject.getString("lyric"));
            musicContent.setInfo(jsonObject.getString("info"));
            musicContent.setMusic_id(jsonObject.getString("music_id"));
            musicContent.setCharge_edt(jsonObject.getString("charge_edt"));
            musicContent.setPraisenum(jsonObject.getString("praisenum"));
            musicContent.setRead_num(jsonObject.getString("read_num"));
            musicContent.setSharenum(jsonObject.getString("sharenum"));
            musicContent.setCommentnum(jsonObject.getString("commentnum"));
            musicContent.setMaketime(jsonObject.getString("maketime"));
            MusicContent.AuthorBean authorBean = new MusicContent.AuthorBean();
            //作者
            JSONObject jsonObject1 = jsonObject.getJSONObject("author");
            authorBean.setUser_id(jsonObject1.getString("user_id"));
            authorBean.setUser_name(jsonObject1.getString("user_name"));
            authorBean.setDesc(jsonObject1.getString("desc"));
            authorBean.setWeb_url(jsonObject1.getString("web_url"));
            musicContent.setAuthor(authorBean);
            //story_author
            MusicContent.StoryAuthorBean storyAuthorBean = new MusicContent.StoryAuthorBean() ;
            JSONObject jsonObject2 = jsonObject.getJSONObject("story_author");
            storyAuthorBean.setUser_id(jsonObject2.getString("user_id"));
            storyAuthorBean.setUser_name(jsonObject2.getString("user_name"));
            musicContent.setStory_author(storyAuthorBean);
            return musicContent;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //音乐页----评论区
    public static List<MusicComment> getComments(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONObject("data").getJSONArray("data");
            TypeToken<List<MusicComment>> tt = new TypeToken<List<MusicComment>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //电影页----显示列表
    public static List<MovieList> getMovieList(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            TypeToken<List<MovieList>> tt = new TypeToken<List<MovieList>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //电影介绍---
    public static List<MovieStory.DataBean> getMovieStory(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONObject("data").getJSONArray("data");
            TypeToken<List<MovieStory.DataBean>> tt = new TypeToken<List<MovieStory.DataBean>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //电影表
    public static MovieTab getMovieTab(String json){
        MovieTab movieTab = new MovieTab();
        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject("data");
            movieTab.setId(jsonObject.getString("id"));
            movieTab.setTitle(jsonObject.getString("title"));
            movieTab.setDetailcover(jsonObject.getString("detailcover"));
            movieTab.setVideo(jsonObject.getString("video"));
            movieTab.setKeywords(jsonObject.getString("keywords"));
            movieTab.setMovie_id(jsonObject.getString("movie_id"));
            movieTab.setInfo(jsonObject.getString("info"));
            List<String> photos = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("photo");
            for (int i = 0;i<jsonArray.length();i++){
                photos.add(jsonArray.getString(i));
            }
            movieTab.setPhoto(photos);
            return movieTab;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //电影评论区
    public static List<MovieComment> getMovieComments(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONObject("data").getJSONArray("data");
            TypeToken<List<MovieComment>> tt = new TypeToken<List<MovieComment>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //获得城市列表
    public static List<CityEntity> getCities(String json){
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("result");
            TypeToken<List<CityEntity>> tt = new TypeToken<List<CityEntity>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //某个城市的天气
    public static WeatherEntity getWeatherCity(String json){
        try {
            WeatherEntity weatherEntity = new WeatherEntity();
            WeatherEntity.ResultBean result = null;

            JSONObject jsonObject = new JSONObject(json).getJSONObject("result");
            result = new Gson().fromJson(jsonObject.toString(),WeatherEntity.ResultBean.class);
            weatherEntity.setResult(result);
            return weatherEntity;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

