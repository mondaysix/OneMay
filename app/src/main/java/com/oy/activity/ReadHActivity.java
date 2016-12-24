package com.oy.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oy.adapter.ReadHCRyAdapter;
import com.oy.entity.ReadHead;
import com.oy.entity.ReadHeadDt;
import com.oy.util.Constants;
import com.oy.util.JSONUtil;
import com.oy.util.RetrofitUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHActivity extends BaseActivity {
    @Bind(R.id.scroll_read)
    public ScrollView scroll_read;
    private ReadHead readHead;
    @Bind(R.id.tv_rd_title)
    public TextView tv_rd_title;
    @Bind(R.id.tv_rd_bottom)
    public TextView tv_rd_bottom;
    @Bind(R.id.iv_rd_bottom)
    public ImageView iv_rd_bottom;
    //recycleview
    @Bind(R.id.rv_content)
    public RecyclerView rv_content;
    public ReadHCRyAdapter readHCRyAdapter;
    //头部
    @Bind(R.id.rd_header_ll)
    public LinearLayout rd_header_ll;
    @Override
    protected int setContentId() {
        return R.layout.activity_read_h;
    }

    @Override
    protected void init() {
        Bundle bundle = getIntent().getExtras();
         readHead = (ReadHead) bundle.getSerializable("ReadHead");
        rd_header_ll.setBackgroundColor(Color.parseColor(readHead.getBgcolor()));
        scroll_read.setBackgroundColor(Color.parseColor(readHead.getBgcolor()));
        tv_rd_title.setText(readHead.getTitle());
        tv_rd_bottom.setText(readHead.getBottom_text());
        Glide.with(this).load(readHead.getCover())
                .placeholder(R.drawable.default_reading_banner_image)
                .into(iv_rd_bottom);
        //初始化recycleview
        readHCRyAdapter = new ReadHCRyAdapter(this);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(readHCRyAdapter);
    }
    @Override
    protected void loadData() {
        String detailURL = String.format(Constants.READ_HEAD_DETAIL,Integer.valueOf(readHead.getId()));
        new RetrofitUtil().init(Constants.BASE_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
            @Override
            public void getJson(String json) {
                //获取详情页内容
                if (json!=null){
                    List<ReadHeadDt> headDtList = JSONUtil.getReadHDetail(json);
                    readHCRyAdapter.setReadHeadDts(headDtList);
                }
            }
        }).downData(detailURL);
    }
    @OnClick(R.id.iv_rd_close)
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_rd_close:
                finishActivity(0x234);
                finish();
                break;
        }
    }
}
