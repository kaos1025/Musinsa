package com.example.demo.mapper;
import com.example.demo.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper {
    List<ItemInfoDto> getLowestPriceItem();
    List<BrandTotalPriceResDto> getBrandTotalPrice();
    //List<ItemInfoDto> getLowestPriceBrand();
    CategoryPriceResDto getCategoryPrice(String categoryName);
}
