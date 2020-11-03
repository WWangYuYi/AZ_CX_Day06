package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MusicMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play;
    private Button pause;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);
        initView();
    }

    private void initView() {
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:

                break;
            case R.id.pause:

                break;
            case R.id.stop:

                break;
        }
    }
}