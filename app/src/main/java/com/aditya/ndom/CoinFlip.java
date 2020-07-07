package com.aditya.ndom;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class CoinFlip extends AppCompatActivity {
    private Button flipButton;
    private ImageView coinView;
    private int currentSide=1;
    Rotate3dAnimation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);
        flipButton=findViewById(R.id.flipButton);
        coinView=findViewById(R.id.coinView);
        final MediaPlayer tossSound=MediaPlayer.create(CoinFlip.this,R.raw.coin_flip);
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSide==1)
                    animation=new Rotate3dAnimation(coinView,R.drawable.heads,R.drawable.tails,0, 180, 0, 0, 0, 0);
                else
                    animation=new Rotate3dAnimation(coinView,R.drawable.tails,R.drawable.heads,0, 180, 0, 0, 0, 0);
                animation.setDuration(110);
                animation.setInterpolator(new LinearInterpolator());
                int rand=(int)(Math.random()*2);
                tossSound.start();
                if(rand==1){
                    currentSide=1;
                    animation.setRepeatCount(5);
                    coinView.setImageResource(R.drawable.heads);
                }
                else{
                    currentSide=0;
                    animation.setRepeatCount(6);
                    coinView.setImageResource(R.drawable.tails);
                }
                coinView.startAnimation(animation);
            }
        });
    }
}