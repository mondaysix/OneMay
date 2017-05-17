package com.oy.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oy.util.Constants;
import com.oy.util.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
    @Bind(R.id.et_fmail)
    public EditText et_fmail;
    @Bind(R.id.et_npwd)
    public EditText et_npwd;
    @Bind(R.id.et_cpwd)
    public EditText et_cpwd;
    String mail;
    @Override
    protected int setContentId() {
        return R.layout.activity_forget;
    }
    @OnClick({R.id.iv_back,R.id.btn_confirrm,R.id.btn_update})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_back:
                startActivity(new Intent(this,MailLoginActivity.class));
                this.finish();
                break;
            case R.id.btn_confirrm:
                //验证邮箱是否存在服务器
                 mail = et_fmail.getText().toString();
                if(!isEmail(mail)){
                    Toast.makeText(this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                }

                String new_url = Constants.VERIFY_MAIL+mail;
                Log.d("msg", "forget"+new_url);
                new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {

                        try {
                            JSONObject js = new JSONObject(json);
                            if(js.getString("status").equals("200")){
                                //验证成功,隐藏显示输入新密码
                                ll_email.setVisibility(View.GONE);
                                ll_setpwd.setVisibility(View.VISIBLE);


                            }
                            else {
                                Toast.makeText(ForgetPwdActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).downData(new_url,null,1);
                break;
            case R.id.btn_update:
                //更新数据
                ///user/newpwd/123?use_email=23456789@qq.com
                String pwd = et_npwd.getText().toString();
                String c_pwd = et_cpwd.getText().toString();
                if(!pwd.equals(c_pwd)){
                    Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                }
                String pwd_url = Constants.NEW_PWD + pwd+"?"+"use_use_email="+mail;
                new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {
                        Log.d("msg", "getJson: "+json);
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            if(jsonObject.getString("status").equals("200")){
                                //跳转到用户登录
                                startActivity(new Intent(ForgetPwdActivity.this,MailLoginActivity.class));
                                ForgetPwdActivity.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).downData(pwd_url,null,1);
                break;
        }
    }
}
