package com.oy.activity;

import android.content.Intent;
import android.os.Handler;

/**
 * Created by Lucky on 2016/10/30.
 */
public class WelcomeActivity extends BaseActivity{
    public Handler handler;
    @Override
    protected int setContentId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}