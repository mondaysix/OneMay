package com.oy.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.oy.activity.R;
import com.oy.adapter.MusicVPAdapter;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Lucky on 2016/10/30.
 */
public class MusicFragment extends BaseFragment {
    @Bind(R.id.vp_music)
    public ViewPager vp_music;
    public MusicVPAdapter musicAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void init(View view) {
        musicAdapter = new MusicVPAdapter(getChildFragmentManager());
        vp_music.setAdapter(musicAdapter);
    }

    @Override
    public void loadDatas() {
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                    List<Integer> integers = JSONUtil.getMusicIds(json);
                    musicAdapter.setIntegers(integers);
                }
            }
        }).downData(Constants.MUSIC_URL,null,1);
    }
}
