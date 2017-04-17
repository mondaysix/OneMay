package com.oy.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oy.entity.ReadContent;
import com.oy.fragment.ReadCFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/31.
 */
public class ReadCAdapter extends FragmentPagerAdapter {
    public ReadContent readContent;
    public List<ReadContent.EssayContent> essayContents;
    public ReadCAdapter(FragmentManager fm) {
        super(fm);
        readContent = new ReadContent();
        essayContents = new ArrayList<>();
    }
    public void setReadContent(ReadContent readContent){
        this.readContent = readContent;
        essayContents = readContent.getEssayContents();
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        ReadCFragment readCFragment = new ReadCFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("essay", (Serializable) readContent.getEssayContents().get(position));
        bundle.putSerializable("serical", (Serializable) readContent.getSerials().get(position));
        bundle.putSerializable("question",readContent.getQuestions().get(position));
        readCFragment.setArguments(bundle);
        return readCFragment;
    }

    @Override
    public int getCount() {
        return essayContents.size();
    }
}
