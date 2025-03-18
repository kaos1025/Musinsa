package com.example.demo.controller;
import com.example.demo.dto.CategoryPriceResDto;
import com.example.demo.dto.LowestPriceBrandResDto;
import com.example.demo.dto.LowestPriceResDto;
import com.example.demo.exception.CustomException;
import com.example.demo.service.RecommendService;
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

    @GetMapping("/lowestPriceItem")
    public ResponseEntity<LowestPriceResDto> getLowestPriceItem()
    {
        LowestPriceResDto lowestPriceItem = recommendService.getLowestPriceItem();
        return ResponseEntity.ok(lowestPriceItem);
    }

    @GetMapping("/lowestPriceBrand")
    public ResponseEntity<LowestPriceBrandResDto> getLowestPriceBrand()
    {
        LowestPriceBrandResDto lowestPriceBrand = recommendService.getLowestPriceBrand();
        return ResponseEntity.ok(lowestPriceBrand);
    }

    @GetMapping("/categoryPrice/{categoryName}")
    public ResponseEntity<CategoryPriceResDto> getCategoryPrice(@PathVariable("categoryName") @NotBlank(message = "카테고리명은 필수입니다.") String categoryName)
    {
        CategoryPriceResDto categoryPrice =  recommendService.getCategoryPrice(categoryName);
        return ResponseEntity.ok(categoryPrice);
    }
}