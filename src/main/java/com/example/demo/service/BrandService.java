package com.example.demo.service;

import com.example.demo.dto.BrandInfoDto;
import com.example.demo.dto.BrandReqDto;
import com.example.demo.mapper.BrandMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    private final BrandMapper brandMapper;
    private final ValidationService validationService;

    public BrandService(ValidationService validationService, BrandMapper brandMapper)
    {
        this.validationService = validationService;
        this.brandMapper = brandMapper;
    }

    public BrandInfoDto getBrand(Integer brandCode)
    {
        return brandMapper.getBrand(brandCode);
    }

    public List<Integer> addBrand(List<BrandReqDto> req)
    {
        List<Integer> brandCodeList = new ArrayList<Integer>();
        for (BrandReqDto entity : req)
        {
            validationService.validateBrand(entity.getBrandName());
            brandMapper.addBrand(entity);

            brandCodeList.add(entity.getBrandCode());
        }

        return brandCodeList;
    }

    public void setBrand(BrandReqDto req)
    {
        brandMapper.setBrand(req);
    }

    public void deleteBrand(Integer brandCode)
    {
        brandMapper.deleteBrand(brandCode);
    }
}
