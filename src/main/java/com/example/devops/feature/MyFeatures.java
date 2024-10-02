package com.example.devops.feature;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

public enum MyFeatures implements Feature {

    @Label("Odd or even")
    ODD_EVEN,
    @Label("Set random number")
    SET_RANDOM_NUMBER,
}