package com.oy.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.oy.activity.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MyPlayer extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, View.OnClickListener {
    public VideoView videoView;
    public String url;
    //手势检测器
    private GestureDetector gestureDetector;
    //底部控制栏
    public LinearLayout ll_bottom;
    public ImageView iv_play;
    public TextView tv_startime;
    public TextView tv_endtime;
    public SeekBar sb_progress;
    public ImageView iv_screen;
    public boolean isTouch = false;//是否拖动进度条
    //Activity
    public Activity activity;
    //videoview 宽高
    public int videoWidth;
    public int videoHeight;
    //缩放模式
    private int scaletype = VideoView.VIDEO_LAYOUT_STRETCH;
    //缓冲进度控件
    public LinearLayout ll_middle_buffer;
    public TextView tv_jindu;
    //控制音量
    public LinearLayout ll_sound;
    public ImageView iv_sound;
    public TextView tv_sound;
    //声音管理器
    private AudioManager audioManager;
    private int audio = -1;//当前音量
    private int maxAudio;//最大音量
    //亮度相关控件
    private LinearLayout ll_light;
    private ImageView iv_light;
    private TextView tv_light;
    private int light=-1;
    //快进、退
    private int total;
    //头部
    public LinearLayout ll_head;
    /**
     * TODO Handler
     */
    private static final int SEND_MESSAGE_SETPROGRESS = 1;//设置当前的播放进度
    private static final int SEND_MESSAGE_HIDE_BOTTOM = 2;//隐藏底部的控件
    private static final int SEND_MESSAGE_HIND_HEAD=3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SEND_MESSAGE_SETPROGRESS:
                    setProgress();
                    this.sendEmptyMessageDelayed(SEND_MESSAGE_SETPROGRESS,1000);
                    break;
                case SEND_MESSAGE_HIDE_BOTTOM:
                    hiddenBottom();
                    break;
                case SEND_MESSAGE_HIND_HEAD:
                    //隐藏头部
                    hiddenHead();

            }
        }
    };
    public MyPlayer(Context context) {
        this(context,null);
    }

    public MyPlayer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (context instanceof  Activity){
            this.activity = (Activity) context;
        }
        initView();
    }

    private void initView() {
        //初始化vitamio
        Vitamio.isInitialized(getContext());
        //加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.custom_play_layout,this,true);
        //播放器
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnPreparedListener(this);
        videoView.setOnInfoListener(this);
        videoView.setOnBufferingUpdateListener(this);
        videoView.setOnCompletionListener(this);
        //头部控制栏
        ll_head = (LinearLayout) findViewById(R.id.ll_head);
        //底部控制栏
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        iv_play.setOnClickListener(this);
        tv_startime = (TextView) findViewById(R.id.tv_startime);
        tv_endtime = (TextView) findViewById(R.id.tv_endtime);
        sb_progress  = (SeekBar) findViewById(R.id.sb_progress);
        sb_progress.setOnSeekBarChangeListener(this);
//        iv_screen = (ImageView) findViewById(R.id.iv_screen);
//        iv_screen.setOnClickListener(this);
        //初始化手势检测器
        gestureDetector = new GestureDetector(getContext(),new GestureListener());
        //缓冲相关控件
        ll_middle_buffer = (LinearLayout) findViewById(R.id.ll_middle_buffer);
        tv_jindu = (TextView) findViewById(R.id.tv_jindu);
        //音量相关控件
        ll_sound = (LinearLayout) findViewById(R.id.ll_sound);
        iv_sound = (ImageView) findViewById(R.id.iv_sound);
        tv_sound = (TextView) findViewById(R.id.tv_sound);
        //获得系统的音频管理器
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        maxAudio = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获得最大音量
        //亮度相关控件
        ll_light = (LinearLayout) findViewById(R.id.ll_light);
        iv_light = (ImageView) findViewById(R.id.iv_light);
        tv_light = (TextView) findViewById(R.id.tv_light);
    }
    public void play(String url){
        play(url,0);
    }
    //按进度播放
    public void play(String url,int position){
        this.url = url;
        if (this.url!=null){
            videoView.setVideoPath(url);
            if (position>0){
                //设置进度
                videoView.seekTo(position);
            }
            videoView.start();
            iv_play.setImageResource(R.drawable.ic_media_pause);
            //设置当前进度
            handler.sendEmptyMessageDelayed(SEND_MESSAGE_SETPROGRESS,1000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_play:
                if (videoView!=null && videoView.isPlaying()){
                    videoView.pause();
                    iv_play.setImageResource(R.drawable.ic_media_play);
                }else {
                    videoView.start();
                    iv_play.setImageResource(R.drawable.ic_media_pause);
                }
                break;
//            case R.id.iv_screen:
//                //切换横竖屏
//                toggleScreen();
//                break;
        }
    }
    public boolean isFullScreen = false;
    /**
     * TODO 设置横竖屏
     */
    public void toggleScreen(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            if (activity != null) {
                //设置竖屏
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                iv_screen.setImageResource(R.drawable.ic_media_fullscreen_stretch);
                isFullScreen = false;
            }
        }

//        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            if (activity!=null){
//                //设置横屏
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                iv_screen.setImageResource(R.drawable.ic_media_fullscreen_shrink);
//                isFullScreen = true;
//            }
//        }
    }

//    /**
//     * 横竖屏切换回掉
//     * @param newConfig
//     */
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        hiddenBottom();
//        if (isFullScreen) {
//            //隐藏状态栏
//            if (activity != null) {
//                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//            }
//            //设置播放器的宽度
//            int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
//            int screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
//            ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
//            layoutParams.width = screenWidth;
//            layoutParams.height = screenHeight;
//            setLayoutParams(layoutParams);
//        } else {
//            //竖屏
//            if (activity != null) {
//                //显示状态栏
//                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            }
//            //设置宽高
//            ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
//            layoutParams.width = videoWidth;
//            layoutParams.height = videoHeight;
//            setLayoutParams(layoutParams);
//        }
//        //重新设置拉伸模式
//        videoView.setVideoLayout(scaletype, 0);
//    }

    /**
     * 设置伸缩模式
     *r
     */
    public MyPlayer setScaleType(int scale){
        this.scaletype = scale;
        return this;
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv_startime.setText(getTimer(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isTouch = true;
        //监听滑动滚动条取消隐藏底部视图
        handler.removeMessages(SEND_MESSAGE_HIDE_BOTTOM,2000);

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isTouch = false;
        int progress = seekBar.getProgress();
        videoView.seekTo(progress);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoWidth = videoView.getWidth();
        videoHeight = videoView.getHeight();
        total = (int) videoView.getDuration();
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what){
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                //正在缓冲
                ll_middle_buffer.setVisibility(VISIBLE);
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                //缓冲结束
                ll_middle_buffer.setVisibility(GONE);
                break;
        }
        return false;
    }

    /**
     * 缓冲回掉方法
     * @param mp      the MediaPlayer the update pertains to
     * @param percent the percentage (0-100) of the buffer that has been filled thus
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        tv_jindu.setText(percent+"%");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        handler.removeMessages(SEND_MESSAGE_SETPROGRESS);
    }

    /**
     * 手势监听器
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        private boolean isFirst = false;//是否第一次滑动
        private boolean isLand = false;//处理滑动的方向
        private boolean isLight = false;//处理亮度和声音

        /**
         * 按下
         * @param e
         * @return
         */
        @Override
        public boolean onDown(MotionEvent e) {
            isFirst = true;
            return true;
        }

        /**
         * 轻按
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            //显示隐藏底部控件
            if (isShow(ll_bottom) && isShow(ll_head)){
                hiddenHead();
                hiddenBottom();
            }
            else {
                showHead(2000);
                showBottom(2000);

            }
            return super.onSingleTapUp(e);
        }

        /**
         * 双击
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        /**
         * 滑动
         * @param e1
         * @param e2
         * @param distanceX
         * @param distanceY
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //获得按下的点的坐标
            float bx = e1.getX();
            float by = e1.getY();
//            Log.d("msg", "bx-->"+bx+"by--->"+by);
            //滑动过程中点移动的坐标
            float ex = e2.getX();
            float ey = e2.getY();
//            Log.d("msg", "ex-->"+ex+"ey--->"+ey);
            float mx = ex - bx;
            float my = by - ey;//往上滑动，ey逐渐减少，
//            Log.d("msg", "mx---"+mx+"my--"+my);
//            Log.d("msg", "distanceX:"+distanceX+"distanceY:"+distanceY);
            if(isFirst){
                if (Math.abs(distanceX)<=Math.abs(distanceY)){
                    //纵向滑动
                    isLand = false;
                    if (bx<=getWidth()/2){
                        //在左半边屏幕
                        isLight = true;
                    }else {
                        isLight = false;
                    }
                }else {
                    //处理横向的滑动
                    isLand = true;

                }
                isFirst = false;
            }
            //处理滑动事件
            if (isLand){
                //纵向控制播放进度
                float x = mx/getWidth();
                Log.d("msg", "x---->"+x);
            }else {
                //获得滑动的百分比
                float p = my/getHeight();
                if (isLight){
                    //左半边控制亮度
                    setLight(p);
                }
                else {
                    //右半边控制音量
                    setSound(p);
                }
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
    public void setJindu(float p){
        int position = (int) (p*100);
        videoView.seekTo(position);
    }
    /**
     * 设置亮度
     * @param p
     */
    public void setLight(float p){
        if (light == -1){
            //获得当前的亮度0~1
            light = (int) activity.getWindow().getAttributes().screenBrightness;
        }
        float newLight = p*1+light;
        if (newLight>1){
            newLight = 1;
        }else if (newLight<0.01){
            newLight = 0.01f;
        }
        //设置亮度
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = newLight;
        activity.getWindow().setAttributes(attributes);
        //控制亮度文本
        ll_light.setVisibility(VISIBLE);
        tv_light.setText((int)(newLight*100)+"%");
    }
    /**
     * 设置声音
     * @param p
     */
    private void setSound(float p){

        if (audio==-1){
            audio = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//获得当前音量
        }
        //获得当前音量
        int newAudio = (int) (p*maxAudio+audio);
        if (newAudio>maxAudio){
            newAudio = maxAudio;
        }else if (newAudio<0){
            newAudio = 0;
        }
        //设置音量
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,newAudio,0);
        //显示中间音量控件
        String s = (int)(((float)newAudio/maxAudio)*100)+"%";
        ll_sound.setVisibility(VISIBLE);
        tv_sound.setText(s);
        iv_sound.setImageResource(newAudio==0?R.drawable.ic_volume_off_white_36dp:R.drawable.ic_volume_up_white_36dp);
    }
    /**
     * 触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)){
            return true;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                audio = -1;
                ll_sound.setVisibility(GONE);
                light = -1;
                ll_light.setVisibility(GONE);
                break;
        }
        return false;
    }
    /**
     * 显示底部控件
     */
    private void showButton(){
        showBottom(0);
    }
    /**
     * 设置时间显示底部控件
     */
    private void showBottom(long timer){
        if (ll_bottom!=null && ll_bottom.getVisibility() == GONE){
            ll_bottom.setVisibility(VISIBLE);
            if (timer>0){
                handler.sendEmptyMessageDelayed(SEND_MESSAGE_HIDE_BOTTOM,timer);
            }
        }

    }
    /**
     * 隐藏底部控件
     */
    private void hiddenBottom(){
        if (ll_bottom!=null && ll_bottom.getVisibility()==VISIBLE){
            ll_bottom.setVisibility(GONE);
        }
    }
    /**
     * 隐藏头部控件
     */
    private void hiddenHead(){
        if (ll_head!=null&& ll_head.getVisibility()==VISIBLE){
            ll_head.setVisibility(GONE);
        }
    }
    /**
     * 设置时间显示头部控件
     */
    private void showHead(long timer){
        if (ll_head!=null && ll_head.getVisibility() == GONE){
            ll_head.setVisibility(VISIBLE);
            if (timer>0){
                handler.sendEmptyMessageDelayed(SEND_MESSAGE_HIND_HEAD,timer);
            }
        }

    }
    /**
     * 是否显示
     */
    private boolean isShow(View view){
        return view.getVisibility()==VISIBLE;
    }
    /**
     * 设置播放进度
     */
    private void setProgress(){
        //获得当前的进度
        int position = (int) videoView.getCurrentPosition();
        int duration = (int) videoView.getDuration();
        if (!isTouch){
            if (sb_progress!=null){
                sb_progress.setMax(duration);
                sb_progress.setProgress(position);
            }
            //设置文本
            tv_startime.setText(getTimer(position));
            tv_endtime.setText(getTimer(duration));
        }
    }
    /**
     * 转化毫秒数为时分秒
     */
    private String getTimer(long timer){
        int h = (int) (timer / 1000 / 60 / 60);
        int m = (int) ((timer / 1000 / 60 % 60));
        int s = (int) (timer / 1000 % 60);
        return (h >= 10 ? h : ("0" + h)) + ":" + (m >= 10 ? m : ("0" + m)) + ":" + (s >= 10 ? s : ("0" + s));
    }
    /**
     * 暂停
     */
    int currentPosition=-1;
    public void onPause(){
        if (videoView!=null){
            videoView.pause();
            //记录当前播放的位置
            currentPosition = (int) videoView.getCurrentPosition();

        }
    }
    /**
     * 重新播放
     */
    public void onResume(){
        if (videoView!=null){
            videoView.resume();
            //根据记录的位置重新开始播放
            if (currentPosition!=-1){
                play(this.url,currentPosition);

            }
        }
    }
    /**
     * 销毁
     */
    public void onDestroy(){
        if (videoView!=null){
            //清空handler中的所有事件
            handler.removeCallbacksAndMessages(null);
            videoView.stopPlayback();
        }
    }

    /**
     * 横屏按下back切换回竖屏，竖屏按下back直接退出
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isFullScreen){
         toggleScreen();
            return true;
        }
        return false;
    }
}
