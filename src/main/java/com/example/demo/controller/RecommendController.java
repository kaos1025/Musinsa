package com.example.demo.controller;
import com.example.demo.dto.CategoryPriceResDto;
import com.example.demo.dto.LowestPriceBrandResDto;
import com.example.demo.dto.LowestPriceResDto;
import com.example.demo.exception.CustomException;
import com.example.demo.service.RecommendService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/recommend")
@Tag(name = "코디 추천 API", description = "코디 추천 API")
public class RecommendController {
    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService)
    {
        this.recommendService = recommendService;
    }

    @Operation(summary = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회")
    @GetMapping("/lowestPriceItem")
    public ResponseEntity<LowestPriceResDto> getLowestPriceItem()
    {
        LowestPriceResDto lowestPriceItem = recommendService.getLowestPriceItem();
        return ResponseEntity.ok(lowestPriceItem);
    }

    @Operation(summary = "- 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을\n" +
            "조회")
    @GetMapping("/lowestPriceBrand")
    public ResponseEntity<LowestPriceBrandResDto> getLowestPriceBrand()
    {
        LowestPriceBrandResDto lowestPriceBrand = recommendService.getLowestPriceBrand();
        return ResponseEntity.ok(lowestPriceBrand);
    }

    @Operation(summary = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회")
    @GetMapping("/categoryPrice/{categoryName}")
    public ResponseEntity<CategoryPriceResDto> getCategoryPrice(@PathVariable("categoryName") @NotBlank(message = "카테고리명은 필수입니다.") String categoryName)
    {
        CategoryPriceResDto categoryPrice =  recommendService.getCategoryPrice(categoryName);
        return ResponseEntity.ok(categoryPrice);
    }
}