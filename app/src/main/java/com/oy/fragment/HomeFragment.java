package com.oy.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.oy.activity.R;
import com.oy.adapter.HomeVPAdapter;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Lucky on 2016/10/30.
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    @Bind(R.id.vp_home)
    public ViewPager vp_home;
    public HomeVPAdapter homeVpAdapter;
    boolean isRefresh = false;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(View view) {
        homeVpAdapter = new HomeVPAdapter(getChildFragmentManager());
        vp_home.setAdapter(homeVpAdapter);
        vp_home.addOnPageChangeListener(this);
        vp_home.setOnTouchListener(this);
    }

    @Override
    public void loadDatas() {
        //下载首页viewpager个数
        new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                Log.d("msg1", "home count--- "+json);
                if (json!=null){
                    //解析id个数
                    List<Integer> idList = JSONUtil.getHomePageNum(json);
                    Log.d("msg", "getJson: "+idList);

                    homeVpAdapter.setIds(idList);
                    vp_home.setCurrentItem(0);
                }
            }
        }).downData(Constants.HOME_URL,null,1);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //开始滑动---1 手指离开的时候是2，停留在当前页的时候是0
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
