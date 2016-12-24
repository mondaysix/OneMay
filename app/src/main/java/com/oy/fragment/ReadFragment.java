package com.oy.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itsronald.widget.ViewPagerIndicator;
import com.oy.activity.R;
import com.oy.activity.ReadHActivity;
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
    @Bind(R.id.vp_convenient)
    public ConvenientBanner vp_convenient;
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
                            //无限轮播viewpager
                            addHeaderViewPager(readHeads);
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
    private void addHeaderViewPager(final List<ReadHead> readHeads){
        //设置轮播图片
        vp_convenient.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },readHeads)
                //设置指示器
                .setPageIndicator(new int[]{R.drawable.banner_default_index,R.drawable.banner_checked_index})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).startTurning(2000);
        //设置点击事件
        vp_convenient.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情页面
                Intent intent = new Intent(getActivity(), ReadHActivity.class);
                intent.putExtra("ReadHead", readHeads.get(position));
                startActivity(intent);
            }
        });

    }
    //自动轮播需要实现的类
    public class LocalImageHolderView implements Holder<ReadHead>{
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, ReadHead data) {
            Glide.with(context)
                    .load(data.getCover())
                    .placeholder(R.drawable.default_hp_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }
}
