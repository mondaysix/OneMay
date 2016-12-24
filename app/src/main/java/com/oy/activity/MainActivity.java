package com.oy.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
    //当前fragment
    public Fragment mContent = new Fragment();
    //四个Fragment
    public HomeFragment homeFragment = new HomeFragment();
    public ReadFragment readFragment = new ReadFragment();
    public MusicFragment musicFragment = new MusicFragment();
    public MovieFragment movieFragment = new MovieFragment();
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
                switchContent(homeFragment);
                break;
            case R.id.rb_reading:
                //阅读
                tv_main_title.setText("阅读");
                switchContent(readFragment);
                break;
            case R.id.rb_music:
                //音乐
                tv_main_title.setText("音乐");
                switchContent(musicFragment);
                break;
            case R.id.rb_movie:
                //电影
                tv_main_title.setText("电影");
                switchContent(movieFragment);
                break;
            case R.id.iv_individual:
                //个人中心
                Intent intent = new Intent(MainActivity.this,IndividualActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     *
     * @param to 下一个fragment
     */
    private void switchContent(Fragment to){
        if (mContent != to){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()){
                Log.d("msg", "is not add");
                //隐藏当前fragment，显示下一个fragment
                transaction.hide(mContent).add(R.id.fragment_main,to).commit();
            }else {
                Log.d("msg", "is add ");
                //隐藏当前fragment,显示下一个fragment
                transaction.hide(mContent).show(to).commit();
            }
            mContent = to;
        }
    }
}
