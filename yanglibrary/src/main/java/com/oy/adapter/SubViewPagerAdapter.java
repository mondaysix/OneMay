package com.oy.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public abstract class SubViewPagerAdapter<T> extends CustomViewPagerAdapter<ViewPager>{
    public List<T> datas;
    public Context context;
    public SubViewPagerAdapter(ViewPager viewPager,Context context) {
        super(viewPager);
        this.context = context;
    }
    public void setDatas(List<T> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }
    @Override
    public int getRealDataCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public Object instantiateRealItem(ViewGroup container, int position) {
//
        View view = bindView(context);
        bindData(view,datas.get(position));
        container.addView(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return view;
    }
    public abstract View bindView(Context context);
    public abstract void bindData(View view,T data);
}
