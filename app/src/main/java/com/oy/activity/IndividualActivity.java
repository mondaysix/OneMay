package com.oy.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oy.entity.UserEntity;
import com.oy.entity.UserInfoDB;
import com.oy.util.Constants;
import com.oy.widget.circleImageView.CircleImageView;
import com.oy.widget.circleImageView.CircleTransform;
import com.tencent.tauth.Tencent;

import butterknife.Bind;
import butterknife.OnClick;

import static com.oy.entity.UserInfoDB.avatar;

/**
 * Created by Lucky on 2016/10/30.
 */
public class IndividualActivity extends BaseActivity {
    @Bind(R.id.iv_header)
    public ImageView iv_header;
    @Bind(R.id.tv_alias)
    public TextView tv_alias;
    public static UserInfoDB userInfoDB;
    public static  String uname="";
    String img_url = Constants.IMG_URL;
    @Override
    protected int setContentId() {
        return R.layout.activity_individual;
    }

    @Override
    protected void init() {
        userInfoDB = new UserInfoDB(this);
        userInfoDB.open();
        Intent intent = getIntent();

        String username = intent.getStringExtra("username");

        if (username!=null){
            uname = username;
            String avatar = intent.getStringExtra("avatar");

            String pwd = intent.getStringExtra("password");
            //保存到本地数据库
            userInfoDB.createUser(username,pwd,avatar);
            tv_alias.setText(username);
            //设置不可点击
            iv_header.setClickable(false);
            if (pwd.equals("123")){
                img_url = avatar;
            }
            else {
                img_url += avatar;
            }

        }
        else if(!uname.equals("")){
                UserEntity ue = userInfoDB.queryEntity(uname);
                Log.d("msg", "init: db---"+ue.getUsername());
                if (ue.getPwd().equals("123")){
                    img_url = ue.getImg();

                }
                else {
                    img_url += ue.getImg();
                }
                tv_alias.setText(ue.getUsername());
                iv_header.setClickable(false);


            }
        Glide.with(context).load(img_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CircleTransform(IndividualActivity.this))
                .placeholder(R.drawable.head)
                .into(iv_header);




    }

    @OnClick({R.id.iv_individual_back,R.id.ll_weather_setting,R.id.other_setting,R.id.iv_header,R.id.tv_up})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_individual_back:
                startActivity(new Intent(this,MainActivity.class));
                IndividualActivity.this.finish();
                break;
            case R.id.other_setting:
                //其他设置页面
                Intent intent1 = new Intent(this,SettingActivity.class);
                startActivity(intent1);
                IndividualActivity.this.finish();
                break;
            case R.id.ll_weather_setting:
                Intent intent = new Intent(this,CityChooseActivity.class);
                startActivity(intent);
                IndividualActivity.this.finish();
                break;
            case R.id.iv_header:
                //登录方式
                startActivityForResult(new Intent(this,loginWayActivity.class),111);
                IndividualActivity.this.finish();
                break;
            case R.id.tv_up:
                //上传用户新头像
                Intent intent2 = new Intent(this,UpAvatarActivity.class);
                intent2.putExtra("uname",uname);
                startActivity(intent2);
                this.finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 111){
            String nickname  = data.getStringExtra("nickname");
            String figure = data.getStringExtra("figure");
            tv_alias.setText(nickname);
            Glide.with(this).load(figure)
                    .placeholder(R.drawable.head)
                    .transform(new CircleTransform(IndividualActivity.this))
                    .into(iv_header);
            //存到数据库
            //保存到本地数据库


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userInfoDB!=null){
            userInfoDB.close();
        }
    }
}
