package com.oy.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.oy.database.CommonDB;
import com.oy.database.MySqliteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Meyki on 2017/4/19.
 */

public class UserInfoDB {
    public static final String username="alias";
    public static final String password = "password";
    public static final String avatar = "avatar";
    public DatabaseHelper databaseHelper;
    public SQLiteDatabase sb;
    public static final String sqlite_table = "userinfo";
    public static final int db_version = 1;
    private final Context context;
    public UserInfoDB(Context context){
        this.context = context;
    }
    public UserInfoDB open(){
        databaseHelper = new DatabaseHelper(this.context);
        sb =  databaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        if (databaseHelper!=null){
            databaseHelper.close();
        }
    }
    public static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
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
    public long createUser(String usernameStr,String passwordStr,String avatarStr){
        long result = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(username,usernameStr);
        contentValues.put(password,passwordStr);
        contentValues.put(avatar,avatarStr);
        result = sb.insert(sqlite_table,null,contentValues);
        return result;
    }
    //查询本地是否有该数据
    public UserEntity queryEntity(String user){
        UserEntity userEntity = new UserEntity();
        Cursor cursor = null;
        cursor = sb.query(sqlite_table,new String[]{username,password,avatar},"alias = ?",new String[]{user},null,null,null);
        if (cursor.moveToNext()){
            userEntity.setUsername(cursor.getString(cursor.getColumnIndex("alias")));
            userEntity.setPwd(cursor.getString(cursor.getColumnIndex("password")));
            userEntity.setImg(cursor.getString(cursor.getColumnIndex("avatar")));

        }
        if (cursor!=null){
            cursor.close();
        }
        return userEntity;

    }

}
