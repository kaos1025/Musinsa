package com.example.demo.service;

import com.example.demo.dto.ItemInfoDto;
import com.example.demo.dto.ItemReqDto;
import com.example.demo.dto.ItemResDto;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.ItemMapper;
import org.springframework.http.HttpStatus;
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
        ItemInfoDto item = itemMapper.getItem(itemNo);

        if (item == null)
            throw new CustomException("존재하지 않는 상품입니다 : " + itemNo, HttpStatus.NOT_FOUND);

        return item;
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
        validationService.validateCategoryCode(req.getCategoryCode());
        validationService.validateBrandCode(req.getBrandCode());

        ItemInfoDto item = itemMapper.getItem(req.getItemNo());

        if (item == null)
            throw new CustomException("존재하지 않는 상품입니다 : " + req.getItemNo(), HttpStatus.NOT_FOUND);

        itemMapper.setItem(req);
    }

    public void deleteItem(BigInteger itemNo)
    {
        ItemInfoDto item = itemMapper.getItem(itemNo);

        if (itemNo == null)
            throw new CustomException("존재하지 않는 상품입니다 : " + itemNo, HttpStatus.NOT_FOUND);

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
