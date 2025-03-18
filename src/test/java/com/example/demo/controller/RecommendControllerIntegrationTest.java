package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecommendControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void 카테고리별_최저가_상품_조회_성공() throws Exception {
        mockMvc.perform(get("/recommend/lowestPriceItem"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").exists());
    }

    @Test
    void 최저가_브랜드_조회_성공() throws Exception {
        mockMvc.perform(get("/recommend/lowestPriceBrand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandName").exists());
    }

    @Test
    void 특정_카테고리_최저가_최고가_조회_성공() throws Exception {
        mockMvc.perform(get("/recommend/categoryPrice/{categoryName}", "상의"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lowestPrice").exists())
                .andExpect(jsonPath("$.highestPrice").exists());
    }
}