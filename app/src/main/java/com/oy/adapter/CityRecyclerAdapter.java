package com.oy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oy.activity.R;
import com.oy.activity.WeatherActivity;
import com.oy.custom.MyCustomRecyclerViewHolder;
import com.oy.entity.CityEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/11/2.
 */
public class CityRecyclerAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> implements View.OnClickListener {
    public Context context;
    public List<CityEntity> cityEntities;
    public CityRecyclerAdapter(Context context){
        this.context = context;
        cityEntities = new ArrayList<>();
    }
    public void setCityEntities(List<CityEntity> cityEntities){
        this.cityEntities  = cityEntities;
        this.notifyDataSetChanged();
    }
    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_item,parent,false);

        return new MyCustomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder holder, int position) {
            TextView tv_city_name = (TextView) holder.getView(R.id.tv_city_name);
            tv_city_name.setText(cityEntities.get(position).getDistrict());
            tv_city_name.setTag(R.id.cityname,cityEntities.get(position).getDistrict());
            tv_city_name.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return cityEntities.size();
    }

    @Override
    public void onClick(View v) {
       String cityname = (String) v.getTag(R.id.cityname);
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra("cityname",cityname);
        context.startActivity(intent);
    }

}
