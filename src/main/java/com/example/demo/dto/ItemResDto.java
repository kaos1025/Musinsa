package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ItemResDto {
    @Schema(description = "등록된 상품 번호", example = "1")
    private BigInteger itemNo;
}
