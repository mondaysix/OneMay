package com.oy.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.oy.fragment.MusicCFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/31.
 */
public class MusicVPAdapter extends FragmentPagerAdapter {
    List<Integer> integers;
    public MusicVPAdapter(FragmentManager fm) {
        super(fm);
        integers = new ArrayList<>();

    }
    public void setIntegers( List<Integer> integers){
        this.integers = integers;
        this.notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        MusicCFragment musicCFragment = new MusicCFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("m_id",integers.get(position));
        musicCFragment.setArguments(bundle);
        return musicCFragment;
    }

    @Override
    public int getCount() {
        return integers.size();
    }
}
