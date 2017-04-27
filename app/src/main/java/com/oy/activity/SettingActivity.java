package com.oy.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oy.widget.BlueDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Lucky on 2016/11/4.
 */
public class SettingActivity extends BaseActivity {
    public BluetoothAdapter bluetoothAdapter;
    @Override
    protected int setContentId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @OnClick(R.id.tv_bluth)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_bluth:
                BlueDialog blueDialog = new BlueDialog(this, new BlueDialog.OnClickListener() {
                    @Override
                    public void onComfirm(View view) {
                      if (!bluetoothAdapter.enable()){
                          Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                          startActivityForResult(intent,0);
                          Toast.makeText(getApplicationContext(),"开启成功",Toast.LENGTH_SHORT).show();
                      }
                        else {
                          Toast.makeText(getApplicationContext(),"已经打开蓝牙，无需打开了",Toast.LENGTH_SHORT).show();
                      }
                    }
                },bluetoothAdapter);
                blueDialog.show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(this,IndividualActivity.class));
            SettingActivity.this.finish();
        }
        return false;
    }
}
