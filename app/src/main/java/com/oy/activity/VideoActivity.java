package com.oy.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oy.widget.MyPlayer;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/11/1.
 */
public class VideoActivity extends BaseActivity {
    @Bind(R.id.play_movie)
    public MyPlayer play_movie;

    @Override
    protected int setContentId() {
        return R.layout.activity_video;
    }

    @Override
    protected void init() {
        TextView tv2 = (TextView) play_movie.findViewById(R.id.tv_video_title);
        Intent intent = getIntent();
         String path = intent.getStringExtra("video");
        String title = intent.getStringExtra("title");
        tv2.setText(title);
        play_movie.play(path);
    }
    @OnClick(R.id.iv_video_back)
    public void setClickListener(View view){
        finishActivity(0x890);
        finish();
    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (play_movie!=null){
//            play_movie.onConfigurationChanged(newConfig);
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if (play_movie!=null){
            play_movie.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (play_movie!=null){
            play_movie.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (play_movie!=null){
            play_movie.onResume();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (play_movie!=null && play_movie.onKeyDown(keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
