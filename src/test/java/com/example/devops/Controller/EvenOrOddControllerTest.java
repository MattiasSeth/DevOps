package com.example.devops.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EvenOrOddControllerTest {

    @Autowired
    private EvenOrOddController controller;

    @Test
    public void contextLoads(){
        assertThat(controller).isNotNull();
    }
}