package com.example.jamesrondina.hotify;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.jamesrondina.hotify.services.PlayerService;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton mPlay, mPause, mStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlay = (ImageButton) findViewById(R.id.play);
        mPause = (ImageButton) findViewById(R.id.pause);
        mStop = (ImageButton) findViewById(R.id.stop);

        /*final MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(url);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        final Intent intent = new Intent(MainActivity.this, PlayerService.class);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.play:
                        //mp.start();
                        intent.putExtra("command",0);
                        startService(intent);
                        break;
                    case R.id.pause:
                        //mp.pause();
                        intent.putExtra("command",1);
                        startService(intent);
                        break;
                    case R.id.stop:
                        //mp.reset();
                        intent.putExtra("command",2);
                        startService(intent);
                        break;
                }
            }
        };

        mPlay.setOnClickListener(listener);
        mPause.setOnClickListener(listener);
        mStop.setOnClickListener(listener);




    }
}
