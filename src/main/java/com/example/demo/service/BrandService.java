package com.example.demo.service;

import com.example.demo.dto.BrandInfoDto;
import com.example.demo.dto.BrandReqDto;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.BrandMapper;
import org.springframework.http.HttpStatus;
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
        BrandInfoDto brandInfo = brandMapper.getBrand(brandCode);
        if (brandInfo == null)
            throw new CustomException("존재하지 않는 브랜드 코드입니다. : " + brandCode, HttpStatus.NOT_FOUND);

        return brandInfo;
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
        BrandInfoDto brandInfo = brandMapper.getBrand(req.getBrandCode());
        if (brandInfo == null)
            throw new CustomException("존재하지 않는 브랜드 코드입니다. : " + req.getBrandCode(), HttpStatus.NOT_FOUND);

        brandMapper.setBrand(req);
    }

    public void deleteBrand(Integer brandCode)
    {
        BrandInfoDto brandInfo = brandMapper.getBrand(brandCode);
        if (brandInfo == null)
            throw new CustomException("존재하지 않는 브랜드 코드입니다. : " + brandCode, HttpStatus.NOT_FOUND);

        brandMapper.deleteBrand(brandCode);
    }
}
