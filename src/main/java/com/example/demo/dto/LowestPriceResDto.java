package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class LowestPriceResDto {
    private List<ItemInfoDto> itemInfoList;
    private BigDecimal totalPrice;
}
