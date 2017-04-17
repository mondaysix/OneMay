package com.oy.widget;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oy.activity.R;

/**
 * Created by Lucky on 2016/11/4.
 */
public class BlueDialog extends Dialog implements View.OnClickListener {
    public Context context;
    public OnClickListener onClickListener;
    public BluetoothAdapter bluetoothAdapter;
    public BlueDialog(Context context,OnClickListener onClickListener, BluetoothAdapter bluetoothAdapter) {
        this(context,0);
        this.onClickListener = onClickListener;
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public BlueDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.blue_dialog,null);
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
                if (bluetoothAdapter!=null){
                    bluetoothAdapter.disable();
                }
                this.dismiss();
                break;
        }
    }

    public interface OnClickListener{
        void onComfirm(View view);
    }
}
