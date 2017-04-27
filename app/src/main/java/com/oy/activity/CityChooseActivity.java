package com.oy.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.oy.adapter.CityRecyclerAdapter;
import com.oy.database.MySqliteOpenHelper;
import com.oy.entity.CityEntity;
import com.oy.entity.CityInfoDB;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucky on 2016/11/3.
 */
public class CityChooseActivity extends BaseActivity {
    public CityRecyclerAdapter cityAdapter;
    public RecyclerView rv_cities;
    List<CityEntity> cityFromDatabase = new ArrayList<>();
    private CityInfoDB cityInfoDB;

    @Override
    protected int setContentId() {
        return R.layout.activity_city_choose;
    }

    @Override
    protected void init() {
        //----数据库初始化
        cityInfoDB = new CityInfoDB(this);
        cityInfoDB.open();
        rv_cities = (RecyclerView) findViewById(R.id.rv_cities);
        rv_cities.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityRecyclerAdapter(this);
        rv_cities.setAdapter(cityAdapter);

    }

    @Override
    protected void loadData() {
        cityFromDatabase = getCityFromDatabase();
        if (cityFromDatabase.size()==0){
            new RetrofitUtil().init(Constants.BASE_Weather_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                @Override
                public void getJson(String json) {
                    Log.d("msg", "getJson----wheather---: "+json);
                    List<CityEntity> cityEntities = JSONUtil.getCities(json);
                    if (cityEntities.size()!=0) {


                        //数据库存储
                        for (int i = 0; i < cityEntities.size(); i++) {
                            ContentValues contentValue = new ContentValues();
                            contentValue.put("cityId", cityEntities.get(i).getId());
                            contentValue.put("cityName", cityEntities.get(i).getCity());
                            contentValue.put("cityProvince", cityEntities.get(i).getProvince());
                            contentValue.put("cityDistrict", cityEntities.get(i).getDistrict());
                            cityInfoDB.addCity("city", null, contentValue);
                        }
                        cityAdapter.setCityEntities(cityEntities);
                    }
                }
            }).downData(Constants.CITYS,null,1);
        }
        else {
            cityAdapter.setCityEntities(cityFromDatabase);
        }

    }
    //从数据库中读取城市列表
    public List<CityEntity> getCityFromDatabase(){
        return  cityInfoDB.getCities();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cityInfoDB!=null){
            cityInfoDB.close();
        }
    }
}
