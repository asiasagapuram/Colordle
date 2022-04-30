package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        //limits characters and auto switches edit text
        EditText letter1 = (EditText) findViewById(R.id.editText1_1);
        EditText letter2 = (EditText) findViewById(R.id.editText1_2);
        EditText letter3 = (EditText) findViewById(R.id.editText1_3);
        EditText letter4 = (EditText) findViewById(R.id.editText1_4);
        EditText letter5 = (EditText) findViewById(R.id.editText1_5);
        EditText letter6 = (EditText) findViewById(R.id.editText1_6);
        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.hashCode() == letter1.hashCode()) {
                    if (editable.toString().length() == 1 && !letter2.isEnabled()){
                        letter1.setEnabled(false);
                        letter2.setEnabled(true);
                        letter2.requestFocus();
                    }
                } else if (editable.hashCode() == letter2.hashCode()) {
                    if (editable.toString().length() == 1 && !letter3.isEnabled()){
                        letter2.setEnabled(false);
                        letter3.setEnabled(true);
                        letter3.requestFocus();
                    } else if (editable.toString().length() == 0 && !letter1.isEnabled()) {
                        letter1.setEnabled(true);
                        letter2.setEnabled(false);
                        letter1.requestFocus();
                    }
                } else if (editable.hashCode() == letter3.hashCode()) {
                    if (editable.toString().length() == 1 && !letter4.isEnabled()){
                        letter3.setEnabled(false);
                        letter4.setEnabled(true);
                        letter4.requestFocus();
                    } else if (editable.toString().length() == 0 && !letter2.isEnabled()) {
                        letter2.setEnabled(true);
                        letter3.setEnabled(false);
                        letter2.requestFocus();
                    }
                } else if (editable.hashCode() == letter4.hashCode()) {
                    if (editable.toString().length() == 1 && !letter5.isEnabled()){
                        letter4.setEnabled(false);
                        letter5.setEnabled(true);
                        letter5.requestFocus();
                    } else if (editable.toString().length() == 0 && !letter3.isEnabled()) {
                        letter3.setEnabled(true);
                        letter4.setEnabled(false);
                        letter3.requestFocus();
                    }
                } else if (editable.hashCode() == letter5.hashCode()) {
                    if (editable.toString().length() == 1 && !letter6.isEnabled()){
                        letter5.setEnabled(false);
                        letter6.setEnabled(true);
                        letter6.requestFocus();
                    } else if (editable.toString().length() == 0 && !letter4.isEnabled()) {
                        letter4.setEnabled(true);
                        letter5.setEnabled(false);
                        letter4.requestFocus();
                    }
                } else if (editable.hashCode() == letter6.hashCode()) {
                    if (editable.toString().length() == 1){
                        letter6.clearFocus();
                    } else if (editable.toString().length() == 0 && !letter5.isEnabled()) {
                        letter5.setEnabled(true);
                        letter6.setEnabled(false);
                        letter5.requestFocus();
                    }
                }
            }
        };
        letter1.addTextChangedListener(textWatcher);
        letter2.addTextChangedListener(textWatcher);
        letter3.addTextChangedListener(textWatcher);
        letter4.addTextChangedListener(textWatcher);
        letter5.addTextChangedListener(textWatcher);
        letter6.addTextChangedListener(textWatcher);
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

        //creates guessed uneditable copies to take the spot of the previous
        //resets and shifts them down
        changeObjectsPositionDown(view, guesses, result);
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

    public void changeObjectsPositionDown(View view, EditText[] letters, int[] results) {
        //Changes Position of the objects down and sets previous button color

    }

    public void winGame(View view) {
        //Won the game

    }

    public void loseGame(View view) {
        //Lost the game

    }

}