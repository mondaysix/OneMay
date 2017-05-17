package com.oy.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.http.POST;

/**
 * Created by Meyki on 2017/4/18.
 */

public class RegisterActivity extends BaseActivity {
    @Bind(R.id.et_registmail)
    public EditText et_registmail;
    @Bind(R.id.et_registcode)
    public EditText et_registcode;
    @Bind(R.id.et_registalias)
    public EditText et_registalias;
    @Bind(R.id.et_registpwd)
    public EditText et_registpwd;
    public String code;
    @Override
    protected int setContentId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onItemListener() {

    }


    @OnClick({R.id.iv_back,R.id.btn_register,R.id.btn_code})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_back:
                startActivity(new Intent(this,MailLoginActivity.class));
                RegisterActivity.this.finish();
                break;
            case R.id.btn_register:
                //确定注册
                String emails =  et_registmail.getText().toString();
                String regcode = et_registcode.getText().toString();
                String alias = et_registalias.getText().toString();
                String pwd = et_registpwd.getText().toString();
                String picname = "default.jpg";
                if(isEmail(emails)){
                        Toast.makeText(this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                }
                if(!regcode.equals(code)){
                    Toast.makeText(this,"输入的验证码有误，请到邮箱中确认",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(alias)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(this,"用户名或者密码不为空",Toast.LENGTH_SHORT).show();
                }
                //更新至服务器
                JSONObject job = new JSONObject();
                try {
                    job.put("userEmail",emails);
                    job.put("code",regcode);
                    job.put("password",pwd);
                    job.put("alias",alias);
                    job.put("picname",picname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new RetrofitUtil().init((Constants.DOM_URL)).setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {
                        try {
                            JSONObject js = new JSONObject(json);
                            if(js.getString("status").equals("200")){
                                //注册成功跳转到登录界面
                                startActivity(new Intent(RegisterActivity.this,MailLoginActivity.class));
                                RegisterActivity.this.finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"注册失败,重新填写信息",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).downData(Constants.REGISTER,job,2);

                break;
            case R.id.btn_code:
                //发送验证码

                String email = et_registmail.getText().toString();

                if(isEmail(email)){
                    String code_url = Constants.USER_CODE.concat(email);

                    new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                        @Override
                        public void getJson(String json) {
                            Log.d("msg", "register code--"+json);
                            code = JSONUtil.getRegisterCode(json);


                        }
                    }).downData(code_url,null,1);

                }
                else {
                    Toast.makeText(this,"邮箱格式不正确或者不为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


}
