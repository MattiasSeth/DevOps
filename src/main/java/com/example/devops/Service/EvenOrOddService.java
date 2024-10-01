package com.example.devops.Service;

import com.example.devops.feature.MyFeatures;
import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

import java.util.Random;

@Service
public class EvenOrOddService {

    final FeatureManager featureManager;


    public EvenOrOddService(FeatureManager featureManager){
        this.featureManager = featureManager;
    }


    public boolean checkGuess (String guess, int randomInt){
        boolean checkEven = randomInt % 2 == 0;
        return checkEven && guess.equals("even") || (!checkEven && guess.equals("odd"));
    }

    public int randomInteger() {
        Random random = new Random();

        if (featureManager.isActive(MyFeatures.SET_RANDOM_NUMBER)) {
            return random.nextInt(10) + 1;
        } else {
            return random.nextInt(10000) + 1;
        }

    }
}
