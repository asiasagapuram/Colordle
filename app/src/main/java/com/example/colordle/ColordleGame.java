package com.example.colordle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private EditText hint1;
    private EditText hint2;
    private EditText hint3;
    private EditText hint4;
    private EditText hint5;
    private EditText hint6;
    private Boolean lastBox;
    private String validChars;
    private View popupView;
    private PopupWindow popupWindow;
    private EditText[] hints;
    private EditText[] guesses;
    private String[] guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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

    @Override
    public void onStop() {
        super.onStop();
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
        hint1 = findViewById(R.id.hint1);
        hint2 = findViewById(R.id.hint2);
        hint3 = findViewById(R.id.hint3);
        hint4 = findViewById(R.id.hint4);
        hint5 = findViewById(R.id.hint5);
        hint6 = findViewById(R.id.hint6);
        guessBtn = findViewById(R.id.colorGuess1);
        validChars = "0123456789ABCDEFabcdef";

        updateVars();

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        popupWindow = new PopupWindow(popupView, width, height, false);

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
        gameInstance = GameDataColordle.getInstance();
        //sets image color to answer color
        Drawable background = imageView.getBackground().mutate();
        System.out.println(gameInstance.getAnswerColordle2());
        background.setTint(Color.parseColor("#"+gameInstance.getAnswerColordle2()));
    }

    public void onBtnClickMainPage(View view) {
        //button to go back to main page (temp)
        popupWindow.dismiss();
        gameInstance.resetInstance();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onBtnClickGuess(View view) {
        updateVars();
        //Check if all boxes are filled
        if (!lastBox) {
            Toast.makeText(ColordleGame.this, "All boxes must be filled", Toast.LENGTH_SHORT).show();
            return;
        }

        //Check for null values
        if (Arrays.asList(guess).contains("")) {
            return;
        }

        //CheckGuess
        int[] result = gameInstance.checkGuess(guess);

        //show Hints
        showHints();

        //Animate Squares
        for (int i = 0; i < result.length; i++) {
            //animate guess squares
            flip(guesses[i], result[i]);
            //updates hint squares
            updateHints(gameInstance.getPrevGuesses().get(i),hints[i], guess[i], result[i], i);
        }

        //setGuessButton color
        setBtnColor("#"+String.join("", guess));

        //check if guess is correct
        if (Arrays.stream(result).allMatch(s -> s == 2)) {
            //Guess is Correct
            winGame(view);
            return;
        }

        //lower ui try count
        EditText tries = findViewById(R.id.triesNum);
        tries.setText(String.valueOf(gameInstance.getTries()));

        //check if all tries are up
        if (gameInstance.getTries() == 0) {
            loseGame(view);
            return;
        }
    }

    public void showHints() {
        hint1.setVisibility(View.VISIBLE);
        hint2.setVisibility(View.VISIBLE);
        hint3.setVisibility(View.VISIBLE);
        hint4.setVisibility(View.VISIBLE);
        hint5.setVisibility(View.VISIBLE);
        hint6.setVisibility(View.VISIBLE);
    }

    public void updateVars() {
        //Creates array of hints
        hints = new EditText[]{
                hint1,
                hint2,
                hint3,
                hint4,
                hint5,
                hint6
        };

        //Create array of guesses
        guesses = new EditText[]{
                letter1,
                letter2,
                letter3,
                letter4,
                letter5,
                letter6};

        //Create array of letters
        guess = new String[]{
                letter1.getText().toString(),
                letter2.getText().toString(),
                letter3.getText().toString(),
                letter4.getText().toString(),
                letter5.getText().toString(),
                letter6.getText().toString()};
    }

    public void updateHints(int prevType, EditText hint, String letter, int type, int spot) {
        if (type >= prevType) {
            gameInstance.setPrevGuesses(spot, type);
            flip(hint, type);
            hint.setText(letter);
        }
    }

    public void setBtnColor(String color) {
        Drawable background = guessBtn.getBackground().mutate();
        if (!isLight(color)) {
            guessBtn.setTextColor(getColor(R.color.white));
        } else {
            guessBtn.setTextColor(getColor(R.color.black));
        }
        background.setTint(Color.parseColor(color));
    }

    public boolean isLight(String color) {
        int rgb = Color.parseColor(color);
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb >> 0) & 0xff;
        return ((0.2126*r + 0.7152*g + 0.0722*b) >= 128);
    }

    public void setColor(EditText letter, int type) {
        Drawable background = letter.getBackground().mutate();
        if (type == 2) {
            background.setTint(getColor(R.color.correct));
        } else if (type == 1) {
            background.setTint(getColor(R.color.inplace));
        } else {
            background.setTint(getColor(R.color.wrong));
        }
    }

    public void flip(EditText letter, int type) {
        final ObjectAnimator front = ObjectAnimator.ofFloat(letter, "rotationX", 180f, 0f);
        front.setInterpolator(new AccelerateDecelerateInterpolator());
        front.setDuration(400);
        front.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setColor(letter, type);
            }
        });
        front.start();
    }

    public void showPopup(View view, Boolean win) {
        //disable everything else
        letter6.clearFocus();
        guessBtn.setEnabled(false);
        letter6.setEnabled(false);
        Button back = findViewById(R.id.button);
        back.setEnabled(false);

        TextView text = popupView.findViewById(R.id.popupText);
        if (win) {
            text.setText(R.string.win);
        } else {
            text.setText(R.string.lose);
        }

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    public void winGame(View view) {
        //Won the game
        final Handler handler = new Handler();
        handler.postDelayed(() -> showPopup(view, true),1000);
    }

    public void loseGame(View view) {
        //Lost the game
        final Handler handler = new Handler();
        handler.postDelayed(() -> showPopup(view, false),1000);
    }

}