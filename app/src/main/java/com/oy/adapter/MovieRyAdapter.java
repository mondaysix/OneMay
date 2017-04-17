package com.oy.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oy.activity.MovieActivity;
import com.oy.activity.R;
import com.oy.custom.MyCustomRecyclerViewHolder;
import com.oy.entity.MovieList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/11/1.
 */
public class MovieRyAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> implements View.OnClickListener {
    public Context context;
    public List<MovieList> movieLists;
    public OnItemClickListener onItemClickListener;
    public MovieRyAdapter(Context context){
        this.context = context;
        movieLists = new ArrayList<>();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setMovieLists(List<MovieList> movieLists){
        this.movieLists = movieLists;
        this.notifyDataSetChanged();
    }
    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_ry_item,null);

        return new MyCustomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder holder, int position) {
       ImageView iv_mv_cover = (ImageView) holder.getView(R.id.iv_mv_cover);
        Glide.with(context)
                .load(movieLists.get(position).getCover())
                .placeholder(R.drawable.movie_placeholder_3)
                .into(iv_mv_cover);
        iv_mv_cover.setTag(R.id.movie_tag,movieLists.get(position).getId());
        iv_mv_cover.setTag(R.id.movie_obj,movieLists.get(position));
        iv_mv_cover.setOnClickListener(this);
        TextView tv_mv_score = (TextView) holder.getView(R.id.tv_mv_score);
        tv_mv_score.setText(movieLists.get(position).getScore());
        Typeface typeface = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/textstyle.ttf");
        tv_mv_score.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener!=null){

            onItemClickListener.setOnClick(v);
        }

    }
    public interface OnItemClickListener {
        void setOnClick(View v);
    }
}
