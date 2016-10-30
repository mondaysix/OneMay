package com.oy.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.oy.entity.ReadHead;
import com.oy.fragment.ReadHFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHAdapter extends FragmentPagerAdapter{
    public List<ReadHead> readHeads;
    public ReadHAdapter(FragmentManager fm) {
        super(fm);
        readHeads = new ArrayList<>();
    }
    public void setReadHData(List<ReadHead> readHeads){
        this.readHeads = readHeads;
        this.notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        ReadHFragment readHFragment = new ReadHFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("head",readHeads.get(position));
        readHFragment.setArguments(bundle);
        return readHFragment;
    }

    @Override
    public int getCount() {
        return readHeads.size();
    }
}
