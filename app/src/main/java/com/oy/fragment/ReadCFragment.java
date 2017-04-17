package com.oy.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.oy.activity.R;
import com.oy.entity.ReadContent;

import butterknife.Bind;

/**
 * Created by Lucky on 2016/10/31.
 */
public class ReadCFragment extends BaseFragment {
    //相关对象
    public ReadContent.EssayContent essayContent;
    public ReadContent.Serial serial;
    public ReadContent.Question question;
    //EssayContent的控件
    @Bind(R.id.tv_rd_ct_es_title)
    public TextView tv_rd_ct_es_title;
    @Bind(R.id.tv_rd_ct_es_name)
    public TextView tv_rd_ct_es_name;
    @Bind(R.id.tv_rd_ct_es_word)
    public TextView tv_rd_ct_es_word;

    //Serial的控件
    @Bind(R.id.tv_rd_ct_se_title)
    public TextView tv_rd_ct_se_title;
    @Bind(R.id.tv_rd_ct_se_name)
    public TextView tv_rd_ct_se_name;
    @Bind(R.id.tv_rd_ct_se_word)
    public TextView tv_rd_ct_se_word;

    //Question的控件
    @Bind(R.id.tv_rd_ct_qu_title)
    public TextView tv_rd_ct_qu_title;
    @Bind(R.id.tv_rd_ct_qu_name)
    public TextView tv_rd_ct_qu_name;
    @Bind(R.id.tv_rd_ct_qu_word)
    public TextView tv_rd_ct_qu_word;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_read_c;
    }

    @Override
    protected void getBundletDatas(Bundle bundle) {
        essayContent = (ReadContent.EssayContent) bundle.getSerializable("essay");
        serial = (ReadContent.Serial) bundle.getSerializable("serical");
        question = (ReadContent.Question) bundle.getSerializable("question");
        //EssayContent
        tv_rd_ct_es_title.setText(essayContent.getHp_title());
        tv_rd_ct_es_name.setText(essayContent.getAuthor().get(0).getUser_name());
        tv_rd_ct_es_word.setText(essayContent.getGuide_word());
        //Serial
        tv_rd_ct_se_title.setText(serial.getTitle());
        tv_rd_ct_se_name.setText(serial.getAuthor().getUser_name());
        tv_rd_ct_se_word.setText(serial.getExcerpt());
        //Question
        tv_rd_ct_qu_title.setText(question.getQuestion_title());
        tv_rd_ct_qu_name.setText(question.getAnswer_title());
        tv_rd_ct_qu_word.setText(question.getAnswer_content());
    }

    @Override
    public void loadDatas() {

    }
}
