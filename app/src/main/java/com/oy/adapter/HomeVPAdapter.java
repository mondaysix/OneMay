package com.oy.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oy.fragment.HomeVpItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/30.
 */
public class HomeVPAdapter extends FragmentPagerAdapter{
    public List<Integer> datas;
    public int counts;
    public HomeVPAdapter(FragmentManager fm) {
        super(fm);
        datas = new ArrayList<>();
    }
    public void setIds(List<Integer> datas){
        this.datas = datas;
        counts = datas.size();
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        HomeVpItemFragment homeVpItemFragment = new HomeVpItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",datas.get(position));
        homeVpItemFragment.setArguments(bundle);
        return homeVpItemFragment;
    }
    @Override
    public int getCount() {
        return datas.size();
    }
}