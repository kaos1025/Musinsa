package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.mapper.RecommendMapper;
import com.example.demo.service.ItemService;
import com.example.demo.service.RecommendService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecommendServiceTest {

    @Mock
    private RecommendMapper recommendMapper;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private RecommendService recommendService;

    @Test
    void getLowestPriceItem_ShouldReturnLowestPriceItems() {
        // Given
        ItemInfoDto itemInfoDto = new ItemInfoDto();
        itemInfoDto.setItemNo(BigInteger.ONE);
        itemInfoDto.setBrandCode(101);
        itemInfoDto.setCategoryCode(201);
        itemInfoDto.setPrice(new BigDecimal("30000"));

        List<ItemInfoDto> mockItems = List.of(itemInfoDto);

        when(recommendMapper.getLowestPriceItem()).thenReturn(mockItems);

        // When
        LowestPriceResDto result = recommendService.getLowestPriceItem();

        // Then
        assertNotNull(result);
        assertEquals(1, result.getItemInfoList().size());
        assertEquals(new BigDecimal("30000"), result.getTotalPrice());
    }

    @Test
    void getLowestPriceBrand_ShouldReturnLowestPriceBrand() {
        // Given
        BrandTotalPriceResDto mockBrand = new BrandTotalPriceResDto();
        mockBrand.setBrandName("Nike");
        mockBrand.setBrandCode(101);
        mockBrand.setTotalPrice(new BigDecimal("50000"));

        List<BrandTotalPriceResDto> brandList = List.of(mockBrand);
        when(recommendMapper.getBrandTotalPrice()).thenReturn(brandList);
        when(itemService.getItemInfoByBrandCode(101)).thenReturn(List.of());

        // When
        LowestPriceBrandResDto result = recommendService.getLowestPriceBrand();

        // Then
        assertNotNull(result);
        assertEquals("Nike", result.getBrandName());
        assertEquals(new BigDecimal("50000"), result.getTotalPrice());
    }

    @Test
    void getCategoryPrice_ShouldReturnMinMaxPriceItems() {
        // Given: 카테고리별 아이템 정보 설정
        ItemInfoDto itemInfoDto = new ItemInfoDto();
        itemInfoDto.setItemNo(BigInteger.ONE);
        itemInfoDto.setBrandCode(101);
        itemInfoDto.setBrandName("Nike");
        itemInfoDto.setCategoryCode(201);
        itemInfoDto.setCategoryName("상의");
        itemInfoDto.setPrice(new BigDecimal("30000"));

        ItemInfoDto itemInfoDto2 = new ItemInfoDto();
        itemInfoDto2.setItemNo(BigInteger.TWO);
        itemInfoDto2.setBrandCode(201);
        itemInfoDto2.setBrandName("Adidas");
        itemInfoDto2.setCategoryCode(201);
        itemInfoDto2.setCategoryName("상의");
        itemInfoDto2.setPrice(new BigDecimal("55000"));

        ItemInfoDto itemInfoDto3 = new ItemInfoDto();
        itemInfoDto3.setItemNo(BigInteger.valueOf(3));
        itemInfoDto3.setBrandCode(301);
        itemInfoDto3.setBrandName("Puma");
        itemInfoDto3.setCategoryCode(201);
        itemInfoDto3.setCategoryName("상의");
        itemInfoDto3.setPrice(new BigDecimal("45000"));

        ItemInfoDto itemInfoDto4 = new ItemInfoDto();
        itemInfoDto4.setItemNo(BigInteger.valueOf(4));
        itemInfoDto4.setBrandCode(401);
        itemInfoDto4.setBrandName("아웃스탠딩");
        itemInfoDto4.setCategoryCode(201);
        itemInfoDto4.setCategoryName("상의");
        itemInfoDto4.setPrice(new BigDecimal("75000"));

        List<ItemInfoDto> itemList = List.of(itemInfoDto, itemInfoDto2, itemInfoDto3, itemInfoDto4);
        when(itemService.getItemInfoByCategoryName("상의")).thenReturn(itemList);

        // When: getCategoryPrice() 호출
        CategoryPriceResDto result = recommendService.getCategoryPrice("상의");

        // Then: 최저가, 최고가 항목 검증
        assertNotNull(result);
        assertEquals("상의", result.getCategoryName());
        assertEquals(1, result.getLowestPrice().size());
        assertEquals(new BigDecimal("30000"), result.getLowestPrice().get(0).getPrice());
        assertEquals(1, result.getHighestPrice().size());
        assertEquals(new BigDecimal("75000"), result.getHighestPrice().get(0).getPrice());

        verify(itemService, times(1)).getItemInfoByCategoryName("상의");
    }
}