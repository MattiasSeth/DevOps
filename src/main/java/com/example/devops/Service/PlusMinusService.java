package com.example.devops.Service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlusMinusService {
    @Getter
    int score = 0;
    int turns = 0;
    Set<Integer> answers = new LinkedHashSet<>();
    public int randomNum(int x) {
        Random random = new Random();
        return random.nextInt(x) + 1;
    }

    public List<Integer> generateAnswers(int num1, String mathSymbol, int num2) {
        int correctAnswer;

        if (Objects.equals(mathSymbol, "+")){
            correctAnswer = num1 + num2;
        }
        else {
            correctAnswer = num1 - num2;

        }
        answers.add(correctAnswer);

        while (answers.size() < 3) {
            int numbers = randomNum(5);
            answers.add(correctAnswer + numbers);
        }

        List<Integer> shuffledList = new ArrayList<>(answers);
        Collections.shuffle(shuffledList);
        answers.clear();
        return shuffledList;
    }

    public int getTurns() {
        return turns++;
    }

    public void incrementScore() {
        score++;
    }

    public void resetScoreBoard() {
        score = 0;
        turns = 0;
    }
}
