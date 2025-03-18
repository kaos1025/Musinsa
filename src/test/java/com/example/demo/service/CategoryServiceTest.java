package com.example.demo.service;

import com.example.demo.dto.CategoryReqDto;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void addCategory_ShouldValidateAndAddCategories() {
        // Given
        CategoryReqDto request = new CategoryReqDto();
        request.setCategoryCode(201);
        request.setCategoryName("상의");

        List<CategoryReqDto> requestList = List.of(request);

        doNothing().when(validationService).validateCategory(any());
        doNothing().when(categoryMapper).addCategory(any());

        // When
        List<Integer> response = categoryService.addCategory(requestList);

        // Then
        assertEquals(1, response.size());
        assertEquals(201, response.get(0));

        verify(validationService, times(1)).validateCategory("상의");
        verify(categoryMapper, times(1)).addCategory(any());
    }
}