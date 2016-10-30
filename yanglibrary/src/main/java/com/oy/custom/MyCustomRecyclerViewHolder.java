package com.oy.custom;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
public class MyCustomRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public OnItemClickListener onItemClickListener;
    public Map<Integer,View> mCacheMap;
    public MyCustomRecyclerViewHolder(View itemView) {
        super(itemView);
        mCacheMap = new HashMap<>();
        //监听事件
        itemView.setTag(getAdapterPosition());
        itemView.setOnClickListener(this);

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public View getView(int resId){
        View view;
        if (mCacheMap.containsKey(resId)){
            return mCacheMap.get(resId);
        }
        else {
            view = itemView.findViewById(resId);
            mCacheMap.put(resId,view);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener!=null){
            onItemClickListener.onClickListener(v,getAdapterPosition());
        }
    }
    public  interface OnItemClickListener{
        void onClickListener(View view, int position);
    }
}
