package com.oy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oy.activity.R;
import com.oy.custom.MyCustomRecyclerViewHolder;
import com.oy.entity.ReadHeadDt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHCRyAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> {
    public Context context;
    public List<ReadHeadDt> readHeadDts;
    public ReadHCRyAdapter(Context context){
        this.context = context;
        readHeadDts = new ArrayList<>();
    }
    public void setReadHeadDts(List<ReadHeadDt> readHeadDts){
        this.readHeadDts = readHeadDts;
        this.notifyDataSetChanged();
    }
    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.read_head_ry_item,null);
        return new MyCustomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder holder, int position) {
        //编号
        TextView tv_rd_detail_num = (TextView) holder.getView(R.id.tv_rd_detail_num);
        int id = position+1;
        tv_rd_detail_num.setText(""+id);
        //标题
        TextView tv_rd_detail_title = (TextView) holder.getView(R.id.tv_rd_detail_title);
        tv_rd_detail_title.setText(readHeadDts.get(position).getTitle());
        //作者
        TextView tv_rd_detail_author = (TextView) holder.getView(R.id.tv_rd_detail_author);
        tv_rd_detail_author.setText(readHeadDts.get(position).getAuthor());
        //内容
        TextView tv_rd_detail_intro= (TextView) holder.getView(R.id.tv_rd_detail_intro);
        tv_rd_detail_intro.setText(readHeadDts.get(position).getIntroduction());
    }

    @Override
    public int getItemCount() {
        return readHeadDts.size();
    }
}
