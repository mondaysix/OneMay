package com.oy.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.oy.adapter.MovieCRyAdapter;
import com.oy.entity.MovieComment;
import com.oy.entity.MovieList;
import com.oy.entity.MovieStory;
import com.oy.entity.MovieTab;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;
import com.oy.widget.CustomDialog;
import com.oy.widget.circleImageView.CircleTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/11/1.
 */
public class MovieActivity extends BaseActivity {
    //标题
    @Bind(R.id.tv_mv_title)
    public TextView tv_mv_title;
    @Bind(R.id.iv_mv_cover)
    public ImageView iv_mv_cover;
    @Bind(R.id.tv_mv_score)
    public TextView tv_mv_score;
    public int movie_id;
    //影片简介---用户
    @Bind(R.id.iv_mv_head)
    public ImageView iv_mv_head;
    @Bind(R.id.tv_mv_user)
    public TextView tv_mv_user;
    @Bind(R.id.tv_mv_time)
    public TextView tv_mv_time;
    @Bind(R.id.tv_mv_praisenum)
    public TextView tv_mv_praisenum;
    @Bind(R.id.tv_mv_st)
    public TextView tv_mv_st;
    //电影表
    public MovieTab movieTab;
    //影片关键字
    @Bind(R.id.iv_mv_keyword)
    public ImageView iv_mv_keyword;
    @Bind(R.id.ll_keyword)
    public LinearLayout ll_keyword;
    @Bind(R.id.tv_mv_keyword1)
    public TextView tv_mv_keyword1;
    @Bind(R.id.tv_mv_keyword2)
    public TextView tv_mv_keyword2;
    @Bind(R.id.tv_mv_keyword3)
    public TextView tv_mv_keyword3;
    @Bind(R.id.tv_mv_keyword4)
    public TextView tv_mv_keyword4;
    @Bind(R.id.tv_mv_keyword5)
    public TextView tv_mv_keyword5;
    //影片剧照
    @Bind(R.id.iv_mv_photo)
    public ImageView iv_mv_photo;
    @Bind(R.id.hsv_mv)
    public HorizontalScrollView hsv_mv;
    @Bind(R.id.ll_mv_photo)
    public LinearLayout ll_mv_photo;
    //影片简介
    @Bind(R.id.iv_mv_info)
    public ImageView iv_mv_info;
    @Bind(R.id.tv_mv_info)
    public TextView tv_mc_info;
    //电影表---->标题
    @Bind(R.id.tv_mv_tab)
    public TextView tv_mv_tab;
    //评论区---recycleview
    @Bind(R.id.rv_mv_comment)
    public RecyclerView rv_mv_comment;
    public MovieCRyAdapter movieComAdapter;
    public MovieStory.DataBean dataBean;
    @Override
    protected int setContentId() {
        return R.layout.activity_movie;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MovieList movieList = (MovieList) bundle.getSerializable("movie");
        movie_id = Integer.valueOf(movieList.getId());
        tv_mv_title.setText(movieList.getTitle());

        tv_mv_score.setText(movieList.getScore());
        Typeface typeface = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/textstyle.ttf");
        tv_mv_score.setTypeface(typeface);
        //评论区recycleview
        movieComAdapter = new MovieCRyAdapter(this);
        rv_mv_comment.setLayoutManager(new LinearLayoutManager(this));
        rv_mv_comment.setAdapter(movieComAdapter);
    }

    @Override
    protected void loadData() {
        //电影简介
        String newUrl = String.format(Constants.MOVIE_DETAIL,movie_id);
        //下载电影表
        final String movie_tab = String.format(Constants.MOVIE_TABLE,movie_id);
        //评论区url
        String commentUrl = String.format(Constants.MUSIC_COMMENT,movie_id);

        new RetrofitUtil().init(Constants.BASE_URL)
                .setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {
                        if (json!=null){
                             dataBean = JSONUtil.getMovieStory(json).get(0);

                            Glide.with(MovieActivity.getContext())
                                    .load(dataBean.getUser().getWeb_url())
                                    .transform(new CircleTransform(MovieActivity.getContext()))
                                    .into(iv_mv_head);
                            tv_mv_user.setText(dataBean.getUser().getUser_name());
                            tv_mv_time.setText(dataBean.getInput_date());
                            tv_mv_praisenum.setText(dataBean.getPraisenum()+"");
                            tv_mv_st.setText(dataBean.getTitle());
                            Spanned spanned = Html.fromHtml(dataBean.getContent());
                            ExpandableTextView expand_text_view = (ExpandableTextView) findViewById(R.id.expand_text_view);
                            expand_text_view.setText(spanned);
                        }
                    }
                }).downData(newUrl,null,1);
        //电影表--
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                movieTab = JSONUtil.getMovieTab(json);
                Glide.with(MovieActivity.getContext())
                        .load(movieTab.getDetailcover())
                        .placeholder(R.drawable.movie_placeholder_3)
                        .into(iv_mv_cover);
                //设置五个关键字
                String keywords =  movieTab.getKeywords();
                String[] strings = keywords.split(";");
                tv_mv_keyword1.setText(strings[0]);
                tv_mv_keyword2.setText(strings[1]);
                tv_mv_keyword3.setText(strings[2]);
                tv_mv_keyword4.setText(strings[3]);
                tv_mv_keyword5.setText(strings[4]);
                //设置横向scrollview---影片剧照
                for (int i = 0;i<movieTab.getPhoto().size();i++){
                    String photo_url = movieTab.getPhoto().get(i);
                    View view = LayoutInflater.from(MovieActivity.getContext()).inflate(R.layout.hsv_image_item,null);
                    ImageView iv = (ImageView) view.findViewById(R.id.iv_mv_p);
                    Glide.with(MovieActivity.getContext()).load(photo_url)
                            .placeholder(R.drawable.default_img_bg)
                            .into(iv);
                    iv.setPadding(5,5,5,5);
                    ll_mv_photo.addView(view);
                }
                //影片信息
                tv_mc_info.setText(movieTab.getInfo());
            }
        }).downData(movie_tab,null,1);
        //评论区
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                List<MovieComment> movieComment = JSONUtil.getMovieComments(json);
                movieComAdapter.setMovieComments(movieComment);
            }
        }).downData(commentUrl,null,1);
    }

    @OnClick({R.id.iv_mv_back,R.id.iv_mv_keyword,R.id.iv_mv_photo,R.id.iv_mv_info,R.id.iv_mv_cover})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_mv_back:
                finishActivity(0x789);
                finish();
                break;
            case R.id.iv_mv_keyword:
                //关键字布局
                //修改点击后的图片

                tv_mv_tab.setText(MovieActivity.getContext().getResources().getString(R.string.movie_tab));
                hsv_mv.setVisibility(View.GONE);
                tv_mc_info.setVisibility(View.GONE);
                iv_mv_keyword.setImageResource(R.drawable.gross_selected);
                ll_keyword.setVisibility(View.VISIBLE);
                iv_mv_photo.setImageResource(R.drawable.still_default);
                iv_mv_info.setImageResource(R.drawable.plot_default);
                break;
            case R.id.iv_mv_photo:
                //影片剧照
                tv_mv_tab.setText(MovieActivity.getContext().getResources().getString(R.string.movie_photo));
                ll_keyword.setVisibility(View.GONE);
                tv_mc_info.setVisibility(View.GONE);
                iv_mv_keyword.setImageResource(R.drawable.gross_default);
                iv_mv_photo.setImageResource(R.drawable.still_selected);
                hsv_mv.setVisibility(View.VISIBLE);
                iv_mv_info.setImageResource(R.drawable.plot_default);
                break;
            case R.id.iv_mv_info:
                tv_mv_tab.setText(MovieActivity.getContext().getResources().getString(R.string.movie_info));
                ll_keyword.setVisibility(View.GONE);
                hsv_mv.setVisibility(View.GONE);
                iv_mv_keyword.setImageResource(R.drawable.gross_default);
                iv_mv_photo.setImageResource(R.drawable.still_default);
                iv_mv_info.setImageResource(R.drawable.plot_selected);
                tv_mc_info.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mv_cover:
                //对话框---提示
                final CustomDialog customDialog = new CustomDialog(this, new CustomDialog.OnClickListener() {
                    @Override
                    public void onComfirm(View view) {
                        Intent intent = new Intent(MovieActivity.getContext(),VideoActivity.class);
                        intent.putExtra("video",movieTab.getVideo());
                        intent.putExtra("title",movieTab.getTitle());
                        startActivity(intent);
                    }
                });
                //取消标题栏
                customDialog.show();
//
                break;

        }
    }
}
