package com.example.demo.service;

import com.example.demo.exception.CustomException;
import com.example.demo.mapper.ValidationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {
    private final ValidationMapper validationMapper;

    public ValidationService(ValidationMapper validationMapper)
    {
        this.validationMapper = validationMapper;
    }

    public void validateBrandCode(Integer brandCode)
    {
        if(validationMapper.existsBrandCode(brandCode))
            throw new CustomException("존재하지 않는 브랜드입니다 : " + brandCode, HttpStatus.NOT_FOUND);
    }

    public void validateCategoryCode(Integer categoryCode)
    {
        if(validationMapper.existsCategoryCode(categoryCode))
            throw new CustomException("존재하지 않는 카테고리입니다 : " + categoryCode, HttpStatus.NOT_FOUND);
    }

    public void validateBrand(String brandName)
    {
        if(validationMapper.existsBrand(brandName))
            throw new CustomException("이미 존재하는 브랜드입니다 : " + brandName, HttpStatus.CONFLICT);
    }

    public void validateCategory(String categoryName)
    {
        if(validationMapper.existsCategory(categoryName))
            throw new CustomException("이미 존재하는 카테고리입니다 : " + categoryName, HttpStatus.CONFLICT);
    }
}
