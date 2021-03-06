package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private Button play1;
    private Button play2;
    private Button help;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        //calls initializer
        initializer();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    public void initializer() {
        //Initializes Variables
        title = findViewById(R.id.mainTitle);
        play1 = findViewById(R.id.playColordle);
        play2 = findViewById(R.id.playColordle2);
        help = findViewById(R.id.Help);

        startAnimations();

        //plays music for title screen
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.twinkle);
        mediaPlayer.start();
    }

    public void startAnimations() {
        //Starts the Animations
        Animation floating1 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        Animation floating2 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        Animation floating3 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        Animation floating4 = AnimationUtils.loadAnimation(getApplication(), R.anim.floating);
        floating2.setStartOffset(200);
        floating3.setStartOffset(400);
        floating4.setStartOffset(600);
        title.startAnimation(floating1);
        play1.startAnimation(floating2);
        play2.startAnimation(floating3);
        help.startAnimation(floating4);
    }

    public void onBtnClickColordle(View view) {
        //Switch activity page
        startActivity(new Intent(this, ColordleGame.class));
        finish();
    }

    public void onBtnClickColordle2(View view) {
        //Switch activity page
        startActivity(new Intent(this, Colordle2Game.class));
        finish();
    }

    public void onBtnClickHelp(View view) {
        //Switch activity page
        startActivity(new Intent(this, Help.class));
        finish();
    }
}