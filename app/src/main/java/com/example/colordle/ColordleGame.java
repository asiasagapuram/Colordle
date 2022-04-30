package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Arrays;

public class ColordleGame extends AppCompatActivity {

    private GameDataColordle gameInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colordle_game);
    }

    @Override
    public void onStart() {
        super.onStart();
        //creates game Instance
        gameInstance = new GameDataColordle();
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
        //Create array of guesses
        EditText[] guesses = {
                letter1,
                letter2,
                letter3,
                letter4,
                letter5,
                letter6};

        String[] guess = {
                letter1.getText().toString(),
                letter2.getText().toString(),
                letter3.getText().toString(),
                letter4.getText().toString(),
                letter5.getText().toString(),
                letter6.getText().toString()};

        //Check for null values
        if (Arrays.asList(guess).contains("")) {
            return;
        }

        //CheckGuess
        int[] result = gameInstance.checkGuess(guess);

        //Animate Squares
        for (int i = 0; i < result.length; i++) {
            animateSquare(view, guesses[i], result[i]);
        }

        //check if guess is correct
        if (Arrays.stream(result).allMatch(s -> s == 2)) {
            //Guess is Correct
            winGame(view);
            return;
        }

        //check if all tries are up
        if (gameInstance.getTries() == 6) {
            loseGame(view);
        }

        changeObjectPositionDown(view, guesses, result);
    }

    public void animateSquare(View view, EditText letter, int type) {
        if (type == 2) {
            //correct
        } else if (type == 1) {
            //Incorrect Position
        } else {
            //Incorrect
        }
    }

    public void changeObjectPositionDown(View view, EditText[] letters, int[] results) {
        //Changes Position of the object down and sets previous button color

    }

    public void winGame(View view) {
        //Won the game

    }

    public void loseGame(View view) {
        //Lost the game

    }

}