package com.oy.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oy.activity.R;

import butterknife.OnClick;

/**
 * Created by Lucky on 2016/11/1.
 */
public class CustomDialog extends Dialog implements View.OnClickListener {
    public Context context;
    public OnClickListener onClickListener;
    public CustomDialog(Context context,OnClickListener onClickListener) {
        this(context,0);
        this.onClickListener = onClickListener;
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null);
        TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
        tv_confirm.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
        LinearLayout.LayoutParams lp  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = 200;
        lp.height = 300;
        this.addContentView(view,lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                onClickListener.onComfirm(v);
                this.dismiss();
                break;
            case R.id.tv_cancle:
                this.dismiss();
                break;
        }
    }

    public interface OnClickListener{
        void onComfirm(View view);
    }

}
