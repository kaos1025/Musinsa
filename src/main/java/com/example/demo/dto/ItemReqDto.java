package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ItemReqDto {
    private BigInteger itemNo;
    @NotNull(message = "브랜드 코드는 필수입니다.")
    private Integer brandCode;
    @NotNull(message = "카테고리 코드는 필수입니다.")
    private Integer categoryCode;
    @NotNull(message = "판매가는 필수입니다.")
    @Min(value = 1, message = "판매가는 1원 이상이여야 합니다.")
    private BigDecimal price;
}
