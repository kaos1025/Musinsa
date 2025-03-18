package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPriceResDto {
    private String categoryName;
    private List<ItemInfoDto> lowestPrice;
    private List<ItemInfoDto> highestPrice;
}
