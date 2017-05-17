package com.oy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.oy.util.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import static com.oy.activity.ApplicationContext.tencent;


/**
 * Created by Meyki on 2017/4/21.
 */

public class ShareActivity extends BaseActivity {
    //分享的内容
    String content;
    //分享标题
    String title;
    //分享的图片
    String cover;
    @Bind(R.id.iv_share_qq)
    public ImageView iv_share_qq;
    @Bind(R.id.iv_share_wx)
    public ImageView iv_share_wx;
    public BaseUiListener baseUiListener;
    //public Tencent tencenta;
    @Override
    protected int setContentId() {
        return R.layout.activity_share;
    }

    @Override
    protected void init() {
        //tencenta = Tencent.createInstance(Constants.APPID,this.getApplicationContext());
        Intent intent = getIntent();
       title =  intent.getStringExtra("title");
        cover = intent.getStringExtra("img");
        content = intent.getStringExtra("content");
        //Log.d("msg", "init: "+content);
    }
    @OnClick({R.id.iv_share_qq})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_share_qq:
                Log.d("msg", "onClickListener: ");
                //分享至qq好友
                Bundle bundle = new Bundle();
                //分享类型为图片加文字
                bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                bundle.putString(QQShare.SHARE_TO_QQ_TITLE,title);//必选，最长30字符
                bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY,content.substring(0,20));//必选，最长40字符
                bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://www.qq.com");
                bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,cover);
                //bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME,"gone");
                tencent.shareToQQ(this, bundle , new BaseUiListener());


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object o) {

            Log.d("msg", "onComplete: ");
        }

        @Override
        public void onError(UiError uiError) {
            Log.d("msg", "onError: ");
        }

        @Override
        public void onCancel() {
            Log.d("msg", "onCancel: ");
        }
    }
}
