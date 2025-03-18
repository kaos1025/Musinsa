package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void 카테고리_등록_성공() throws Exception {
        String requestBody = """
        [
            {
                "categoryName": "상의"
            }
        ]
        """;

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}