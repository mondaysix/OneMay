package com.oy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oy.activity.R;
import com.oy.activity.ReadHActivity;
import com.oy.entity.ReadHead;
import com.oy.util.Constants;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import immortalz.me.library.TransitionsHeleper;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHFragment extends BaseFragment {
    @Bind(R.id.iv_music_head)
    public ImageView iv_music_head;
    public ReadHead readHead;
    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_read_h;
    }

    @Override
    protected void getBundletDatas(Bundle bundle) {
         readHead = (ReadHead) bundle.getSerializable("head");
        String cover_url = Constants.IMG_URL+readHead.getCover();
        Glide.with(getActivity())
                .load(cover_url)
                .placeholder(R.drawable.default_reading_banner_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_music_head);


    }
    @OnClick(R.id.iv_music_head)
    public void onClickListener(View view){
        switch (view.getId()){
            case R.id.iv_music_head:
                //跳转到详情页面
                Intent intent = new Intent(getActivity(), ReadHActivity.class);
                intent.putExtra("ReadHead",readHead);
                startActivity(intent);
                break;
        }
    }
}
