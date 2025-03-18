package com.example.demo.mapper;

import com.example.demo.dto.CategoryReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CategoryMapper {
    @Options(useGeneratedKeys = true, keyProperty = "categoryCode")
    void addCategory(CategoryReqDto req);
}
