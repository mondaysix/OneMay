package com.oy.activity;

import android.content.ContentResolver;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/9/26 0026.
 */
public abstract class BaseActivity<T> extends AppCompatActivity {
    T data;
    public static BaseActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setContentId());
        context = this;
//        //1---EventBus注册
//        EventBus.getDefault().register(this);

        //注册activity
        ButterKnife.bind(this);
//        onEventMainThread(data);
        init();
        loadData();
        onItemListener();
    }
    /**
     * 布局id
     * @return
     */
    protected abstract int setContentId();
    protected  void loadData(){};
    protected  void init(){};
    protected  void onItemListener(){};
    public static BaseActivity getContext(){
        return context;
    }
//    @Subscribe(threadMode = ThreadMode.MainThread, priority = 100, sticky = true)
//    public void  onEventMainThread(T data){};
//判断email格式是否正确
public boolean isEmail(String email) {
    if(!"".equals(email)){
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
    return false;

}
    //解决小米手机上获取本地图片uri为null的情况
    public Uri getUri(android.content.Intent intent){
        Uri uri = intent.getData();
        String type = intent.getType();
        if(uri.getScheme().equals("file") && type.contains("image/")){
            String path = uri.getEncodedPath();
            if(path!=null){
                path = Uri.decode(path);
                ContentResolver contentResolver = this.getContentResolver();
                StringBuffer sb = new StringBuffer();
                sb.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'"+path+"'").append(")");
                Log.d("msg", "getUri: "+sb);
                Cursor cursor  = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,new String[]{MediaStore.Images.ImageColumns._ID},sb.toString(),null,null);
                int index = 0;
                for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    index = cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cursor.getInt(index);
                }
                if(index == 0){

                }
                else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/"+index);
                    if(uri_temp!=null){
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }
    /**
     * activity的跳转
     * @param intent
     * @param srcid
     * @param desid
     */
    protected void startActivityForAnimation(Intent intent,int srcid,int desid){
        startActivity(intent);
        overridePendingTransition(srcid,desid);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
