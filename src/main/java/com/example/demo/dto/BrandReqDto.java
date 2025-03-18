package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigInteger;

@Data
public class BrandReqDto {
    private Integer brandCode;
    @NotBlank(message = "브랜드명은 필수입니다.")
    private String brandName;
}
