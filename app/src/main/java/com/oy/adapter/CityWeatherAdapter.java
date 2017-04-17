package com.oy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oy.activity.R;
import com.oy.custom.MyCustomRecyclerViewHolder;
import com.oy.entity.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/11/3.
 */
public class CityWeatherAdapter extends RecyclerView.Adapter<MyCustomRecyclerViewHolder> {
    public Context context;
    public List<WeatherEntity.ResultBean.FutureBean> futureBeen;
    public CityWeatherAdapter(Context context){
        this.context = context;
        futureBeen = new ArrayList<>();
    }
    public void setFutureBeen(List<WeatherEntity.ResultBean.FutureBean> futureBeen){
        this.futureBeen = futureBeen;
        this.notifyDataSetChanged();
    }
    @Override
    public MyCustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_item,parent,false);

        return new MyCustomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomRecyclerViewHolder holder, int position) {
        TextView tv_w_week = (TextView) holder.getView(R.id.tv_w_week);
        tv_w_week.setText(futureBeen.get(position).getDate());
        TextView tv_w_weather = (TextView) holder.getView(R.id.tv_w_weather);
        tv_w_weather.setText(futureBeen.get(position).getWeather()+"");
        TextView tv_w_wind = (TextView) holder.getView(R.id.tv_w_wind);
        tv_w_wind.setText(futureBeen.get(position).getWind()+"");
        TextView tv_w_temper = (TextView) holder.getView(R.id.tv_w_temper);
        tv_w_temper.setText(futureBeen.get(position).getTemperature()+"");
    }

    @Override
    public int getItemCount() {
        return futureBeen.size();
    }
}
