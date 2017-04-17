package com.oy.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.itsronald.widget.ViewPagerIndicator;
import com.oy.activity.R;
import com.oy.adapter.ReadCAdapter;
import com.oy.adapter.ReadHAdapter;
import com.oy.entity.ReadContent;
import com.oy.entity.ReadHead;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadFragment extends BaseFragment {
    //头部viewpager
    @Bind(R.id.vp_read_header)
    public ViewPager vp_read_header;
    public ReadHAdapter readHAdapter;
    //内容content
    @Bind(R.id.vp_read_content)
    public ViewPager vp_read_content;
    public ReadCAdapter readCAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    public void init(View view) {
        //头部viewpager初始化
        readHAdapter = new ReadHAdapter(getChildFragmentManager());
        vp_read_header.setAdapter(readHAdapter);
        //内容页viewpager
        readCAdapter = new ReadCAdapter(getChildFragmentManager());
        vp_read_content.setAdapter(readCAdapter);
    }

    @Override
    public void loadDatas() {
        //下载头部数据
        new RetrofitUtil().init(Constants.BASE_URL)
                .setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {
                        if (json!=null){
                            List<ReadHead> readHeads = JSONUtil.getReadHs(json);
                            readHAdapter.setReadHData(readHeads);
                        }
                    }
                }).downData(Constants.READ_HEAD);
        //下载内容页
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                    ReadContent readContent = JSONUtil.getReadContent(json);
                    readCAdapter.setReadContent(readContent);
                }
            }
        }).downData(Constants.READ_CONTENT);
    }
}
