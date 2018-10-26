package com.example.root.timer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textClock;
    SeekBar seekBar;
    Boolean counterIsActive = false;
    Button button;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft){

        int minute = (int) secondsLeft / 60;
        int seconds = secondsLeft - minute * 60;

        String secondString = Integer.toString(seconds);

        if(seconds <= 9)
            secondString = "0" +secondString;

        textClock.setText(Integer.toString(minute)+":"+ secondString);
    }

    public void resetTimer(){
        textClock.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        button.setText("GO!");
        seekBar.setEnabled(true);
        counterIsActive =false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textClock = findViewById(R.id.countdownTime);
        seekBar = findViewById(R.id.seekBar);
        button = findViewById(R.id.controlButton);


        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
        }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void goClick(View view) {

        if(!counterIsActive){

        counterIsActive = true;
        seekBar.setEnabled(false);
        button.setText("Stop");

        countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 +100, 1000){

            public void onTick(long millisecondsUntilDone){
                updateTimer((int) millisecondsUntilDone / 1000);
            }

            public void onFinish(){
                resetTimer();
            }
        }.start();

        }else{
            resetTimer();
        }
    }
}
