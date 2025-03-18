package com.example.demo.controller;

import com.example.demo.dto.BrandInfoDto;
import com.example.demo.dto.BrandReqDto;
import com.example.demo.dto.ItemInfoDto;
import com.example.demo.exception.CustomException;
import com.example.demo.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/brand")
@Tag(name = "Brand Management API", description = "브랜드 관리 API")
public class BrandController {
    private final BrandService BrandService;

    public BrandController(BrandService brandService)
    {
        this.BrandService = brandService;
    }

    @Operation(summary = "브랜드 조회", description = "브랜드 코드(brandCode)를 입력받아 브랜드 정보를 조회합니다.")
    @GetMapping("/{brandCode}")
    public ResponseEntity<BrandInfoDto> getBrand(@PathVariable("brandCode") Integer brandCode)
    {
        if (brandCode == null || brandCode <= 0) {
            throw new CustomException("유효하지 않은 브랜드코드입니다.", HttpStatus.BAD_REQUEST);
        }

        BrandInfoDto brand = BrandService.getBrand(brandCode);
        return ResponseEntity.ok(brand);
    }

    @Operation(summary = "브랜드 등록")
    @PostMapping
    public ResponseEntity<List<Integer>> addBrand(@Valid @RequestBody List<BrandReqDto> req)
    {
        List<Integer> brandCodeList = BrandService.addBrand(req);
        return ResponseEntity.ok(brandCodeList);
    }

    @Operation(summary = "브랜드 수정")
    @PutMapping
    public ResponseEntity<String> setBrand(@Valid @RequestBody BrandReqDto req)
    {
        if (req.getBrandCode() == null || req.getBrandCode() <= 0) {
            throw new CustomException("유효하지 않은 브랜드코드입니다.", HttpStatus.BAD_REQUEST);
        }

        BrandService.setBrand(req);
        return ResponseEntity.ok("브랜드가 성공적으로 수정되었습니다.");
    }

    @Operation(summary = "브랜드 삭제")
    @DeleteMapping("/{brandCode}")
    public ResponseEntity<String> deleteBrand(@PathVariable("brandCode")Integer brandCode)
    {
        BrandService.deleteBrand(brandCode);
        return ResponseEntity.ok("브랜드가 성공적으로 삭제되었습니다.");
    }
}
