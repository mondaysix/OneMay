package com.oy.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.oy.activity.MovieActivity;
import com.oy.activity.R;
import com.oy.adapter.MovieRyAdapter;
import com.oy.entity.MovieList;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/10/30.
 */
public class MovieFragment extends BaseFragment {
    @Bind(R.id.rv_mv_list)
    public RecyclerView rv_mv_list;
    public MovieRyAdapter movieRyAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void init(View view){
        movieRyAdapter = new MovieRyAdapter(getActivity());
        rv_mv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_mv_list.setAdapter(movieRyAdapter);
    }

    @Override
    public void setListener() {
        movieRyAdapter.setOnItemClickListener(new MovieRyAdapter.OnItemClickListener() {
            @Override
            public void setOnClick(View v) {
                MovieList movie = (MovieList) v.getTag(R.id.movie_obj);
                Intent intent = new Intent(getActivity(),MovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie",movie);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadDatas() {
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                if (json!=null){
                   List<MovieList> movieLists =  JSONUtil.getMovieList(json);
                    movieRyAdapter.setMovieLists(movieLists);
                }
            }
        }).downData(Constants.MOVIE_HOME,null,1);
    }
}
