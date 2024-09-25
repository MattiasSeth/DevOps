package com.example.devops.Controller;

import com.example.devops.Service.PlusMinusService;
import org.junit.jupiter.api.AfterEach;
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

class MinusControllerTest {
    @Mock
    private PlusMinusService plusMinusService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private MinusController minusController;

    private AutoCloseable mocks;

    @BeforeEach
    public void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
    }

    @Test
    public void testMinusPage() {
        when(plusMinusService.randomNum(19)).thenReturn(10, 5);
        when(plusMinusService.generateAnswers(10, "-", 5)).thenReturn(Arrays.asList(5, 7, 8));
        when(plusMinusService.getTurns()).thenReturn(1);
        when(plusMinusService.getScore()).thenReturn(0);

        String viewName = minusController.minusPage(model);

        verify(model).addAttribute("num1", 10);
        verify(model).addAttribute("num2", 5);
        verify(model).addAttribute("answers", Arrays.asList(5, 7, 8));
        verify(model).addAttribute("correctAnswer", 5);
        verify(model).addAttribute("turns", 1);
        verify(model).addAttribute("result", 0);

        assertEquals("minus", viewName);
    }


    @Test
    public void testCheckAnswer() {

        when(plusMinusService.getScore()).thenReturn(1);

        String viewName = minusController.checkAnswer(5, 5, redirectAttributes);

        verify(plusMinusService).incrementScore();
        verify(redirectAttributes).addFlashAttribute("result", 1);

        assertEquals("redirect:/minus", viewName);
    }

    @Test
    public void testClearScoreBoard() {
        String viewName = minusController.clearScoreBoard(redirectAttributes);

        verify(plusMinusService).resetScoreBoard();
        verify(redirectAttributes).addFlashAttribute("result", 0);
        verify(redirectAttributes).addFlashAttribute("turns", 0);

        assertEquals("redirect:/minus", viewName);
    }

}