package com.oy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {


    public MySqliteOpenHelper(Context context) {
        super(context, CommonDB.DB_NAME, null, CommonDB.DB_VERSION);//参数1：上下文 参数2：数据库名称 参数3：cursorFactory 参数4：数据库版本
    }

    //第一次得到sqlitedatabase对象调用一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CommonDB.create_table_user);
        db.execSQL(CommonDB.create_table_city);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
