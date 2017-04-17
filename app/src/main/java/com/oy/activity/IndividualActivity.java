package com.oy.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/10/30.
 */
public class IndividualActivity extends BaseActivity {
    @Override
    protected int setContentId() {
        return R.layout.activity_individual;
    }
    @OnClick({R.id.iv_individual_back,R.id.ll_weather_setting,R.id.other_setting})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_individual_back:
                finishActivity(0x123);
                finish();
                break;
            case R.id.other_setting:
                //其他设置页面
                Intent intent1 = new Intent(this,SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_weather_setting:
                Intent intent = new Intent(this,CityChooseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
