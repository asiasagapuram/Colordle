package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO need to implement page switching

        //IDEA FOR IMPLEMENTATION OF CLASSES
        //on main page select either colordle or colordle2
        //on page entry of colordle, instantiates GameDataColordle class
        //Answer is auto generated upon class instantiation
        //person enters letters and numbers for guess
        //presses guess, grabs guess data
        //guess button calls checkGuess method
        //checkGuess method returns a list of values [0,1,2,0,0,1]
        //0 = totally wrong, 1 = wrong place in answer, 2 = correct both
        //display prevGuesses.
        //when guessed or failed display popup and go back to button to go back to main menu
        // COLORDLE 2
        //

    }

    public void onBtnClickColordle(View view) {
        startActivity(new Intent(this, ColordleGame.class));
        finish();
    }

    public void onBtnClickColordle2(View view) {
        startActivity(new Intent(this, Colordle2Game.class));
        finish();
    }

    public void onBtnClickHelp(View view) {
        startActivity(new Intent(this, Help.class));
        finish();
    }
}