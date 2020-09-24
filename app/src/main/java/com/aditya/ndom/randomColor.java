package com.aditya.ndom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class randomColor extends AppCompatActivity {
    private TextView colorCodeText;
    private Button randColorButton;
    private ConstraintLayout colorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_color);
        Toolbar toolbar=findViewById(R.id.randomColorToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Random Color Generator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        randColorButton=findViewById(R.id.randColorButton);
        colorCodeText=findViewById(R.id.colorCodeText);
        colorLayout=findViewById(R.id.colorLayout);
        final Animation animation= AnimationUtils.loadAnimation(randomColor.this,R.anim.flip);
        randColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random obj=new Random();
                int rand_num=obj.nextInt(0xffffff + 1);
                String colorCode=String.format("#%06x", rand_num);
                colorCodeText.startAnimation(animation);
                colorCodeText.setText(colorCode);
                colorLayout.setBackgroundColor(Color.parseColor(colorCode));
            }
        });
        colorCodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text",colorCodeText.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(randomColor.this,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}