package com.oy.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.oy.util.Constants;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

import static com.oy.activity.ApplicationContext.tencent;

/**
 * Created by Meyki on 2017/4/18.
 */

public class loginWayActivity extends BaseActivity {
    //private Tencent tencent;//qq操作对象
    private String scope;//获取信息的范围参数
    private IUiListener loginListener;
    private IUiListener userInfoListener;
    private UserInfo userinfo;
    @Override
    protected int setContentId() {
        return R.layout.activity_login_way;
    }

    @Override
    protected void init() {
        scope = "all";
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {


                if(o == null){
                    return;
                }
                /**
                 * "ret":0,"openid":"23F8216D056C5F095AD04019AAEAAFB2",
                 * "access_token":"5FF26395A776827D29A8AAD70494D09F",
                 * "pay_token":"465F65CD9D1560067ECC149AAEAAD6FF",
                 * "expires_in":7776000,"pf":"desktop_m_qq-10000144-android-2002-",
                 * "pfkey":"ae44d77b403d7303e71f4b56fda4eb23",
                 * "msg":"","login_cost":92,"query_authority_cost":131,"authority_cost":0}
                 */
                try {
                    JSONObject jsonObject = (JSONObject) o;
                    Log.d("msg", "login qq --"+jsonObject.toString());
                    //String msg = jsonObject.getString("msg");
                    String openID = jsonObject.getString("openid");
                    String accessToken = jsonObject.getString("access_token");
                    String expires = jsonObject.getString("expires_in");
                    tencent.setOpenId(openID);
                    tencent.setAccessToken(accessToken,expires);
                    if (tencent.getQQToken() == null){
                        Log.d("msg", "qqtoken == null");
                    }
                    userinfo = new UserInfo(loginWayActivity.this,tencent.getQQToken());
                    userinfo.getUserInfo(userInfoListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject jo = (JSONObject) o;
                Log.d("msg", "onComplete: "+jo);
                try {
                    String nickname = jo.getString("nickname");
                    String figure_url = jo.getString("figureurl");
                    Intent intent = new Intent(new Intent(loginWayActivity.this,IndividualActivity.class));

                    intent.putExtra("username",nickname);
                    intent.putExtra("avatar",figure_url);
                    intent.putExtra("password","123");

                    startActivity(intent);
                    //loginWayActivity.this.setResult(111,intent);
                    loginWayActivity.this.finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };

    }

    @OnClick({R.id.tv_mail,R.id.iv_back,R.id.iv_qq})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.tv_mail:
                //跳转到mail login登录界面
                startActivity(new Intent(this,MailLoginActivity.class));
                loginWayActivity.this.finish();
                break;
            case R.id.iv_back:
                //退出当前页面
                startActivity(new Intent(this,IndividualActivity.class));
                loginWayActivity.this.finish();
                break;
            case R.id.iv_qq:
                //qq登录
                if(!tencent.isSessionValid()){
                    tencent.login(loginWayActivity.this,scope,loginListener);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("msg", "onActivityResult: "+requestCode+"----"+resultCode);


                Tencent.handleResultData(data, loginListener);

            super.onActivityResult(requestCode, resultCode, data);




    }
}
