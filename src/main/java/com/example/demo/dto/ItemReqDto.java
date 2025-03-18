package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ItemReqDto {
    @Schema(description = "상품 번호", example = "1")
    private BigInteger itemNo;
    @Schema(description = "브랜드 코드", example = "1")
    @NotNull(message = "브랜드 코드는 필수입니다.")
    private Integer brandCode;
    @Schema(description = "카테고리 코드", example = "3")
    @NotNull(message = "카테고리 코드는 필수입니다.")
    private Integer categoryCode;
    @Schema(description = "판매가", example = "50000")
    @NotNull(message = "판매가는 필수입니다.")
    @Min(value = 1, message = "판매가는 1원 이상이여야 합니다.")
    private BigDecimal price;
}
