package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ValidationMapper {
    Boolean existsBrand(String brandName);
    Boolean existsBrandCode(Integer brandCode);
    Boolean existsCategory(String CategoryName);
    Boolean existsCategoryCode(Integer CategoryCode);
}
