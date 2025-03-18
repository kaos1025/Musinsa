package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryReqDto {
    private Integer categoryCode;
    @NotBlank(message = "카테고리명은 필수입니다.")
    private String categoryName;
}
