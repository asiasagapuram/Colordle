package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Colordle2Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colordle2_game);
    }

    public void onBtnClickMainPage(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}