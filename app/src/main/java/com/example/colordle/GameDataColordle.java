package com.example.colordle;

import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;

public class GameDataColordle implements GameInterfaceColordle {
    private static GameDataColordle gameInstance;
    private final String answerColordle2; //for colordle 2
    private final String[] answerColordle; //for colordle 1
    private HashMap<Integer, Integer> prevGuesses; //for Colordle 1
    private int tries;

    private GameDataColordle() {
        String hex = String.format("#%06x", new Random().nextInt(0xffffff + 1)).substring(1).toUpperCase();
        this.answerColordle2 = hex;
        this.answerColordle = hex.split("");
        this.tries = 6;
        this.prevGuesses = new HashMap<>();
    }

    public static GameDataColordle getInstance() {
        if (gameInstance == null) {
            gameInstance = new GameDataColordle();
        }
        return gameInstance;
    }

    public static GameDataColordle getOnlyInstance() { return gameInstance;}

    public void resetInstance() {
        gameInstance = null;
    }

    @Override // used to check data when doing front end tests
    public void checkData() {
        System.out.println(prevGuesses);
        System.out.println("\n");
        System.out.println(Arrays.toString((answerColordle)));
    }

    public String getAnswerColordle2() {
        return answerColordle2;
    }

    public String[] getAnswerColordle() {
        return answerColordle;
    }

    public HashMap<Integer, Integer> getPrevGuesses() {
        return prevGuesses;
    }

    public void setPrevGuesses(int key, int value) {
        this.prevGuesses.put(key, value);
    }

    public int getTries() { return tries; }

    @Override
    public int[] checkGuess(String[] currGuess) {
        //0 = wrong, 1 = wrong spot, 2 = correct spot
        tries -= 1;
        //check if null
        if (currGuess == null || answerColordle == null) {
            return null;
        }

        //instantiate results
        int [] results = new int[currGuess.length];

        for (int i=0; i < currGuess.length; i++) {
            //fill results
            results[i] = (currGuess[i].toUpperCase().equals(answerColordle[i])) ? 2 : (Arrays.asList(answerColordle).contains(currGuess[i].toUpperCase()) ? 1 : 0);
            //save guess as prev guess
            if (tries == 5) {
                prevGuesses.put(i, results[i]);
            }
        }

        //results is array ex: {0,1,2,0,0,0}
        return results;
    }

    @Override
    public boolean checkGuess2(String hexColor) {
        return hexColor.equals(answerColordle2);
    }
}
