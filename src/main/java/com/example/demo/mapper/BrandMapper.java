package com.example.demo.mapper;

import com.example.demo.dto.BrandInfoDto;
import com.example.demo.dto.BrandReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandMapper {
    BrandInfoDto getBrand(Integer brandCode);
    @Options(useGeneratedKeys = true, keyProperty = "brandCode")
    void addBrand(@Param("req") BrandReqDto req);
    void setBrand(@Param("req") BrandReqDto req);
    void deleteBrand(Integer brandCode);
}
