package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigInteger;

@Data
public class BrandReqDto {
    @Schema(description = "브랜드 코드", example = "1")
    private Integer brandCode;
    @Schema(description = "브랜드명", example = "무탠다드")
    @NotBlank(message = "브랜드명은 필수입니다.")
    private String brandName;
}
