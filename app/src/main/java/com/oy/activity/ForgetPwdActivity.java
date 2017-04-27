package com.oy.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Meyki on 2017/4/27.
 */

public class ForgetPwdActivity extends BaseActivity {
    @Bind(R.id.ll_email)
    public LinearLayout ll_email;
    @Bind(R.id.ll_setpwd)
    public LinearLayout ll_setpwd;


    @Override
    protected int setContentId() {
        return R.layout.activity_forget;
    }
    @OnClick({R.id.iv_back,R.id.btn_confirrm})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_back:
                startActivity(new Intent(this,MailLoginActivity.class));
                this.finish();
                break;
            case R.id.btn_confirrm:
                //验证邮箱是否存在服务器
                
                break;
        }
    }
}
