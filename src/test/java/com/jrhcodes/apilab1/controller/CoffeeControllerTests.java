package com.jrhcodes.apilab1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class CoffeeControllerTests {
    @Autowired
    private MockMvc mockMvcController;

    @Test
    public void testGetHome() throws Exception {
        String expectedContent = "I like coffee!";
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/coffeelover"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent
                ));
    }

    @Test
    public void testGetDefault() throws Exception {
        String expectedContent = "latte";
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/coffee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedContent));

    }

    @Test
    public void testGeCoffee() throws Exception {
        final String expectedContent = "lapsang souchong";
        final String uriString = String.format("/coffee?name=%s", expectedContent);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get(uriString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedContent));
    }
}