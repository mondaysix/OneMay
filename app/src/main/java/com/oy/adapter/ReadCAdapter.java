package com.oy.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

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
        essayContents = readContent.getEssay();
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        ReadCFragment readCFragment = new ReadCFragment();
        Bundle bundle = new Bundle();
        Log.d("msg1", "essay"+readContent.getEssay().size()+"--serial--"+readContent.getSerial().size()+"--question--"+readContent.getQuestion().size());
        bundle.putSerializable("essay", (Serializable) readContent.getEssay().get(position));
        bundle.putSerializable("serical", (Serializable) readContent.getSerial().get(position));
        bundle.putSerializable("question",readContent.getQuestion().get(position));
        readCFragment.setArguments(bundle);
        return readCFragment;
    }

    @Override
    public int getCount() {
        return essayContents.size();
    }
}
