package com.example.jamesrondina.hotify.services;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by jamesrondina on 8/8/16.
 */
public class PlayerIntentService extends IntentService {

    private final String TAG = "PlayerIntentService";
    String url = "https://googledrive.com/host/0B9VqDeB2Cm-RdVo2cVA4T3FtZm8/cena.mp3";

    public PlayerIntentService() {
        super("PlayerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final MediaPlayer mp = new MediaPlayer();

        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(url);
            mp.prepare();
            Log.i(TAG,"Starting Media Player");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG,"File Source Not Found");
        }

        int command = intent.getIntExtra("command",0);

        switch (command) {
            case 0:
                mp.start();
                break;
            case 1:
                mp.release();
                break;
            case 2:
                mp.reset();
        }



    }


}
