package com.example.devops.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EvenOrOddControllerTest {

    private EvenOrOddController controller;

    @BeforeEach
    void setUp() {
        controller = new EvenOrOddController();
    }

    @Test
    void testCheckGuessEven() {
        assertTrue(controller.checkGuess("even", 2));
    }

    @Test
    void testCheckGuessOdd() {
        assertTrue(controller.checkGuess("odd", 3));
    }

    @Test
    void testCheckGuessWrongEven() {
        assertFalse(controller.checkGuess("odd", 4));
    }

    @Test
    void testCheckGuessWrongOdd() {
        assertFalse(controller.checkGuess("even", 5));
    }

    @Test
    void testRandomIntegerInRange() {
        int randomInt = controller.randomInteger();
        assertTrue(randomInt >= 1 && randomInt <= 10000);
    }
}
