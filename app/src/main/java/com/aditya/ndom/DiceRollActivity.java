package com.aditya.ndom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class DiceRollActivity extends AppCompatActivity {
    private SensorManager sm;
    private float acelVal,acelLast,shake;
    private ImageView diceImageView;
    private Button rollButton;
    Animation rotateAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roll);
        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        acelVal=SensorManager.GRAVITY_EARTH;
        acelLast=SensorManager.GRAVITY_EARTH;
        shake=0.00f;
        diceImageView=findViewById(R.id.diceImageView);
        rollButton=findViewById(R.id.rollButton);
        rotateAnimation= AnimationUtils.loadAnimation(DiceRollActivity.this,R.anim.rotate);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceRoll();
            }
        });
        diceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceRoll();
            }
        });
    }
    private final SensorEventListener sensorListener=new SensorEventListener(){
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x=sensorEvent.values[0];
            float y=sensorEvent.values[1];
            float z=sensorEvent.values[2];
            acelLast=acelVal;
            acelVal=(float)Math.sqrt((double)(x*x+y*y+z*z));
            float delta=acelVal-acelLast;
            shake=shake*0.9f+delta;
            if(shake>6){
                diceRoll();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    private void diceRoll(){
        int rand=(int)(Math.random()*6)+1;
        final MediaPlayer mediaPlayer=MediaPlayer.create(DiceRollActivity.this,R.raw.dice_roll);
        mediaPlayer.start();
        diceImageView.startAnimation(rotateAnimation);
        switch(rand){
            case 1:diceImageView.setImageResource(R.drawable.dice_1);break;
            case 2:diceImageView.setImageResource(R.drawable.dice_2);break;
            case 3:diceImageView.setImageResource(R.drawable.dice_3);break;
            case 4:diceImageView.setImageResource(R.drawable.dice_4);break;
            case 5:diceImageView.setImageResource(R.drawable.dice_5);break;
            case 6:diceImageView.setImageResource(R.drawable.dice_6);break;
        }
    }
}