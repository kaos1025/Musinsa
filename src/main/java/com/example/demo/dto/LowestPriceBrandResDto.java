package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class LowestPriceBrandResDto {
    private String brandName;
    private List<ItemInfoDto> categoryItemList;
    private BigDecimal totalPrice;
}