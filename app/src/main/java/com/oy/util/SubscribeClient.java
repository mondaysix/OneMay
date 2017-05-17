package com.oy.util;



import android.util.Log;

import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;


/**
 * Created by Meyki on 2017/2/18.
 */

public class SubscribeClient {
    public String CONNECTION_STRING = "tcp://192.168.6.105:1884";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 10;//低耗网络，但是又需要及时获取数据，心跳30s
    private final static String CLIENT_ID = "client";

    private MqttClient mqttClient = null;

        public SubscribeClient(String i){
        //connect servers
        try {
            mqttClient = new MqttClient(CONNECTION_STRING);
            mqttClient.connect(CLIENT_ID+i, CLEAN_START, KEEP_ALIVE);
            SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();
            mqttClient.registerSimpleHandler(simpleCallbackHandler);//注册接收消息方法
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    public void upAvatar (String picstr, String bs) {
        //connect servers
//        try {
//            mqttClient = new MqttClient(CONNECTION_STRING);
//            mqttClient.connect(CLIENT_ID + i, CLEAN_START, KEEP_ALIVE);
//            SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();
//            mqttClient.registerSimpleHandler(simpleCallbackHandler);//注册接收消息方法
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
        try {

            mqttClient.subscribe(Constants.TOPICS, Constants.QOS_VALUES);//订阅接主题
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 完成订阅后，可以增加心跳，保持网络通畅，也可以发布自己的消息
         */

        try {
            if (!picstr.equals("")) {
                Log.d("msg", "upAvatar: " + picstr);
                mqttClient.publish("publishmsg", picstr.getBytes(), Constants.QOS_VALUES[0], true);
                mqttClient.publish("publishmsg2", bs.getBytes(), Constants.QOS_VALUES[0], true);

                //  mqttClient.publish("user/getPic","test".getBytes(),Contants.QOS_VALUES[0],true);
            }


        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public MqttClient getMqttClient(){
        if(mqttClient!=null){
            return mqttClient;
        }
        else
            return null;

    }
}
