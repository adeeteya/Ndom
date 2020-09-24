package com.aditya.ndom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class MainActivity2 extends AppCompatActivity {
    private TextView numberView;
    private EditText minEditText,maxEditText;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar=findViewById(R.id.randomNoToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Generate Random Number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        minEditText=findViewById(R.id.minEditText);
        maxEditText=findViewById(R.id.maxEditText);
        submitButton=findViewById(R.id.submitButton);
        numberView=findViewById(R.id.numberView);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minText,maxText;
                boolean isInteger=true;
                minText=minEditText.getText().toString();
                maxText=maxEditText.getText().toString();
                for(int i=0;i<minText.length();i++){
                    if(minText.charAt(i)=='.')
                        isInteger=false;
                }
                for(int i=0;i<maxText.length();i++){
                    if(maxText.charAt(i)=='.')
                        isInteger=false;
                }
                if(isInteger) {
                    int min,max,rand;
                    min = Integer.parseInt(minText);
                    max = Integer.parseInt(maxText);
                    if(min>max){
                        StyleableToast.makeText(MainActivity2.this, "Minimum cannot be greater than maximum!", R.style.errorToast).show();
                        return;
                    }
                    rand = (int) (Math.random() * (max - min)) + min;
                    numberView.setText(Integer.toString(rand));
                }
                else{
                    double min,max,rand;
                    min=Double.parseDouble(minText);
                    max=Double.parseDouble(maxText);
                    if(min>max){
                        StyleableToast.makeText(MainActivity2.this, "Minimum cannot be greater than maximum!", R.style.errorToast).show();
                        return;
                    }
                    rand=(Math.random()*(max-min))+min;
                    rand=Math.round(rand*Math.pow(10,2))/(Math.pow(10,2));
                    numberView.setText(Double.toString(rand));
                }
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
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}