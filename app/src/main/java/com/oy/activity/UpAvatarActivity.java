package com.oy.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.oy.entity.UserInfoDB;
import com.oy.util.Constants;
import com.oy.util.SubscribeClient;
import com.tencent.connect.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Meyki on 2017/5/2.
 */

public class UpAvatarActivity extends BaseActivity {
    @Bind(R.id.radioGroup)
    public RadioGroup radioGroup;
    @Bind(R.id.rb_album)
    public RadioButton rb_album;
    @Bind(R.id.rb_recode)
    public RadioButton rb_recode;
    @Bind(R.id.iv_avatar)
    public ImageView iv_avatar;
    private String picstr,bs,picname;
    public SubscribeClient subscribeClient;
    public String uname;
    public UserInfoDB udb;
    @Override
    protected int setContentId() {
        return R.layout.activity_avatar;
    }

    @Override
    protected void init() {
       uname =  getIntent().getStringExtra("uname");
        udb = new UserInfoDB(this);
        udb.open();
    }

    @Override
    protected void onItemListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == rb_album.getId()){
                    final Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(Constants.TYPE);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,100);
                }
                else if(checkedId == rb_recode.getId()){
                    //拍照获取图片
//                    ActivityCompat.requestPermissions(UpAvatarActivity.this,
//                            new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            1);
                    Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent1,101);


                }
            }
        });
    }
    int imgCount = 0;
    boolean isNULL = false;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUESTCODE){

            Uri uri = data.getData();
            String path = uri.getPath();
            ///storage/emulated/0/DCIM/Camera/20170426002511.jpg
            Log.d("msg", "onActivityResult: "+path);
            String[] pathname = path.split("/Camera/");

            Log.d("msg", "onActivityResult: "+pathname[1]);

            try {
                final Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                iv_avatar.setImageBitmap(bitmap);
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bytestream);
                bs = Base64.encodeToString(bytestream.toByteArray(), Base64.DEFAULT);
                picname=pathname[1];
                picstr = "{" +
                        "\"picname\":"+"\""+picname+"\""+","+
                        "\"avatar\":"+"\""+uname+"\""+
                        "}";
                Log.d("msg", picstr);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == Constants.REQUESTCODE2){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            iv_avatar.setImageBitmap(bitmap);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,bytestream);
            bs = Base64.encodeToString(bytestream.toByteArray(), Base64.DEFAULT);
            picname="camera"+imgCount;
            Log.d("msg2", "onActivityResult: "+uname);
            if(uname.equals("")){
                Log.d("msg2", "null ");
               isNULL = true;
            }
            else {
                isNULL = false;
            }
            picstr = "{" +
                    "\"picname\":"+"\""+picname+"\""+","+
                    "\"avatar\":"+"\""+uname+"\""+
                    "}";
           imgCount++;

        }

    }
    @OnClick({R.id.btn_up})
    public void onClickListener(View view){
        if(view.getId() == R.id.btn_up){
            //上传头像名和数据到服务器
            //上传用户头像
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(!isNULL){
                        subscribeClient = new SubscribeClient("0");
                        subscribeClient.upAvatar(picstr,bs);
                    }
                }
            }).start();
            if(isNULL){

                Toast.makeText(UpAvatarActivity.this,"用户别名不为空",Toast.LENGTH_SHORT).show();

            }
            Toast.makeText(UpAvatarActivity.this,"需要重启app，重新登录才可生效",Toast.LENGTH_SHORT).show();
            //System.exit(0);//正常退出App

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(udb!=null){
            udb.close();
        }
    }
}
