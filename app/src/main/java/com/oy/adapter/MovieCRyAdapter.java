package com.oy.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oy.activity.R;
import com.oy.custom.MyCustomRecyclerViewHolder;
import com.oy.entity.MovieComment;
import com.oy.widget.circleImageView.CircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/11/1.
 */
public class MovieCRyAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> {
   public Context context;
    public List<MovieComment> movieComments;
    public MovieCRyAdapter(Context context){
        this.context = context;
        movieComments = new ArrayList<>();
    }
    public void setMovieComments(List<MovieComment> movieComments){
        this.movieComments = movieComments;
        this.notifyDataSetChanged();
    }
    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_com_ry_item,null);
        return new MyCustomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder holder, int position) {
        //头像
        ImageView iv = (ImageView) holder.getView(R.id.iv_mv_com_head);
        Glide.with(context)
                .load(movieComments.get(position).getUser().getWeb_url())
                .transform(new CircleTransform(context))
                .placeholder(R.drawable.head)
                .into(iv);
        //用户名
        TextView tv_mv_com_username = (TextView) holder.getView(R.id.tv_mv_com_username);
        tv_mv_com_username.setText(movieComments.get(position).getUser().getUser_name());
        //更新时间
        TextView tv_mv_com__time = (TextView) holder.getView(R.id.tv_mv_com__time);
        tv_mv_com__time.setText(movieComments.get(position).getCreated_at());
        //分数
        TextView tv_mv_user_score = (TextView) holder.getView(R.id.tv_mv_user_score);
        tv_mv_user_score.setText(movieComments.get(position).getScore());
        Typeface fromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/textstyle.ttf");
        tv_mv_user_score.setTypeface(fromAsset);
        //点赞数
        TextView tv_mv_com_user_praisenum = (TextView) holder.getView(R.id.tv_mv_com_user_praisenum);
        tv_mv_com_user_praisenum.setText(movieComments.get(position).getPraisenum()+"");
        //内容页
       TextView tv_mv_com__user_comment = (TextView) holder.getView(R.id.tv_mv_com__user_comment);
        tv_mv_com__user_comment.setText(movieComments.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return movieComments.size();
    }
}
