package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ColordleGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colordle_game);
    }

    @Override
    public void onStart() {
        super.onStart();
        //creates game Instance
        GameDataColordle gameInstance = new GameDataColordle();
        //Finds image id
        ImageView image = (ImageView) findViewById(R.id.colorAnswer);
        //sets image color to answer color
        image.setBackgroundColor(Color.parseColor(gameInstance.getAnswerColordle2()));
    }

    public void onBtnClickMainPage(View view) {
        //button to go back to main page (temp)
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onBtnClickGuess(View view) {
        //When guess button is clicked
        EditText letter1 = (EditText) findViewById(R.id.editText1_1);
        EditText letter2 = (EditText) findViewById(R.id.editText1_2);
        EditText letter3 = (EditText) findViewById(R.id.editText1_3);
        EditText letter4 = (EditText) findViewById(R.id.editText1_4);
        EditText letter5 = (EditText) findViewById(R.id.editText1_5);
        EditText letter6 = (EditText) findViewById(R.id.editText1_6);

        if (letter1 == null || letter2 == null || letter3 == null || letter4 == null || letter5 == null || letter6 == null) {
            return;
        }
    }
}