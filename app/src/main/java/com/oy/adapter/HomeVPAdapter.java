package com.oy.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.oy.fragment.HFirstFragment;
import com.oy.fragment.HomeVpItemFragment;
import com.oy.fragment.LastFragment;

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
        counts = datas.size() +2;
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        HomeVpItemFragment homeVpItemFragment = new HomeVpItemFragment();
        Bundle bundle = new Bundle();
        if (datas.get(position)==0){
            return new HFirstFragment();
        }
        else if (datas.get(position)==1){
            return new LastFragment();
        }
        bundle.putInt("id",datas.get(position));
        homeVpItemFragment.setArguments(bundle);
        return homeVpItemFragment;
    }
    @Override
    public int getCount() {
        return datas.size();
    }
}