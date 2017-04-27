package com.oy.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.oy.util.Constants;
import com.oy.util.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Meyki on 2017/4/18.
 */

public class MailLoginActivity extends BaseActivity {
    @Bind(R.id.et_mail)
    public EditText et_mail;
    @Bind(R.id.et_pwd)
    public EditText et_pwd;
    @Override
    protected int setContentId() {
        return R.layout.activity_mail_login;
    }

    @Override
    protected void onItemListener() {

    }

    @OnClick({R.id.iv_back,R.id.btn_login,R.id.tv_register,R.id.tv_forgetpwd})
    public void onClickListener(View view){


        switch (view.getId()){
            case R.id.iv_back:
                startActivity(new Intent(this,loginWayActivity.class));
                this.finish();
                break;
            case R.id.btn_login:
                //登录
                final String mailStr = et_mail.getText().toString();
                final String pwdStr = et_pwd.getText().toString();
                final JSONObject jsonObject = new JSONObject();

                if( TextUtils.isEmpty(mailStr)|| TextUtils.isEmpty(pwdStr)){
                    Toast.makeText(getApplicationContext(),"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();

                }else {
                    try {
                        jsonObject.put("alias",mailStr);
                        jsonObject.put("password",pwdStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("msg1", "onClickListener: "+jsonObject.toString());

                    new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                        @Override
                        public void getJson(String json) {
                            Log.d("msg1", "login---user---"+json);
                            try {
                                JSONObject jsonObject1 = new JSONObject(json);
                                Log.d("msg1", "getJson: "+jsonObject1.toString());
                                String status = jsonObject1.getString("status");
                                String msg = jsonObject1.getString("msg");
                                String avatar = jsonObject1.getString("avatar");
                                Log.d("msg1", "getJson: "+status+"msg"+msg+"---");
                                if(status.equals("200")){
                                    //跳转到个人中心页面
                                    Log.d("msg", "login page ---->: "+avatar);
                                    Intent intent = new Intent(MailLoginActivity.this, IndividualActivity.class);

                                    intent.putExtra("avatar",avatar);
                                    intent.putExtra("username",mailStr);
                                    intent.putExtra("password",pwdStr);
                                    startActivity(intent);
                                    MailLoginActivity.this.finish();

                                }else {
                                    Log.d("msg1", "else");
                                    Toast.makeText(MailLoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).downData(null,jsonObject,3);
                }
                break;
            case R.id.tv_register:
                //注册
                startActivity(new Intent(this,RegisterActivity.class));
                this.finish();
                break;
            case R.id.tv_forgetpwd:
                //忘记密码
                startActivity(new Intent(this,ForgetPwdActivity.class));
                this.finish();
                break;
        }
    }

}
