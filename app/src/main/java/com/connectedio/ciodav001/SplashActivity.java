package com.connectedio.ciodav001;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.connectedio.ciodav001.utils.MyPreferences;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //re routing to next activity with delay.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // Intent i = new Intent(SplashActivity.this, MainActivity.class);
                boolean isLogin= MyPreferences.getInstance(SplashActivity.this).isLogin();
                Intent i=null;
                if(isLogin) {
                     i = new Intent(SplashActivity.this, MainActivity.class);
                }else{
                     i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
