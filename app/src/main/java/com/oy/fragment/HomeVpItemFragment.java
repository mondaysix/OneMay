package com.oy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oy.activity.R;
import com.oy.activity.ShareActivity;
import com.oy.entity.ContentEntity;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.LruCacheUtil;
import com.oy.util.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/10/30.
 */
public class HomeVpItemFragment extends BaseFragment {
    @Bind(R.id.iv_hp_image)
    public ImageView iv_hp_image;
    @Bind(R.id.tv_hp_title)
    public TextView tv_hp_title;
    @Bind(R.id.tv_hp_author)
    public TextView tv_hp_author;
    @Bind(R.id.tv_hp_content)
    public TextView tv_hp_content;
    @Bind(R.id.tv_hp_maketime)
    public TextView tv_hp_maketime;
    @Bind(R.id.tv_hp_praisenum)
    public TextView tv_hp_praisenum;
    @Bind(R.id.iv_share)
    public ImageView iv_share;//分享
    //点赞相关
    @Bind(R.id.iv_laud)
    public ImageView iv_laud;//点赞
    public boolean isLaud = false;
    public int praiseNum;
    public int content_id;
    //屏幕宽高
    public int widthScreen,heightScreen;
    //设置缩放的属性
    public int locationX,locationY;
    public int toX=2,toY=2;
    public int pivotX,povitY;
    public ScaleAnimation scaleAnimation;
    @Override
    public int getLayoutId() {
        return R.layout.home_vp_item;
    }

    @Override
    protected void getBundletDatas(Bundle bundle) {
        int id = bundle.getInt("id");
        final String content_url = String.format(Constants.HOME_CONTENT_URL,id);
        Log.d("msg1", content_url);
        //下载content
        new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                Log.d("msg1", json);
                if (json!=null){
                    final ContentEntity contentEntity = JSONUtil.getContent(json);
                    //id
                    content_id = contentEntity.getHpcontent_id();
                    //文章图片
                    final String img_url = Constants.IMG_URL+contentEntity.getHp_img_url();
                    Glide.with(getActivity()).load(img_url)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.default_hp_image)
                            .into(iv_hp_image);
                    //文章标题
                    tv_hp_title.setText(contentEntity.getHp_title());
                    //文章的作者
                    tv_hp_author.setText(contentEntity.getHp_author());
                    //内容
                    tv_hp_content.setText(contentEntity.getHp_content());
                    //创作时间
                    tv_hp_maketime.setText(contentEntity.getHp_maketime());
                    //点赞数
                    praiseNum = contentEntity.getPraisenum();
                    tv_hp_praisenum.setText(praiseNum+"");
                    //分享方式
                    iv_share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转到分享页面
                            Intent intent = new Intent(BaseFragment.context, ShareActivity.class);
                            intent.putExtra("title",contentEntity.getHp_author());
                            intent.putExtra("img",img_url);
                            intent.putExtra("content",contentEntity.getHp_content());
                            startActivity(intent);

                        }
                    });

                }
            }
        }).downData(content_url,null,1);
        //设置缩放参数
        //获得屏幕的宽高
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthScreen = displayMetrics.widthPixels;
        heightScreen = displayMetrics.heightPixels;
        //header图片的x、y坐标
        int[] location=new int[2];
        iv_hp_image.getLocationOnScreen(location);
        locationX = iv_hp_image.getWidth();
        locationY = iv_hp_image.getHeight();

        povitY = widthScreen;
        povitY = heightScreen/2;
        scaleAnimation = new ScaleAnimation(
                locationX, toX,
                locationX, toY,
                povitY, povitY,Animation.RELATIVE_TO_SELF,1
        );
        scaleAnimation.setDuration(3000);
    }

    @Override
    public void setListener() {
    }

    @OnClick({R.id.iv_hp_image,R.id.iv_laud})
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_hp_image:
                iv_hp_image.startAnimation(scaleAnimation);
                break;
            case R.id.iv_laud:
                if(!isLaud){
                    praiseNum++;
                    isLaud = true;
                    Toast.makeText(HomeVpItemFragment.context,"再按一下可取消点赞",Toast.LENGTH_SHORT).show();
                }
                else {
                    praiseNum--;
                    isLaud = false;
                }
                tv_hp_praisenum.setText(""+praiseNum);
                //更新至数据库
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("content_id",content_id);
                    jsonObject.put("praise_num",praiseNum);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {
                        Log.d("msg", "update---laud--"+json);
                    }
                }).downData(null,jsonObject,4);

                break;
        }
    }

}
