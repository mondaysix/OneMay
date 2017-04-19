package com.oy.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.oy.adapter.CityWeatherAdapter;
import com.oy.entity.WeatherEntity;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

/**
 * Created by Lucky on 2016/11/2.
 */
public class WeatherActivity extends BaseActivity {
    public String name;
    public WeatherEntity weatherEntity;
    public TextView tv_temp;
    public TextView tv_city;
    public TextView tv_weather1;
    public TextView tv_wind_strength;
    public TextView tv_humidity;
    public TextView tv_weather2;
    public TextView tv_wind;
    public TextView tv_temper;
    public TextView tv_update_time;
    public TextView tv_wind_direction;
    public RecyclerView rv_fu_weathers;

    public CityWeatherAdapter weatherAdapter;
    @Override
    protected int setContentId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void init() {
        //城市名
        name = getIntent().getStringExtra("cityname");
        rv_fu_weathers = (RecyclerView) findViewById(R.id.rv_fu_weathers);
        tv_temp = (TextView) findViewById(R.id.tv_temp);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_weather1 = (TextView) findViewById(R.id.tv_weather1);
        tv_wind_strength = (TextView) findViewById(R.id.tv_wind_strength);
        tv_humidity = (TextView) findViewById(R.id.tv_humidity);
        tv_weather2 = (TextView) findViewById(R.id.tv_weather2);
        tv_weather2 = (TextView) findViewById(R.id.tv_weather2);
        tv_weather2 = (TextView) findViewById(R.id.tv_weather2);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
        tv_temper = (TextView) findViewById(R.id.tv_temper);
        tv_wind_direction = (TextView) findViewById(R.id.tv_wind_direction);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        rv_fu_weathers.setLayoutManager(new LinearLayoutManager(getContext()));
        weatherAdapter = new CityWeatherAdapter(this);
        rv_fu_weathers.setAdapter(weatherAdapter);

    }

    @Override
    protected void loadData() {
        String newUrl = String.format(Constants.CITY_WETHER,name);
        new RetrofitUtil().init(Constants.BASE_Weather_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                    weatherEntity = JSONUtil.getWeatherCity(json);
                    WeatherEntity.ResultBean.SkBean skBean = weatherEntity.getResult().getSk();
                    WeatherEntity.ResultBean.TodayBean todayBean = weatherEntity.getResult().getToday();

                    weatherEntity.getResult().getFuture();
                    tv_temp.setText(skBean.getTemp());
                    tv_update_time.setText(skBean.getTime());
                    tv_city.setText(todayBean.getCity());
                    tv_weather1.setText(todayBean.getWeather()+"");
                    tv_wind_direction.setText(skBean.getWind_direction()+"");
                    tv_wind_strength.setText(skBean.getWind_strength()+"");
                    tv_humidity.setText(skBean.getHumidity()+"");
                    tv_weather2.setText(todayBean.getWeather()+"");
                    tv_wind.setText(todayBean.getWind()+"");
                    tv_temper.setText(todayBean.getTemperature()+"");
                    //未来一周天气
                    weatherAdapter.setFutureBeen(weatherEntity.getResult().getFuture());
                }
            }
        }).downData(newUrl,null,1);
    }
}
