package com.example.devops.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvenOrOddServiceTest {

    private EvenOrOddService service;

    @BeforeEach
    void setUp() {
        service = new EvenOrOddService();
    }

    @Test
    void testCheckGuessEven() {
        assertTrue(service.checkGuess("even", 2));
    }

    @Test
    void testCheckGuessOdd() {
        assertTrue(service.checkGuess("odd", 3));
    }

    @Test
    void testCheckGuessWrongEven() {
        assertFalse(service.checkGuess("odd", 4));
    }

    @Test
    void testCheckGuessWrongOdd() {
        assertFalse(service.checkGuess("even", 5));
    }

    @Test
    void testRandomIntegerInRange() {
        int randomInt = service.randomInteger();
        assertTrue(randomInt >= 1 && randomInt <= 10000);
    }

}