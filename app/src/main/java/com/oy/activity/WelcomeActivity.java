package com.oy.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.util.Calendar;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

/**
 * Created by Lucky on 2016/10/30.
 */
public class WelcomeActivity extends BaseActivity{
    public Handler handler;
    @Bind(R.id.iv_wel)
    public ImageView iv_wel;
    String week;
    @Override
    protected int setContentId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {

        handler = new Handler();
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        week =  sdf.format(date);
        Log.d("msg", "init: "+week);
        switch (week){
            case "周一":
                iv_wel.setImageResource(R.drawable.opening_monday);
                changePage();
                break;
            case "周二":
                iv_wel.setImageResource(R.drawable.opening_tuesday);
                changePage();
                break;
            case "周三":
                iv_wel.setImageResource(R.drawable.opening_wednesday);
                changePage();
                break;
            case "周四":
                iv_wel.setImageResource(R.drawable.opening_thursday);
                changePage();
                break;
            case "周五":
                iv_wel.setImageResource(R.drawable.opening_friday);
                changePage();
                break;
            case "周六":
                iv_wel.setImageResource(R.drawable.opening_saturday);
                changePage();
                break;
            case "周日":
                iv_wel.setImageResource(R.drawable.opening_sunday);
                changePage();
                break;

        }


    }
    public void changePage(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

}