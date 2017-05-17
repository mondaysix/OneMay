package com.oy.fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v4.content.LocalBroadcastManager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oy.activity.MainActivity;
import com.oy.activity.R;
import com.oy.adapter.MusicCRyAdapter;
import com.oy.entity.MusicComment;
import com.oy.entity.MusicContent;
import com.oy.util.AudioPlayer;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;
import com.oy.widget.circleImageView.CircleTransform;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;



/**
 * Created by Lucky on 2016/10/31.
 */
public class MusicCFragment extends BaseFragment{
    @Bind(R.id.iv_music_anim)
    public ImageView iv_music_anim;
    @Bind(R.id.ll_ms)
    public LinearLayout ll_ms;
    //封面
    @Bind(R.id.iv_ms_cover)
    public ImageView iv_ms_cover;
    //story 内容区
    @Bind(R.id.iv_ms_story)
    public ImageView iv_ms_story;
    @Bind(R.id.ll_ms_story)
    public LinearLayout ll_ms_story;
    @Bind(R.id.tv_ms_title)
    public TextView tv_ms_title;
    @Bind(R.id.tv_ms_author)
    public TextView tv_ms_author;
    @Bind(R.id.tv_ms_content)
    public TextView tv_msg_content;
    //lyric 内容
    @Bind(R.id.iv_ms_lyric)
    public ImageView iv_ms_lyric;
    @Bind(R.id.ll_ms_lyric)
    public LinearLayout ll_ms_lyric;
    @Bind(R.id.tv_ms_ly_content)
    public TextView tv_ms_ly_content;
    //about 内容
    @Bind(R.id.iv_ms_about)
    public ImageView iv_ms_about;
    @Bind(R.id.ll_ms_about)
    public LinearLayout ll_ms_about;
    @Bind(R.id.tv_ms_ab_content)
    public TextView tv_ms_ab_content;
    //责任编辑
    @Bind(R.id.tv_ms_charge_edt)
    public TextView tv_ms_charge_edt;
    //点赞、评论区
    @Bind(R.id.tv_ms_praisenum)
    public TextView tv_ms_praisenum;
    @Bind(R.id.tv_ms_commentnum)
    public TextView tv_ms_commentnum;
    @Bind(R.id.tv_ms_sharenum)
    public TextView tv_ms_sharenum;
    //评论详情区
    @Bind(R.id.rv_ms_comment)
    public RecyclerView rv_ms_comment;
    public MusicCRyAdapter cRyAdapter;
    //歌手简单介绍
    @Bind(R.id.iv_ms_singer_head)
    public ImageView iv_ms_singer_head;
    @Bind(R.id.tv_ms_singer_name)
    public TextView tv_ms_singer_name;
    @Bind(R.id.tv_ms_singer_desc)
    public TextView tv_ms_singer_desc;
    @Bind(R.id.tv_ms_singer_title)
    public TextView tv_ms_singer_title;
    @Bind(R.id.tv_ms_singer_time)
    public TextView tv_ms_singer_time;
    //广播相关
    public LocalBroadcastManager localBroadManager;
    public MainActivity.BroadLocalReceiver broadReceiver;
    public IntentFilter intentFilter;
    //播放音乐
   public AudioPlayer player;
    public boolean isPause  = false;
    @Bind(R.id.iv_ms_play)
    public ImageView iv_ms_play;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_music_c;
    }

    @Override
    public void init(View view) {

        broadReceiver = new MainActivity.BroadLocalReceiver();
        //广播过滤
        intentFilter = new IntentFilter("com.action.play.album");
        //初始化广播
        localBroadManager = LocalBroadcastManager.getInstance(getActivity());
        //注册本地广播监听器
        localBroadManager.registerReceiver(broadReceiver,intentFilter);

//        //正在加载动画
        iv_music_anim.setImageResource(R.drawable.animation_default);
        AnimationDrawable  animDrawable = (AnimationDrawable) iv_music_anim.getDrawable();
        animDrawable.start();
        //评论recycleview
        cRyAdapter = new MusicCRyAdapter(getActivity());
        rv_ms_comment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_ms_comment.setAdapter(cRyAdapter);
        player = new AudioPlayer();
//        mediaPlayer = new MediaPlayer();
        //mediaPlayer = MediaPlayer.create(getActivity(),R.raw.music1);

    }
    String song_url;
    @Override
    protected void getBundletDatas(Bundle bundle) {
        final int music_id = bundle.getInt("m_id");
        String newUrl = String.format(Constants.MUSIC_DETAIL,music_id);
        new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                    ll_ms.setVisibility(View.GONE);

                    MusicContent musicCon = new MusicContent();
                    musicCon = JSONUtil.getMusicCon(json);
                    //歌曲id
                     song_url = Constants.SONG_URL + musicCon.getMucic_id();

                    Log.d("msg", "getJson: "+song_url);
                    if(musicCon.getCover()!=null){
                        String cover_url = Constants.IMG_URL +musicCon.getCover();
                        //封面
                        Glide.with(getActivity())
                                .load(cover_url)
                                .placeholder(R.drawable.default_hp_image)
                                .into(iv_ms_cover);
                    }


                    //歌手简单介绍
                    String singer_url = Constants.IMG_URL + musicCon.getAuthor().getWeb_url();
                    Glide.with(getActivity())
                            .load(singer_url)
                            .placeholder(R.drawable.head)
                            .transform(new CircleTransform(getActivity()))
                            .into(iv_ms_singer_head);
                    tv_ms_singer_name.setText(musicCon.getAuthor().getUser_name());
                    tv_ms_singer_desc.setText(musicCon.getAuthor().getDesc());
                    tv_ms_singer_title.setText(musicCon.getTitle());
                    tv_ms_singer_time.setText(musicCon.getMaketime());

                    //标题

                    tv_ms_title.setText(musicCon.getStory_title());
                    //作者
                    tv_ms_author.setText(musicCon.getUser_name());
                    //内容
                    CharSequence charSequence=Html.fromHtml(musicCon.getStory().replace("\\n","\n").replace("\\r","\r"));
                    tv_msg_content.setText(charSequence);
                    //该语句在设置后必加，不然没有任何效果  
                    tv_msg_content.setMovementMethod(LinkMovementMethod.getInstance());
                    //----------lyric
                    //内容
                    tv_ms_ly_content.setText(musicCon.getLyric().replace("\\n","\n").replace("\\r","\r"));
                    //---------about
                    //内容
                    tv_ms_ab_content.setText(musicCon.getInfo().replace("\\n","\n").replace("\\r","\r"));
                    //责任编辑
                    tv_ms_charge_edt.setText(musicCon.getCharge_edit());
                    //点赞、评论
                    tv_ms_praisenum.setText(""+musicCon.getPraisenum());
                    tv_ms_commentnum.setText(""+musicCon.getCommentnum());
                    tv_ms_sharenum.setText(""+musicCon.getSharenum());


                }
                else {
                    ll_ms.setVisibility(View.VISIBLE);
                }
            }
        }).downData(newUrl,null,1);
        //评论详情下载
        String comUrl = String.format(Constants.MUSIC_COMMENT,music_id);

        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                    List<MusicComment> commentList = JSONUtil.getComments(json);
                    cRyAdapter.setCommentList(commentList);
                }
            }
        }).downData(comUrl,null,1);
    }
    @OnClick({R.id.iv_ms_story,R.id.iv_ms_lyric,
            R.id.iv_ms_about,R.id.iv_ms_play})
    public void onItemClick(View view){
        switch (view.getId()){
            case R.id.iv_ms_story:
                //显示、隐藏布局
                ll_ms_story.setVisibility(View.VISIBLE);
                ll_ms_lyric.setVisibility(View.GONE);
                ll_ms_about.setVisibility(View.GONE);
                //切换图片
                iv_ms_story.setImageResource(R.drawable.music_story_selected);
                iv_ms_lyric.setImageResource(R.drawable.music_lyric_default);
                iv_ms_about.setImageResource(R.drawable.music_about_default);
                break;
            case R.id.iv_ms_lyric:
                //显示、隐藏布局
                ll_ms_story.setVisibility(View.GONE);
                ll_ms_lyric.setVisibility(View.VISIBLE);
                ll_ms_about.setVisibility(View.GONE);
                //切换图片
                iv_ms_story.setImageResource(R.drawable.music_story_default);
                iv_ms_lyric.setImageResource(R.drawable.music_lyric_selected);
                iv_ms_about.setImageResource(R.drawable.music_about_default);
                break;
            case R.id.iv_ms_about:
                //显示、隐藏布局
                ll_ms_story.setVisibility(View.GONE);
                ll_ms_lyric.setVisibility(View.GONE);
                ll_ms_about.setVisibility(View.VISIBLE);
                //切换图片
                iv_ms_story.setImageResource(R.drawable.music_story_default);
                iv_ms_lyric.setImageResource(R.drawable.music_lyric_default);
                iv_ms_about.setImageResource(R.drawable.music_about_selected);
                break;
            case R.id.iv_ms_play:
                //开始播放音乐
                Intent intent = new Intent("com.action.play.album");


                if (isPause && player.isPlaying()){

                    iv_ms_play.setImageResource(R.drawable.play);
                    player.pause();
                    isPause = false;
                }
                else {
                    Log.d("msg", "onItemClick: "+song_url);
                    if(song_url!=null){
                        player.playURL(song_url);
                    }

                    iv_ms_play.setImageResource(R.drawable.player_pause);
                    isPause = true;


                }
                intent.putExtra("show",isPause);
                localBroadManager.sendBroadcast(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player!=null){
           player.stop();
        }
        localBroadManager.unregisterReceiver(broadReceiver);
    }
}
