package com.example.devops.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.Random;

@Controller
public class EvenOrOddController {

    @GetMapping("/oddOrEven")
    public String oddOrEven(Model model, HttpSession session) {
        int randomInt = randomInteger();
        model.addAttribute("randomInt", randomInt);
        session.setAttribute("score", 0);

        model.addAttribute("score", session.getAttribute("score"));
        return "oddoreven";
    }
//Test temp test temp
    @PostMapping("/submitGuess")
    public String submitGuess(@RequestParam("guess") String guess,
                              @RequestParam("randomInt") int randomInt,
                              Model model, HttpSession session) {
        boolean isCorrect = checkGuess(guess, randomInt);

        Integer score = (Integer) session.getAttribute("score");
        if (isCorrect) {
            score += 1;
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }

        session.setAttribute("score", score);
        model.addAttribute("score", score);
        int newRandomInt = randomInteger();
        model.addAttribute("randomInt", newRandomInt);
        return "oddoreven";
    }

    public boolean checkGuess (String guess, int randomInt){
        boolean checkEven = randomInt % 2 == 0;
        return checkEven && guess.equals("even") || (!checkEven && guess.equals("odd"));
    }

    public int randomInteger() {
        Random random = new Random();
        return random.nextInt(10000) + 1;
    }


}
