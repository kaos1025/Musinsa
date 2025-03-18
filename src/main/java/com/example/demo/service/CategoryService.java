package com.example.demo.service;

import com.example.demo.dto.CategoryReqDto;
import com.example.demo.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final ValidationService validationService;

    public CategoryService(ValidationService validationService, CategoryMapper categoryMapper)
    {
        this.validationService = validationService;
        this.categoryMapper = categoryMapper;
    }

    public List<Integer> addCategory(List<CategoryReqDto> req)
    {
        List<Integer> categoryCodeList = new ArrayList<Integer>();
        for(CategoryReqDto entity : req) {
            validationService.validateCategory(entity.getCategoryName());
            categoryMapper.addCategory(entity);

            categoryCodeList.add(entity.getCategoryCode());
        }

        return categoryCodeList;
    }
}
