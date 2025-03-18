package com.example.demo.controller;
import com.example.demo.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ItemControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void 상품_등록_성공() throws Exception {
        String requestBody = """
        [
            {
                "brandCode": 1,
                "categoryCode": 1,
                "price": 50000
            }
        ]
        """;

        mockMvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void 상품_조회_성공() throws Exception {
        // ✅ 등록된 상품 번호로 조회
        mockMvc.perform(get("/item/{itemNo}", BigInteger.ONE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandName").exists());
    }

    @Test
    void 상품_삭제_성공() throws Exception {
        mockMvc.perform(delete("/item/{itemNo}", BigInteger.ONE))
                .andExpect(status().isOk());
    }
}