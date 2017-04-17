package com.oy.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oy.adapter.CityRecyclerAdapter;
import com.oy.database.MySqliteOpenHelper;
import com.oy.entity.CityEntity;
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
    public MySqliteOpenHelper sqliteHelper;
    public SQLiteDatabase sqliteDatabase;
    List<CityEntity> cityEntities = new ArrayList<>();
    List<CityEntity> cityFromDatabase = new ArrayList<>();
    String str = "create table city"+"(_id primary key,cityId,cityProvince,cityName,cityDistrict)";
    private String DATABASE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+"/mydatabase/onedatabase.db";
    @Override
    protected int setContentId() {
        return R.layout.activity_city_choose;
    }

    @Override
    protected void init() {
        //----数据库初始化
//        sqliteHelper = new MySqliteOpenHelper(this,"onedatabase.db",str);
        File file = new File(DATABASE_PATH);
        sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
//      sqliteDatabase = sqliteHelper.getWritableDatabase();
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
                    List<CityEntity> cityEntities = JSONUtil.getCities(json);
                    //数据库存储
                    for (int i = 0;i<cityEntities.size();i++){
                        ContentValues contentValue = new ContentValues();
                        contentValue.put("cityId",cityEntities.get(i).getId());
                        contentValue.put("cityName",cityEntities.get(i).getCity());
                        contentValue.put("cityProvince",cityEntities.get(i).getProvince());
                        contentValue.put("cityDistrict",cityEntities.get(i).getDistrict());
                        sqliteDatabase.insert("city",null,contentValue);
                    }
                    cityAdapter.setCityEntities(cityEntities);
                }
            }).downData(Constants.CITYS);
        }
        else {
            cityAdapter.setCityEntities(cityFromDatabase);
        }

    }
    //从数据库中读取城市列表
    public List<CityEntity> getCityFromDatabase(){

        Cursor cursor = sqliteDatabase.query("city",new String[]{"_id" ,"cityId","cityProvince","cityName","cityDistrict"},null, null, null, null, null);
        while(cursor.moveToNext()){
            String cityid = cursor.getString(cursor.getColumnIndex("cityId"));
            String cityProvince = cursor.getString(cursor.getColumnIndex("cityProvince"));
            String cityName = cursor.getString(cursor.getColumnIndex("cityName"));
            String cityDistrict = cursor.getString(cursor.getColumnIndex("cityDistrict"));
            CityEntity cityEntity = new CityEntity(cityid,cityProvince,cityName,cityDistrict);
            cityEntities.add(cityEntity);
        }
        cursor.close();
        return  cityEntities;
    }
}
