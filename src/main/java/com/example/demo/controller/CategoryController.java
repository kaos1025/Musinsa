package com.example.demo.controller;

import com.example.demo.dto.CategoryReqDto;
import com.example.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "카테고리 Management API", description = "카테고리 관리 API")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<List<Integer>> AddCategory(@Valid @RequestBody List<CategoryReqDto> req)
    {
        List<Integer> categoryCodeList = categoryService.addCategory(req);
        return ResponseEntity.ok(categoryCodeList);
    }
}
