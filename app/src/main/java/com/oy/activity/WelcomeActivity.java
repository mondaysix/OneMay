package com.oy.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Lucky on 2016/10/30.
 */
public class WelcomeActivity extends BaseActivity{
    public Handler handler;
//    //SD卡下目录
//    private String DATABASE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/mydatabase";
//    //数据库名字
//    private String DATABASE_FILENAME="onedatabase.db";
    @Override
    protected int setContentId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        handler = new Handler();
//        OpenDataBase();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
//    public void OpenDataBase(){
//        String databaseFileName = DATABASE_PATH+"/"+DATABASE_FILENAME;
//        File file = new File(DATABASE_PATH);
//        //如果存放数据库的目录不存在，则新建目录
//        if (!file.exists()){
//            file.mkdir();
//        }
//        File file1 = new File(databaseFileName);
//        if (!file1.exists()){
//            InputStream inputStream = getResources().openRawResource(R.raw.onedatabase);
//            try {
//                FileOutputStream out = new FileOutputStream(databaseFileName);
//                byte[] buffer = new byte[1024*8];
//                int len = 0;
//                while ((len = inputStream.read(buffer))>0){
//                    out.write(buffer,0,len);
//                }
//                out.flush();
//                out.close();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//
//    }
}