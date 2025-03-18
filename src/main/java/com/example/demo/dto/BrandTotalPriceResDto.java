package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BrandTotalPriceResDto {
    private Integer brandCode;
    private String brandName;
    private BigDecimal totalPrice;
}
