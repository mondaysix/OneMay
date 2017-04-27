package com.oy.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.oy.database.CommonDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meyki on 2017/4/21.
 */

public class CityInfoDB {
    public DatabaseHelper2 databaseHelper;
    public SQLiteDatabase sb;
    public static final String sqlite_table = "userinfo";
    public static final int db_version = 1;
    private final Context context;
    private  List<CityEntity> cityEntities;
    public CityInfoDB(Context context){
        this.context = context;
    }
    public CityInfoDB open(){
        databaseHelper = new DatabaseHelper2(this.context);
        cityEntities = new ArrayList<>();
        sb =  databaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        if (databaseHelper!=null){
            databaseHelper.close();
        }
    }
    public static class DatabaseHelper2 extends SQLiteOpenHelper{

        public DatabaseHelper2(Context context) {
            super(context, CommonDB.DB_NAME, null, CommonDB.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    //insert data
    public void addCity(String table_name,String columnhack,ContentValues contentValues){
        sb.insert(table_name,columnhack,contentValues);

    }
    //读取城市列表
    public List<CityEntity> getCities(){
        Cursor cursor = sb.query("city",new String[]{"_id" ,"cityId","cityProvince","cityName","cityDistrict"},null, null, null, null, null);
        while(cursor.moveToNext()){
            String cityid = cursor.getString(cursor.getColumnIndex("cityId"));
            String cityProvince = cursor.getString(cursor.getColumnIndex("cityProvince"));
            String cityName = cursor.getString(cursor.getColumnIndex("cityName"));
            String cityDistrict = cursor.getString(cursor.getColumnIndex("cityDistrict"));
            CityEntity cityEntity = new CityEntity(cityid,cityProvince,cityName,cityDistrict);
            cityEntities.add(cityEntity);
        }
        cursor.close();
        return cityEntities;

    }
}
