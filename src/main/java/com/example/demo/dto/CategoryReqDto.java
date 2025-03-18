package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryReqDto {
    @Schema(description = "카테고리 코드", example = "1")
    private Integer categoryCode;
    @Schema(description = "카테고리명", example = "상의")
    @NotBlank(message = "카테고리명은 필수입니다.")
    private String categoryName;
}
