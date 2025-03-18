package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class BrandControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void 브랜드_등록_성공() throws Exception {
        String requestBody = """
        [
            {
                "brandName": "Nike"
            }
        ]
        """;

        mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void 브랜드_수정_성공() throws Exception {
        String requestBody = """
        {
                "brandCode": "1"
                "brandName": "Adidas"
        }
        """;
        // 존재하는 브랜드 코드 조회 (예: 1)
        mockMvc.perform(put("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void 브랜드_조회_성공() throws Exception {
        // 존재하는 브랜드 코드 조회 (예: 1)
        mockMvc.perform(get("/brand/{brandCode}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandName").exists());
    }

    @Test
    void 브랜드_삭제_성공() throws Exception {
        mockMvc.perform(delete("/brand/{brandCode}", 1))
                .andExpect(status().isOk());
    }
}