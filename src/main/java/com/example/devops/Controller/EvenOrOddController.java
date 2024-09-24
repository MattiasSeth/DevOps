package com.example.devops.Controller;

import com.example.devops.Service.EvenOrOddService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class EvenOrOddController {

    private final EvenOrOddService evenOrOddService;

    public EvenOrOddController(EvenOrOddService evenOrOddService){
        this.evenOrOddService = evenOrOddService;
    }

    @GetMapping("/oddOrEven")
    public String oddOrEven(Model model, HttpSession session) {
        int randomInt = evenOrOddService.randomInteger();
        model.addAttribute("randomInt", randomInt);
        session.setAttribute("score", 0);

        model.addAttribute("score", session.getAttribute("score"));
        return "oddoreven";
    }

    @PostMapping("/submitGuess")
    public String submitGuess(@RequestParam("guess") String guess,
                              @RequestParam("randomInt") int randomInt,
                              Model model, HttpSession session) {

        boolean isCorrect = evenOrOddService.checkGuess(guess, randomInt);
        Integer score = (Integer) session.getAttribute("score");

        if (isCorrect) {
            score += 1;
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }

        session.setAttribute("score", score);
        model.addAttribute("score", score);
        int newRandomInt = evenOrOddService.randomInteger();
        model.addAttribute("randomInt", newRandomInt);
        return "oddoreven";
    }

}
