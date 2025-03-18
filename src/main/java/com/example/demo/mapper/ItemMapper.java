package com.example.demo.mapper;

import com.example.demo.dto.ItemInfoDto;
import com.example.demo.dto.ItemReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ItemMapper {
    ItemInfoDto getItem(BigInteger itemNo);

    @Options(useGeneratedKeys = true, keyProperty = "itemNo")
    void addItem(@Param("req")ItemReqDto req);

    void setItem(@Param("req")ItemReqDto req);
    void deleteItem(@Param("itemNo")BigInteger itemNo);
    List<ItemInfoDto> getItemInfoByBrandName(String brandName);
    List<ItemInfoDto> getItemInfoByBrandCode(Integer brandCode);
    List<ItemInfoDto> getItemInfoByCategoryName(String categoryName);
    List<ItemInfoDto> getItemInfoByCategoryCode(Integer categoryCode);
}
