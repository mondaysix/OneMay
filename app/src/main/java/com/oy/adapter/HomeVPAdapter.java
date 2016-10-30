package com.oy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.oy.fragment.HFirstFragment;
import com.oy.fragment.HomeVpItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/30.
 */
public class HomeVPAdapter extends FragmentPagerAdapter{
    public List<Integer> datas;
    public HomeVPAdapter(FragmentManager fm) {
        super(fm);
        datas = new ArrayList<>();
    }
    public void setIds(List<Integer> datas){
        this.datas = datas;
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