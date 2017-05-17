package com.oy.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.oy.entity.PushEntity;
import com.oy.entity.UserInfoDB;
import com.oy.util.Constants;
import com.oy.util.DownUtil;
import com.oy.util.HttpUtil;
import com.oy.util.RetrofitUtil;
import com.oy.widget.BlueDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/11/4.
 */
public class SettingActivity extends BaseActivity {
    public BluetoothAdapter bluetoothAdapter;


    public UserInfoDB userInfoDB;
    public String avatar;
    public boolean isCheck = false;
    @Bind(R.id.iv_nofity)
    public ImageView iv_notify;
    Bitmap bitmap;
    @Override
    protected int setContentId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        userInfoDB = new UserInfoDB(this);
        userInfoDB.open();
         avatar = userInfoDB.queryUser();
    }

    @OnClick({R.id.tv_bluth,R.id.tv_logout,R.id.rl_notify,R.id.tv_update,R.id.tv_changeuser})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_bluth:
                BlueDialog blueDialog = new BlueDialog(this, new BlueDialog.OnClickListener() {
                    @Override
                    public void onComfirm(View view) {
                      if (!bluetoothAdapter.enable()){
                          Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                          startActivityForResult(intent,0);
                          Toast.makeText(getApplicationContext(),"开启成功",Toast.LENGTH_SHORT).show();
                      }
                        else {
                          Toast.makeText(getApplicationContext(),"已经打开蓝牙，无需打开了",Toast.LENGTH_SHORT).show();
                      }
                    }
                },bluetoothAdapter);
                blueDialog.show();
                break;
            case R.id.tv_logout:

                //注销当前账号,仅限于邮箱登录
                Log.d("msg4", "onClick: "+avatar);
                if (avatar!=null){
                    dialog();
                }
                else {
                    Toast.makeText(SettingActivity.this,"账户还未登录，请先登录",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rl_notify:
                //选择是否打开消息推送通知
                if(!isCheck){
                    //打开
                    iv_notify.setBackgroundResource(R.drawable.btn_check);
                    isCheck = true;
                    new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                        @Override
                        public void getJson(String json) {

                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String status = jsonObject.getString("status");
                                if(status.equals("200")){
                                    JSONObject js = jsonObject.getJSONObject("data");
                                    PushEntity pe = new PushEntity(js.getString("id"),js.getString("img"),
                                            js.getString("title"), js.getString("contentInfo"));
                                    //获取通知图标bitmap

                                    String url = Constants.PUSH_IMG + pe.getImg();
                                    Glide.with(SettingActivity.this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            bitmap = resource;
                                        }
                                    });
                                    Log.d("msg8", "getJson: "+url);
                                    Log.d("msg8", "getJson: "+bitmap);
                                    //1--获取状态通知栏管理
                                    NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    //2---
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(SettingActivity.this);
                                    //3---设置内容
                                    builder.setSmallIcon(R.drawable.one_icon)
                                            .setLargeIcon(bitmap)
                                            .setContentTitle(pe.getTitle())
                                            .setContentText(pe.getContentInfo())
                                            .setWhen(System.currentTimeMillis())

                                            ;
                                    int id = 1;
                                    Notification notification = builder.build();
                                    //声音提示
                                    notification.defaults = Notification.DEFAULT_SOUND;

                                    notifyManager.notify(id,notification);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }).downData(Constants.PUSH_MSG,null,1);


                }
                else {
                    //关闭
                    iv_notify.setBackgroundResource(R.drawable.btn_normal);
                    isCheck = false;

                }
                break;
            case R.id.tv_update:
                //检查更新
                Log.d("msg3", "onClick: "+getVersionName()+"---"+getversionFromServer());
                if (getversionFromServer()!=null){
                    if(getVersionName().equals(getversionFromServer())){
                        Toast.makeText(this,"当前app已经是最新的app了，不需要进行更新",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,"请前往相应的下载地址下载相应的app",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this,"正在检测。。。。请稍后",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_changeuser:
                //编辑当前基本账号信息

                break;
        }
    }
    //获取当前程序的版本号码
    public String getVersionName(){
        PackageManager pm = getPackageManager();
        try {
           PackageInfo packageInfo =  pm.getPackageInfo(getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
    public String version_id ;
    //获取服务器的版本号
    public String getversionFromServer(){
        new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                try {
                    JSONObject js = new JSONObject(json);
                    JSONObject js1 = js.getJSONObject("appinfo");
                    version_id = js1.getString("version_code");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).downData(Constants.VERSION_URL,null,1);
        return version_id;
    }
    public void dialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("确认删除当前用户")
        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userInfoDB.deleteUser(avatar);
                dialog.dismiss();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SettingActivity.this,IndividualActivity.class));
                SettingActivity.this.finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(this,IndividualActivity.class));
            SettingActivity.this.finish();
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(userInfoDB!=null){
            userInfoDB.close();
        }
    }
}
