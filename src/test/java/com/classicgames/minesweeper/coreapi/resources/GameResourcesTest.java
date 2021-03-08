package com.classicgames.minesweeper.coreapi.resources;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameResourcesTest {

    @Autowired
    private GameResources resource;

    @Test
    public void contextLoads() {
        Assertions.assertThat(resource).isNotNull();
    }

}
