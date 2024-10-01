package com.example.devops.Service;

import com.example.devops.feature.MyFeatures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.togglz.core.manager.FeatureManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EvenOrOddServiceTest {

    private EvenOrOddService service;
    private FeatureManager featureManager;

    @BeforeEach
    void setUp() {
        featureManager = Mockito.mock(FeatureManager.class);
        service = new EvenOrOddService(featureManager);
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
    void testRandomIntegerWhenFeatureActive() {
        when(featureManager.isActive(MyFeatures.SET_RANDOM_NUMBER)).thenReturn(true);
        int randomInt = service.randomInteger();

        assertTrue(randomInt >= 1 && randomInt <= 10);
    }

    @Test
    void testRandomIntegerWhenFeatureInactive() {
        when(featureManager.isActive(MyFeatures.SET_RANDOM_NUMBER)).thenReturn(false);
        int randomInt = service.randomInteger();

        assertTrue(randomInt >= 1 && randomInt <= 10000);
    }

}