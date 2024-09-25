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
public class PlusController {

    private final PlusMinusService plusMinusService;

    public PlusController(PlusMinusService plusMinusService) {
        this.plusMinusService = plusMinusService;
    }

    @GetMapping("/plus")
    public String plusPage(Model model) {
        int num1 = plusMinusService.randomNum(19);
        int num2 = plusMinusService.randomNum(19);
        List<Integer> plusShuffledList = plusMinusService.generateAnswers(num1, "+", num2);

        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("answers", plusShuffledList);
        model.addAttribute("correctAnswer", num1 + num2);
        model.addAttribute("turns", plusMinusService.getTurns());
        model.addAttribute("result", plusMinusService.getScore());

        return "plus";
    }

    @PostMapping("/checkPlusAnswer")
    public String checkAnswer(@RequestParam("correctAnswer") int correctAnswer,
                               @RequestParam("clickedAnswer") int clickedAnswer,
                               RedirectAttributes redirectAttributes) {

        if (correctAnswer == clickedAnswer) {
            plusMinusService.incrementScore();
        }
        redirectAttributes.addFlashAttribute("result", plusMinusService.getScore());

        return "redirect:/plus";
    }

    @PostMapping("/clearAllPlus")
    public String clearScoreBoard(RedirectAttributes redirectAttributes) {
        plusMinusService.resetScoreBoard();
        redirectAttributes.addFlashAttribute("result", 0);
        redirectAttributes.addFlashAttribute("turns", 0);

        return "redirect:/plus";
    }
}
