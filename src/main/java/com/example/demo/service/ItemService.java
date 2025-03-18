package com.example.demo.service;

import com.example.demo.dto.ItemInfoDto;
import com.example.demo.dto.ItemReqDto;
import com.example.demo.dto.ItemResDto;
import com.example.demo.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private final ItemMapper itemMapper;
    private final ValidationService validationService;

    public ItemService(ValidationService validationService, ItemMapper itemMapper)
    {
        this.validationService = validationService;
        this.itemMapper = itemMapper;
    }

    public ItemInfoDto getItem(BigInteger itemNo)
    {
        return itemMapper.getItem(itemNo);
    }

    public List<ItemResDto> addItem(List<ItemReqDto> req)
    {
        List<ItemResDto> itemNoList = new ArrayList<ItemResDto>();

        for(ItemReqDto entity : req) {
            validationService.validateCategoryCode(entity.getCategoryCode());
            validationService.validateBrandCode(entity.getBrandCode());

            itemMapper.addItem(entity);
            ItemResDto res = new ItemResDto();
            res.setItemNo(entity.getItemNo());
            itemNoList.add(res);
        }

        return itemNoList;
    }

    public void setItem(ItemReqDto req)
    {
        itemMapper.setItem(req);
    }

    public void deleteItem(BigInteger itemNo)
    {
        itemMapper.deleteItem(itemNo);
    }

    public List<ItemInfoDto> getItemInfoByBrandName(String brandName)
    {
        return itemMapper.getItemInfoByBrandName(brandName);
    }

    public List<ItemInfoDto> getItemInfoByBrandCode(Integer brandCode)
    {
        return itemMapper.getItemInfoByBrandCode(brandCode);
    }

    public List<ItemInfoDto> getItemInfoByCategoryName(String categoryName)
    {
        return itemMapper.getItemInfoByCategoryName(categoryName);
    }

    public List<ItemInfoDto> getItemInfoByCategoryCode(Integer categoryCode)
    {
        return itemMapper.getItemInfoByCategoryCode(categoryCode);
    }
}
