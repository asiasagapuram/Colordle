package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Arrays;

public class ColordleGame extends AppCompatActivity {

    private GameDataColordle gameInstance;
    private Button guessBtn;
    private ImageView imageView;
    private EditText letter1;
    private EditText letter2;
    private EditText letter3;
    private EditText letter4;
    private EditText letter5;
    private EditText letter6;
    private Boolean lastBox;
    private String validChars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colordle_game);
        //initializes Views
        initializeViews();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Starts the Game
        startGame();
    }

    private void initializeViews() {
        lastBox = false;
        imageView = findViewById(R.id.colorAnswer);
        letter1 = findViewById(R.id.editText1);
        letter2 = findViewById(R.id.editText2);
        letter3 = findViewById(R.id.editText3);
        letter4 = findViewById(R.id.editText4);
        letter5 = findViewById(R.id.editText5);
        letter6 = findViewById(R.id.editText6);
        guessBtn = findViewById(R.id.colorGuess1);
        validChars = "0123456789ABCDEFabcdef";

        letter1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!validChars.contains(letter1.getText())) {
                    editable.replace(0, editable.length(),"");
                    Toast.makeText(ColordleGame.this, "Only 0-9 and A-F is allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (letter1.length() == 1) {
                    letter2.setEnabled(true);
                    letter2.requestFocus();
                    letter1.setEnabled(false);
                }
            }
        });

        letter2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!validChars.contains(letter2.getText())) {
                    letter2.removeTextChangedListener(this);
                    editable.replace(0, editable.length(),"");
                    letter2.addTextChangedListener(this);
                    Toast.makeText(ColordleGame.this, "Only 0-9 and A-F is allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (letter2.length() == 0) {
                    letter1.setEnabled(true);
                    letter1.requestFocus();
                    letter2.setEnabled(false);
                } else if (letter2.length() == 1) {
                    letter3.setEnabled(true);
                    letter3.requestFocus();
                    letter2.setEnabled(false);
                }
            }
        });

        letter3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!validChars.contains(letter3.getText())) {
                    letter3.removeTextChangedListener(this);
                    editable.replace(0, editable.length(),"");
                    letter3.addTextChangedListener(this);
                    Toast.makeText(ColordleGame.this, "Only 0-9 and A-F is allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (letter3.length() == 0) {
                    letter2.setEnabled(true);
                    letter2.requestFocus();
                    letter3.setEnabled(false);
                } else if (letter3.length() == 1) {
                    letter4.setEnabled(true);
                    letter4.requestFocus();
                    letter3.setEnabled(false);
                }
            }
        });

        letter4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!validChars.contains(letter4.getText())) {
                    letter4.removeTextChangedListener(this);
                    editable.replace(0, editable.length(),"");
                    letter4.addTextChangedListener(this);
                    Toast.makeText(ColordleGame.this, "Only 0-9 and A-F is allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (letter4.length() == 0) {
                        letter3.setEnabled(true);
                        letter3.requestFocus();
                        letter4.setEnabled(false);
                    } else if (letter4.length() == 1) {
                        letter5.setEnabled(true);
                        letter5.requestFocus();
                        letter4.setEnabled(false);
                    }
                }
            }
        });
        //sets auto switching between boxes when typing and deleting (convenience)
        letter5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!validChars.contains(letter5.getText())) {
                    letter5.removeTextChangedListener(this);
                    editable.replace(0, editable.length(),"");
                    letter5.addTextChangedListener(this);
                    Toast.makeText(ColordleGame.this, "Only 0-9 and A-F is allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (letter5.length() == 0) {
                        letter4.setEnabled(true);
                        letter4.requestFocus();
                        letter5.setEnabled(false);
                    } else if (letter3.length() == 1) {
                        letter6.setEnabled(true);
                        letter6.requestFocus();
                        letter5.setEnabled(false);
                    }
                }
            }
        });

        letter6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!validChars.contains(letter6.getText())) {
                    letter6.removeTextChangedListener(this);
                    editable.replace(0, editable.length(),"");
                    letter6.addTextChangedListener(this);
                    Toast.makeText(ColordleGame.this, "Only 0-9 and A-F is allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (letter6.length() == 0) {
                        letter5.setEnabled(true);
                        letter5.requestFocus();
                        letter6.setEnabled(false);
                        lastBox = false;
                    } else if (letter3.length() == 1) {
                        lastBox = true;
                    }
                }
            }
        });

    }

    private void startGame() {
        //Creates game instance
        gameInstance = new GameDataColordle();
        //sets image color to answer color
        Drawable background = imageView.getBackground();
        System.out.println(gameInstance.getAnswerColordle2());
        background.setTint(Color.parseColor("#"+gameInstance.getAnswerColordle2()));
    }

    public void onBtnClickMainPage(View view) {
        //button to go back to main page (temp)
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onBtnClickGuess(View view) {
        //set new letters?
        System.out.println("These are the current letters");
        System.out.println(letter1.getText());
        System.out.println(letter2.getText());
        System.out.println(letter3.getText());
        System.out.println(letter4.getText());
        System.out.println(letter5.getText());
        System.out.println(letter6.getText());

        System.out.println("Are all boxes filled?");
        //Check if all boxes are filled
        if (!lastBox) {
            System.out.println("No");
            return;
        }
        System.out.println("Yes");

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
        Drawable background = letter.getBackground();
        if (type == 2) {
            //correct
            flip(view, letter);
            background.setTint(getColor(R.color.correct));
        } else if (type == 1) {
            //Incorrect Position
            flip(view, letter);
            background.setTint(getColor(R.color.inplace));
        } else {
            flip(view, letter);
            background.setTint(getColor(R.color.wrong));
        }
    }

    public void flip(View view, EditText letterFront) {
        final ObjectAnimator front = ObjectAnimator.ofFloat(letterFront, "rotationX", 180f, 0f);
        final ObjectAnimator back = ObjectAnimator.ofFloat(letterFront, "rotationX", 0f, 90f);
        front.setInterpolator(new AccelerateDecelerateInterpolator());
        front.setDuration(500);
        front.start();
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