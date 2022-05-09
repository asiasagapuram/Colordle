package com.example.colordle;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;

public class Colordle2Game extends AppCompatActivity {

    private GameDataColordle gameInstance;
    private View popupView;
    private PopupWindow popupWindow;
    private Button back;
    //add private variables here for views, buttons and such

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_colordle2_game);

        initializer();
    }

    public void onBtnClickMainPage(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Starts the Game
        startGame();
    }

    public void initializer() {
        //set variables
        back = findViewById(R.id.button);

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        popupWindow = new PopupWindow(popupView, width, height, false);
    }

    public void startGame() {
        //Gets and initializes Game Instance
        gameInstance = GameDataColordle.getInstance();

        //TODO: set Text to hex code answer, answer is here (string: 123456) : gameInstance.getAnswerColordle2()
        //answer does not have # in it ex #123456


        //TODO: generate random colors of buttons here
        //Example generated hex, add .substring(1) to get rid of #
        String genHex = String.format("#%06x", new Random().nextInt(0xffffff + 1)).toUpperCase();

    }

    public void onBtnClickGuessed(View view) {
        //TODO: set colored buttons to run this function when pressed


        //TODO: get button color/string data

        //TODO: check guess with answer
        String guess = "#123456"; //replace this

        Boolean result = gameInstance.checkGuess2(guess);

        //logic for what happens if wrong and right
        if (result) {
            // if true
            winGame(view);
        } else {
            // do things
        }

        if (gameInstance.getTries() <= 0) {
            loseGame(view);
            return;
        }
    }

    public void flip(EditText letter, int type) {
        //flipping animation
        final ObjectAnimator front = ObjectAnimator.ofFloat(letter, "rotationX", 180f, 0f);
        front.setInterpolator(new AccelerateDecelerateInterpolator());
        front.setDuration(400);
        //TODO: add listener for effects or whatever for after flip

        /*front.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setColor(letter, type);
            }
        });*/

        front.start();
    }

    public void showPopup(View view, Boolean win) {
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
        //TODO: add more disables for each button and all.

        final Handler handler = new Handler();
        handler.postDelayed(() -> showPopup(view, true),1000);
    }

    public void loseGame(View view) {
        //Lost the game
        //TODO: add more disables for each button and all.
        final Handler handler = new Handler();
        handler.postDelayed(() -> showPopup(view, false),1000);
    }


}