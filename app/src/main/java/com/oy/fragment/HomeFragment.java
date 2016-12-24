package com.oy.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.oy.activity.R;
import com.oy.adapter.HomeVPAdapter;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Lucky on 2016/10/30.
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @Bind(R.id.vp_home)
    public ViewPager vp_home;
    public HomeVPAdapter homeVpAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(View view) {
        homeVpAdapter = new HomeVPAdapter(getChildFragmentManager());
        vp_home.addOnPageChangeListener(this);
        vp_home.setAdapter(homeVpAdapter);
    }

    @Override
    public void loadDatas() {
        //下载首页viewpager个数
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                    //解析id个数
                    List<Integer> idList = JSONUtil.getHomePageNum(json);
                    homeVpAdapter.setIds(idList);
                }
            }
        }).downData(Constants.HOME_URL);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("msg", "当前页面偏移百分比 "+positionOffset);
        Log.d("msg", "当前页面偏移的像素位置"+positionOffsetPixels);
    }

    /**
     * 当前选中的页
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        Log.d("msg", "onPageSelected: "+position);

    }

    /**
     *滑动首先调用该方法，其次是onPageScrolled
     * @param state
     * 三种状态：0：滑动停止的时候
     *         1：正在滑动
     *         2：滑动停止
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("msg", "onPageScrollStateChanged: "+state);
    }
}
