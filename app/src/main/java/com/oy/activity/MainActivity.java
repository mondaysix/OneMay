package com.oy.activity;


import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.oy.fragment.HomeFragment;
import com.oy.fragment.MovieFragment;
import com.oy.fragment.MusicFragment;
import com.oy.fragment.ReadFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{
    @Bind(R.id.rg_home)
    public RadioGroup rg_home;
    @Bind(R.id.iv_individual)
    public ImageView iv_individual;
    @Bind(R.id.tv_main_title)
    public TextView tv_main_title;
    @Override
    protected int setContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        rg_home.getChildAt(0).performClick();
    }
    @OnClick({R.id.rb_home,R.id.rb_reading,R.id.rb_music,R.id.rb_movie,R.id.iv_individual})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.rb_home:
                //首页
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main,new HomeFragment())
                        .commit();
                break;
            case R.id.rb_reading:
                //阅读
                tv_main_title.setText("阅读");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main,new ReadFragment())
                        .commit();
                break;
            case R.id.rb_music:
                //音乐
                tv_main_title.setText("音乐");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main,new MusicFragment())
                        .commit();
                break;
            case R.id.rb_movie:
                //电影
                tv_main_title.setText("电影");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main,new MovieFragment())
                        .commit();
                break;
            case R.id.iv_individual:
                //个人中心
                Intent intent = new Intent(MainActivity.this,IndividualActivity.class);
                startActivity(intent);
                break;
        }
    }
}
