package com.example.colordle;

import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;

public class GameDataColordle implements GameInterfaceColordle {
    private String answerColordle2; //for colordle 2
    private String[] answerColordle; //for colordle 1
    private HashMap<String, Integer> prevGuesses; //for Colordle 1

    public GameDataColordle() {
        String hex = String.format("#%06x", new Random().nextInt(0xffffff + 1)).substring(1);
        this.answerColordle2 = hex;
        this.answerColordle = hex.split("");
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

    public HashMap<String, Integer> getPrevGuesses() {
        return prevGuesses;
    }

    @Override
    public int[] checkGuess(String[] currGuess) {
        //0 = wrong, 1 = wrong spot, 2 = correct spot

        //check if null
        if (currGuess == null || answerColordle == null) {
            return null;
        }

        //instantiate results
        int [] results = new int[currGuess.length];

        for (int i=0; i < currGuess.length; i++) {
            //fill results
            results[i] = (currGuess[i].equals(answerColordle[i])) ? 2 : (Arrays.asList(answerColordle).contains(currGuess[i]) ? 1 : 0);
            //fill with previous guesses
            if (!prevGuesses.containsKey(currGuess[i])) {
                prevGuesses.put(currGuess[i], results[i]);
            }
        } // System.out.println(Arrays.toString(colorCode));

        //results is array ex: {0,1,2,0,0,0}
        return results;
    }

    @Override
    public boolean checkGuess2(String hexColor) {
        return hexColor.equals(answerColordle2);
    }
}
