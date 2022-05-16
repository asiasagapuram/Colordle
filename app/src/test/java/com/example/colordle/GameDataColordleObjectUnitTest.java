package com.example.colordle;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameDataColordleObjectUnitTest {

    @Test
    public void SanityCheckUnitTest() {
        //makes sure Assert works as needed
        Assert.assertEquals(0,0);
        Assert.assertEquals("0", "0");
    }

    @Test
    public void SingleTonUnitTest() {
        //Tests if SingleTon Design Pattern is working as needed
        GameDataColordle gameInstance = GameDataColordle.getInstance();
        assertNotNull(gameInstance);

        //Tests if resetting works for SingleTon
        gameInstance.resetInstance();
        assertNull(gameInstance.getOnlyInstance());
    }

    @Test
    public void CheckGuessUnitTest() {
        GameDataColordle gameInstance = GameDataColordle.getInstance();

        //Data setup
        String[] correctInput = gameInstance.getAnswerColordle();
        int[] correctAnswer = new int[]{
          2,2,2,2,2,2
        };
        String[] wrongInput = new String[]{
                "g","g","g","g","g","g"
        };
        int[] wrongAnswer = new int[]{
                0,0,0,0,0,0
        };

        //check if the correct answer is correct
        int[] correctResult = gameInstance.checkGuess(correctInput);
        Assert.assertArrayEquals(correctResult, correctAnswer);

        //check if the wrong answer is wrong
        int[] wrongResult = gameInstance.checkGuess(wrongInput);
        Assert.assertArrayEquals(wrongAnswer, wrongResult);

        gameInstance.resetInstance();
    }

    @Test
    public void CheckGuess2UnitTest() {
        GameDataColordle gameInstance = GameDataColordle.getInstance();

        //Data setup
        String correctInput = gameInstance.getAnswerColordle2();
        String wrongInput = "gggggg";

        //check if the correct answer is correct
        assertTrue(gameInstance.checkGuess2(correctInput));

        //check if the wrong answer is wrong
        assertFalse(gameInstance.checkGuess2(wrongInput));

        gameInstance.resetInstance();
    }
}
