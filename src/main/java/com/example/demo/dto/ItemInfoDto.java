package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //
public class ItemInfoDto {
    private BigInteger itemNo;
    private Integer brandCode;
    private String brandName;
    private Integer categoryCode;
    private String categoryName;
    private BigDecimal price;
}
