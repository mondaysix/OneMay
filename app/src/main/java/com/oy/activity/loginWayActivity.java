package com.oy.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Meyki on 2017/4/18.
 */

public class loginWayActivity extends BaseActivity {

    @Override
    protected int setContentId() {
        return R.layout.activity_login_way;
    }
    @OnClick({R.id.tv_mail,R.id.iv_back})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.tv_mail:
                //跳转到mail login登录界面
                startActivity(new Intent(this,MailLoginActivity.class));
                this.finish();
                break;
            case R.id.iv_back:
                //退出当前页面
                startActivity(new Intent(this,IndividualActivity.class));
                this.finish();
                break;
        }

    }
}
