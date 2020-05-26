package com.hussain.assignment.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hussain.assignment.R;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    private static final int SPLASH_TIME = 4000; //4 seconds

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        launchDashBoard();

    }

    public void launchDashBoard() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mySuperIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_TIME);
    }
}
