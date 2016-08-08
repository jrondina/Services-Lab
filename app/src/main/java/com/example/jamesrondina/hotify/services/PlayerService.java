package com.example.jamesrondina.hotify.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import java.io.IOException;

/**
 * Created by jamesrondina on 8/8/16.
 */
public class PlayerService extends Service {

    private final String TAG = "PlayerService";
    String url = "http://tylergrund.com/mp3/Beatles/Taxman.mp3";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        Thread customThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // this is where the real work happens
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
                        mp.pause();
                        break;
                    case 2:
                        mp.reset();
                }
            }
        });

        customThread.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
