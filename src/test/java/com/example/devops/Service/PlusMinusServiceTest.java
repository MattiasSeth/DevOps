package com.example.devops.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlusMinusServiceTest {

    private PlusMinusService plusMinusService;

    @BeforeEach
    public void setUp() {
        plusMinusService = new PlusMinusService();
    }

    @Test
    public void testRandomNum() {
        int randomNum = plusMinusService.randomNum(10);
        assertTrue(randomNum >= 1 && randomNum <= 10, "Random number ska vara mellan 1-10");
    }

    @Test
    public void GenerateAnswersPlus() {
        List<Integer> answers = plusMinusService.generateAnswers(3, "+", 2);
        assertEquals(3, answers.size(), "Listan ska innehålla 3 värden");
        assertTrue(answers.contains(5), "Rätt svar ska vara 5");
    }

    @Test
    public void GenerateAnswersMinus() {
        List<Integer> answers = plusMinusService.generateAnswers(8, "-", 3);
        assertEquals(3, answers.size(), "Listan ska innehålla 3 värden");
        assertTrue(answers.contains(5), "Rätt svar ska vara 5");
    }

    @Test
    public void testIncrementScore() {
        plusMinusService.incrementScore();
        assertEquals(1, plusMinusService.getScore(), "Score ska öka med 1");
    }

    @Test
    public void testResetScoreBoard() {
        plusMinusService.incrementScore();
        plusMinusService.getTurns();
        plusMinusService.resetScoreBoard();
        assertEquals(0, plusMinusService.getScore(), "Score ska vara 0");
        assertEquals(0, plusMinusService.getTurns(), "Turns ska vara 0");
    }

    @Test
    public void testTurnIncrement() {
        int initialTurns = plusMinusService.getTurns();
        int nextTurns = plusMinusService.getTurns();
        assertEquals(initialTurns + 1, nextTurns, "Turns ska öka med 1");
    }
}