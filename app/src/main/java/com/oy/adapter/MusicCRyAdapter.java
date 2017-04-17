package com.oy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oy.activity.R;
import com.oy.custom.MyCustomRecyclerViewHolder;
import com.oy.entity.MusicComment;
import com.oy.widget.circleImageView.CircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/31.
 */
public class MusicCRyAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> {
    public Context context;
    public List<MusicComment> commentList;
    public MusicCRyAdapter(Context context){
        this.context = context;
        commentList = new ArrayList<>();
    }
    public void setCommentList(List<MusicComment> commentList){
        this.commentList = commentList;
        this.notifyDataSetChanged();
    }
    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_com_ry_item,null);
        return new MyCustomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder holder, int position) {
        //用户头像
        ImageView iv_ms_head = (ImageView) holder.getView(R.id.iv_ms_head);
        Glide.with(context)
                .load(commentList.get(position).getUser().getWeb_url())
                .placeholder(R.drawable.head)
                .transform(new CircleTransform(context))
                .into(iv_ms_head);
        //用户名
        TextView tv_ms_username = (TextView) holder.getView(R.id.tv_ms_username);
        tv_ms_username.setText(commentList.get(position).getUser().getUser_name());
        //更新时间
        TextView tv_ms_time = (TextView) holder.getView(R.id.tv_ms_time);
        tv_ms_time.setText(commentList.get(position).getInput_date());
        //内容
        TextView tv_ms_user_comment = (TextView) holder.getView(R.id.tv_ms_user_comment);
        tv_ms_user_comment.setText(commentList.get(position).getContent());
        //点赞数
        TextView tv_ms_user_praisenum = (TextView) holder.getView(R.id.tv_ms_user_praisenum);
        tv_ms_user_praisenum.setText(commentList.get(position).getPraisenum()+"");
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
