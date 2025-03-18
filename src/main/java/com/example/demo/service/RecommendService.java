package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.mapper.RecommendMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendService {
    private final ItemService itemService;
    private final RecommendMapper recommendMapper;
    public RecommendService(ItemService itemService, RecommendMapper recommendMapper)
    {
        this.itemService = itemService;
        this.recommendMapper = recommendMapper;
    }

    // 구현1 - 카테고리 별 최저가 브랜드, 상품가격, 총액
    public LowestPriceResDto getLowestPriceItem()
    {
        LowestPriceResDto res = new LowestPriceResDto();

        List<ItemInfoDto> itemList = recommendMapper.getLowestPriceItem();
        res.setItemInfoList(itemList);
        res.setTotalPrice(itemList.stream()
                .map(ItemInfoDto::getPrice) // price 추출
                .reduce(BigDecimal.ZERO, BigDecimal::add)); // 합산

        return res;
    }

    // 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액 조회
    public LowestPriceBrandResDto getLowestPriceBrand()
    {
        LowestPriceBrandResDto res = new LowestPriceBrandResDto();

        List<BrandTotalPriceResDto> brandTotalPriceList = recommendMapper.getBrandTotalPrice();
        BrandTotalPriceResDto minBrandTotalPrice = Collections.min(brandTotalPriceList, Comparator.comparing(BrandTotalPriceResDto::getTotalPrice));

        res.setBrandName(minBrandTotalPrice.getBrandName());
        res.setTotalPrice(minBrandTotalPrice.getTotalPrice());

        //List<ItemInfoDto> itemList = itemService.getItemInfoByBrandCode(minBrandTotalPrice.getBrandCode());
        res.setCategoryItemList(itemService.getItemInfoByBrandCode(minBrandTotalPrice.getBrandCode()));

        return res;
    }

    //구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    public CategoryPriceResDto getCategoryPrice(String categoryName)
    {
        CategoryPriceResDto res = new CategoryPriceResDto();

        List<ItemInfoDto> itemList = itemService.getItemInfoByCategoryName(categoryName);
        res.setCategoryName(categoryName);

        Optional<BigDecimal> minPrice = itemList.stream()
                .map(ItemInfoDto::getPrice)
                .min(BigDecimal::compareTo);

        List<ItemInfoDto> minPriceItem = minPrice.map(price -> itemList.stream()
                .filter(item -> item.getPrice().compareTo(price) == 0)
                .collect(Collectors.toList()))
                .orElseGet(List::of);

        Optional<BigDecimal> maxPrice = itemList.stream()
                .map(ItemInfoDto::getPrice)
                .max(BigDecimal::compareTo);

        List<ItemInfoDto> maxPriceItem = maxPrice.map(price -> itemList.stream()
                        .filter(item -> item.getPrice().compareTo(price) == 0)
                        .collect(Collectors.toList()))
                .orElseGet(List::of);

        res.setLowestPrice(minPriceItem);
        res.setHighestPrice(maxPriceItem);
        return res;
    }
}
