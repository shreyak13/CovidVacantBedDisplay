package com.example.mytext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class splashScreen extends AppCompatActivity {
TextView appname;
LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appname=findViewById(R.id.appName);
        lottie=findViewById(R.id.lottie);
        appname.animate().translationY(2400).setDuration(2000).setStartDelay(1000);
        lottie.animate().translationX(2400).setDuration(3000).setStartDelay(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(getApplicationContext(),signin.class);
                startActivity(i);
                finish();

            }
        },3000);



    }
}