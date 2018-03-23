package com.example.saurabh.tic_tac_to;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class first_page extends AppCompatActivity {
    Handler h= new Handler();
    Animation uptodown,downtoup;
    LinearLayout l3,l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l3.setAnimation(uptodown);
        l4.setAnimation(downtoup);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(first_page.this,second_page.class);
                startActivity(i);
            }
        },2000);
    }
}
