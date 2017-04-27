package com.oy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Meyki on 2017/4/19.
 */

public class CommonDB {
    public static final String DB_NAME = "oneDB";
    public static final int DB_VERSION = 1;
    //创建用户表
    public static final String create_table_user = "create table userinfo" +
            "(alias varchar(255),"+
            "password varchar(255),"+
            "avatar varchar(255)"+
            ")";
    //创建城市表
    public static final String create_table_city = "create table city"+
            "(_id primary key," +
            "cityId," +
            "cityProvince," +
            "cityName," +
            "cityDistrict" +
            ")";
    private final Context context;
    private MySqliteOpenHelper mySqliteOpenHelper;
    private SQLiteDatabase db;
    public CommonDB(Context context){
        this.context = context;
        this.mySqliteOpenHelper = new MySqliteOpenHelper(this.context);
    }
    public CommonDB open(){
        this.db = this.mySqliteOpenHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        this.mySqliteOpenHelper.close();
    }


}
