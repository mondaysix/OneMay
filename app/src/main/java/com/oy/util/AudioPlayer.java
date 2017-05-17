package com.oy.util;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Meyki on 2017/5/2.
 */

public class AudioPlayer implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {
    public MediaPlayer mediaPlayer;
    public AudioPlayer(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnPreparedListener(this);
    }
    public void play(){
        mediaPlayer.start();
    }
    public void playURL(String videourl){

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videourl);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pause(){
        mediaPlayer.pause();
    }
    public void stop(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public boolean isPlaying(){
        if(mediaPlayer.isPlaying()){
            return true;
        }
        return false;
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
