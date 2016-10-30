package com.oy.util;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oy.entity.ContentEntity;
import com.oy.entity.ReadContent;
import com.oy.entity.ReadHead;
import com.oy.entity.ReadHeadDt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
                    jsonObject.getString("hpcontent_id"),
                    jsonObject.getString("hp_title"),
                    jsonObject.getString("author_id"),
                    jsonObject.getString("hp_img_url"),
                    jsonObject.getString("hp_author"),
                    jsonObject.getString("hp_content"),
                    jsonObject.getString("hp_makettime"),
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
            readContent.setEssayContents((List<ReadContent.EssayContent>) new Gson().fromJson(ja1.toString(),tt1.getType()));

            JSONArray ja2 =jsonObject.getJSONArray("serial");
            TypeToken<List<ReadContent.Serial>> tt2 = new TypeToken<List<ReadContent.Serial>>(){};
            readContent.setSerials((List<ReadContent.Serial>) new Gson().fromJson(ja2.toString(),tt2.getType()));

            JSONArray ja3 =jsonObject.getJSONArray("question");
            TypeToken<List<ReadContent.Question>> tt3 = new TypeToken<List<ReadContent.Question>>(){};
            readContent.setQuestions((List<ReadContent.Question>) new Gson().fromJson(ja3.toString(),tt3.getType()));
            return readContent;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
