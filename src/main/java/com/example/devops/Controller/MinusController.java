package com.example.devops.Controller;

import com.example.devops.Service.PlusMinusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class MinusController {

    private final PlusMinusService plusMinusService;

    public MinusController(PlusMinusService plusMinusService) {
        this.plusMinusService = plusMinusService;
    }

    @GetMapping("/minus")
    public String minusPage(Model model) {
        int number1 = plusMinusService.randomNum(19);
        int number2 = plusMinusService.randomNum(19);
        List<Integer> shuffledList = plusMinusService.generateAnswers(number1, "-" , number2);

        model.addAttribute("num1", number1);

        model.addAttribute("num2", number2);

        model.addAttribute("answers", shuffledList);
        model.addAttribute("correctAnswer", number1 - number2);
        model.addAttribute("turns", plusMinusService.getTurns());
        model.addAttribute("result", plusMinusService.getScore());

        return "minus";
    }

    @PostMapping("/checkMinusAnswer")
    public String checkAnswer(@RequestParam("correctAnswer") int correctAnswer,
                       @RequestParam("clickedAnswer") int clickedAnswer,
                       RedirectAttributes redirectAttributes) {

        if (correctAnswer == clickedAnswer) {
            plusMinusService.incrementScore();
        }
        redirectAttributes.addFlashAttribute("result", plusMinusService.getScore());

        return "redirect:/minus";
    }

    @PostMapping("/clearAllMinus")
    public String clearScoreBoard(RedirectAttributes redirectAttributes) {
        plusMinusService.resetScoreBoard();
        redirectAttributes.addFlashAttribute("result", 0);
        redirectAttributes.addFlashAttribute("turns", 0);

        return "redirect:/minus";
    }


}
