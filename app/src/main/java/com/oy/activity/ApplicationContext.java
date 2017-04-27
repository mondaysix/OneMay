package com.oy.activity;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.Environment;

import com.oy.database.CommonDB;
import com.oy.util.Constants;
import com.tencent.tauth.Tencent;

/**
 * Created by Lucky on 2016/11/10.
 */
public class ApplicationContext extends Application {
    private CommonDB commonDB;

    public static Tencent tencent;
    @Override
    public void onCreate() {
        super.onCreate();
        if (Constants.APPID!=null){
            tencent = Tencent.createInstance(Constants.APPID,this);
        }

        commonDB = new CommonDB(this);
        commonDB.open();
    }
}

