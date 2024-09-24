package com.example.devops.Service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class EvenOrOddService {

    public boolean checkGuess (String guess, int randomInt){
        boolean checkEven = randomInt % 2 == 0;
        return checkEven && guess.equals("even") || (!checkEven && guess.equals("odd"));
    }

    public int randomInteger() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }
}
