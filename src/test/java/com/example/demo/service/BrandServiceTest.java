package com.example.demo.service;

import com.example.demo.dto.BrandInfoDto;
import com.example.demo.dto.BrandReqDto;
import com.example.demo.mapper.BrandMapper;
import com.example.demo.service.BrandService;
import com.example.demo.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandMapper brandMapper;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private BrandService brandService;

    @Test
    void getBrand_ShouldReturnBrandInfo() {
        Integer brandCode = 101;
        BrandInfoDto mockBrand = new BrandInfoDto();
        mockBrand.setBrandCode(brandCode);
        mockBrand.setBrandName("무신사 스탠다드");

        when(brandMapper.getBrand(brandCode)).thenReturn(mockBrand);

        BrandInfoDto result = brandService.getBrand(brandCode);

        assertNotNull(result);
        assertEquals("무신사 스탠다드", result.getBrandName());
        assertEquals(101, result.getBrandCode());
    }

    @Test
    void addBrand_ShouldValidateAndAddBrands() {
        BrandReqDto request = new BrandReqDto();
        request.setBrandCode(101);
        request.setBrandName("무신사 스탠다드");

        List<BrandReqDto> requestList = List.of(request);

        doNothing().when(validationService).validateBrand(any());
        doNothing().when(brandMapper).addBrand(any());

        List<Integer> response = brandService.addBrand(requestList);

        assertEquals(1, response.size());
        assertEquals(101, response.get(0));

        verify(validationService, times(1)).validateBrand("무신사 스탠다드");
        verify(brandMapper, times(1)).addBrand(any());
    }

    @Test
    void setBrand_ShouldUpdateBrand() {
        BrandReqDto request = new BrandReqDto();
        request.setBrandCode(101);
        request.setBrandName("아웃스탠딩");

        doNothing().when(brandMapper).setBrand(any());

        brandService.setBrand(request);

        verify(brandMapper, times(1)).setBrand(request);
    }

    @Test
    void deleteBrand_ShouldRemoveBrand() {
        Integer brandCode = 101;
        doNothing().when(brandMapper).deleteBrand(brandCode);

        brandService.deleteBrand(brandCode);

        verify(brandMapper, times(1)).deleteBrand(brandCode);
    }
}