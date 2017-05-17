package com.oy.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oy.util.Constants;
import com.oy.util.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;

/**
 * Created by Meyki on 2017/5/12.
 */

public class HomeEditActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.et_word)
    public EditText et_word;
    public int content_id;
    @Bind(R.id.btn_con)
    public Button btn_con;
    public String content_before,content_after;
    @Override
    protected int setContentId() {
        return R.layout.activity_home_edit;
    }

    @Override
    protected void init() {
       Intent intent =  getIntent();
        content_id = intent.getIntExtra("content_id",0);
        String word = intent.getStringExtra("word");
        content_before = word;
        Log.d("msg2", "init: "+content_id+word);
        if(!"".equals(word)){
            et_word.setText(word);
        }

    }

    @Override
    protected void onItemListener() {
        et_word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content_after = s.toString();
            }
        });
        btn_con.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (content_after==null){
            Toast.makeText(this,"没有进行修改",Toast.LENGTH_SHORT).show();
        }
        else {
            if(!content_before.equals(content_after)){
                //上传至服务器
                JSONObject js = new JSONObject();
                try {
                    js.put("content_id",content_id);
                    js.put("content",content_after);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new RetrofitUtil().init(Constants.DOM_URL).setListener(new RetrofitUtil.OnGetJsonListener() {
                    @Override
                    public void getJson(String json) {
                        Log.d("msg", "update---content--"+json);
                        JSONObject js = null;
                        try {
                            js = new JSONObject(json);
                            if(js.getString("status").equals("200")){
                                Toast.makeText(HomeEditActivity.this,"重启app刷新界面",Toast.LENGTH_LONG).show();
                                HomeEditActivity.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).downData(null,js,5);

            }
            else {
                Toast.makeText(this,"没有进行修改",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
