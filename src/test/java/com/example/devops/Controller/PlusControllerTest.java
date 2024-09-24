package com.example.devops.Controller;
import com.example.devops.Service.PlusMinusService;
import com.example.devops.Controller.PlusController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlusControllerTest {


    @Mock
    private PlusMinusService plusMinusService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private PlusController plusController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlusPage() {

        when(plusMinusService.randomNum(19)).thenReturn(7, 3);
        when(plusMinusService.generateAnswers(7, "+", 3)).thenReturn(Arrays.asList(10, 12, 13));
        when(plusMinusService.getTurns()).thenReturn(1);
        when(plusMinusService.getScore()).thenReturn(0);

        String viewName = plusController.plusPage(model);

        verify(model).addAttribute("num1", 7);
        verify(model).addAttribute("num2", 3);
        verify(model).addAttribute("answers", Arrays.asList(10, 12, 13));
        verify(model).addAttribute("correctAnswer", 10);
        verify(model).addAttribute("turns", 1);
        verify(model).addAttribute("result", 0);

        assertEquals("plus", viewName);
    }

    @Test
    public void CheckAnswer() {
        when(plusMinusService.getScore()).thenReturn(1);

        String viewName = plusController.checkAnswer(10, 10, redirectAttributes);

        verify(plusMinusService).incrementScore();
        verify(redirectAttributes).addFlashAttribute("result", 1);

        assertEquals("redirect:/plus", viewName);
    }

    @Test
    public void testClearScoreBoard() {
        String viewName = plusController.clearScoreBoard(redirectAttributes);

        verify(plusMinusService).resetScoreBoard();
        verify(redirectAttributes).addFlashAttribute("result", 0);
        verify(redirectAttributes).addFlashAttribute("turns", 0);

        assertEquals("redirect:/plus", viewName);
    }
}