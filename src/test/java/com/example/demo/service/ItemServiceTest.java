package com.example.demo.service;
import com.example.demo.dto.ItemInfoDto;
import com.example.demo.dto.ItemReqDto;
import com.example.demo.dto.ItemResDto;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.service.ItemService;
import com.example.demo.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //
public class ItemServiceTest {

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private ItemService itemService; //

    @BeforeEach
    void setUp() {
        // 테스트 실행 전 설정 가능 (필요 시 추가)
    }

    @Test
    void getItem_ShouldReturnItem() {
        // Given
        BigInteger itemNo = BigInteger.ONE;
        ItemInfoDto mockItem = new ItemInfoDto();
        mockItem.setItemNo(itemNo);
        mockItem.setBrandName("무탠다드");
        mockItem.setPrice(new BigDecimal("30000"));

        when(itemMapper.getItem(itemNo)).thenReturn(mockItem);

        // When
        ItemInfoDto result = itemService.getItem(itemNo);

        // Then
        assertNotNull(result);
        assertEquals("무탠다드", result.getBrandName());
        assertEquals(new BigDecimal("30000"), result.getPrice());
    }

    @Test
    void addItem_ShouldValidateAndAddItems() {
        // Given
        ItemReqDto request = new ItemReqDto();
        request.setBrandCode(101);
        request.setCategoryCode(201);
        request.setPrice(new BigDecimal("30000"));

        List<ItemReqDto> requestList = List.of(request);

        doNothing().when(validationService).validateCategoryCode(any());
        doNothing().when(validationService).validateBrandCode(any());
        doNothing().when(itemMapper).addItem(any());

        // When
        List<ItemResDto> response = itemService.addItem(requestList);

        // Then
        assertEquals(1, response.size());
        verify(validationService, times(1)).validateCategoryCode(201);
        verify(validationService, times(1)).validateBrandCode(101);
        verify(itemMapper, times(1)).addItem(any());
    }

    @Test
    void setItem_ShouldUpdateItem() {
        // Given
        ItemReqDto request = new ItemReqDto();
        request.setItemNo(BigInteger.ONE);
        request.setBrandCode(101);
        request.setCategoryCode(201);
        request.setPrice(new BigDecimal("40000"));

        doNothing().when(itemMapper).setItem(any());

        // When
        itemService.setItem(request);

        // Then
        verify(itemMapper, times(1)).setItem(request);
    }

    @Test
    void deleteItem_ShouldRemoveItem() {
        // Given
        BigInteger itemNo = BigInteger.ONE;
        doNothing().when(itemMapper).deleteItem(itemNo);

        // When
        itemService.deleteItem(itemNo);

        // Then
        verify(itemMapper, times(1)).deleteItem(itemNo);
    }

    @Test
    void getItemInfoByBrandName_ShouldReturnItems() {
        // Given
        String brandName = "Nike";
        ItemInfoDto mockItem = new ItemInfoDto();
        mockItem.setBrandName(brandName);

        when(itemMapper.getItemInfoByBrandName(brandName)).thenReturn(List.of(mockItem));

        // When
        List<ItemInfoDto> items = itemService.getItemInfoByBrandName(brandName);

        // Then
        assertFalse(items.isEmpty());
        assertEquals("Nike", items.get(0).getBrandName());
    }

    @Test
    void getItemInfoByBrandCode_ShouldReturnItems() {
        // Given
        Integer brandCode = 101;
        ItemInfoDto mockItem = new ItemInfoDto();
        mockItem.setBrandCode(brandCode);

        when(itemMapper.getItemInfoByBrandCode(brandCode)).thenReturn(List.of(mockItem));

        // When
        List<ItemInfoDto> items = itemService.getItemInfoByBrandCode(brandCode);

        // Then
        assertFalse(items.isEmpty());
        assertEquals(101, items.get(0).getBrandCode());
    }

    @Test
    void getItemInfoByCategoryName_ShouldReturnItems() {
        // Given
        String categoryName = "상의";
        ItemInfoDto mockItem = new ItemInfoDto();
        mockItem.setCategoryName(categoryName);

        when(itemMapper.getItemInfoByCategoryName(categoryName)).thenReturn(List.of(mockItem));

        List<ItemInfoDto> items = itemService.getItemInfoByCategoryName(categoryName);

        assertFalse(items.isEmpty());
        assertEquals("상의", items.get(0).getCategoryName());
    }

    @Test
    void getItemInfoByCategoryCode_ShouldReturnItems() {
        Integer categoryCode = 201;
        ItemInfoDto mockItem = new ItemInfoDto();
        mockItem.setCategoryCode(categoryCode);

        when(itemMapper.getItemInfoByCategoryCode(categoryCode)).thenReturn(List.of(mockItem));

        List<ItemInfoDto> items = itemService.getItemInfoByCategoryCode(categoryCode);

        assertFalse(items.isEmpty());
        assertEquals(201, items.get(0).getCategoryCode());
    }
}