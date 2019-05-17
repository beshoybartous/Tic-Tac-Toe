package com.android.thegeekers.tictactoc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
public class Welcome_Activity extends AppCompatActivity {
    private int splachTime=1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Welcome=new Intent(Welcome_Activity.this
                        ,MainActivity.class);
                startActivity(Welcome);
                finish();
            }
        },splachTime);
    }
}
