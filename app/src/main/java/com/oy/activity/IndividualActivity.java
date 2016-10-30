package com.oy.activity;

import android.view.View;

import butterknife.OnClick;

/**
 * Created by Lucky on 2016/10/30.
 */
public class IndividualActivity extends BaseActivity {
    @Override
    protected int setContentId() {
        return R.layout.activity_individual;
    }
    @OnClick(R.id.iv_individual_back)
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_individual_back:
                finishActivity(0x123);
                finish();
                break;

        }
    }
}
