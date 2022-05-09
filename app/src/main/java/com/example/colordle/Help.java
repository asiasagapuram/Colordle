package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    private TextView howtoplay;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_help);

        initializer();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void initializer() {

        howtoplay = findViewById(R.id.howtoplay);
        textview1 = findViewById(R.id.textView1);
        textview2 = findViewById(R.id.textView2);
        textview3 = findViewById(R.id.textView3);


        startAnimations();

    }

    public void startAnimations() {

        Animation floating5 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        Animation floating6 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        Animation floating7 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        Animation floating8 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);

        floating6.setStartOffset(200);
        floating7.setStartOffset(400);
        floating8.setStartOffset(600);
        howtoplay.startAnimation(floating5);
        textview1.startAnimation(floating6);
        textview2.startAnimation(floating7);
        textview3.startAnimation(floating8);
    }

    public void onBtnClickMainPage(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}