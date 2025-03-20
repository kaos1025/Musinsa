package com.example.demo.controller;

import com.example.demo.dto.ItemInfoDto;
import com.example.demo.dto.ItemReqDto;
import com.example.demo.dto.ItemResDto;
import com.example.demo.exception.CustomException;
import com.example.demo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/item")
@Tag(name = "Item Management API", description = "상품 관리 API")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }


    @Operation(summary = "상품 조회", description = "상품 번호(itemNo)를 입력받아 해당 상품 정보를 조회합니다.")
    @GetMapping("/{itemNo}")
    public ResponseEntity<ItemInfoDto> getItem(@PathVariable("itemNo")BigInteger itemNo)
    {
        if (itemNo == null || itemNo.compareTo(BigInteger.ZERO) <= 0) {
            throw new CustomException("유효하지 않은 상품 번호입니다.", HttpStatus.BAD_REQUEST);
        }

        ItemInfoDto item = itemService.getItem(itemNo);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "상품 조회", description = "브랜드코드(brandCode)를 입력받아 해당 브랜드코드로 설정된 상품 정보를 조회합니다.")
    @GetMapping("/brandCode/{brandCode}")
    public ResponseEntity<List<ItemInfoDto>> getItemInfoByBrandCode(@PathVariable("brandCode")Integer brandCode)
    {
        if (brandCode == null || brandCode <= 0) {
            throw new CustomException("유효하지 않은 브랜드 코드입니다.", HttpStatus.BAD_REQUEST);
        }

        List<ItemInfoDto> itemList = itemService.getItemInfoByBrandCode(brandCode);
        return ResponseEntity.ok(itemList);
    }

    @Operation(summary = "상품 조회", description = "브랜드명(brandName)을 입력받아 해당 브랜드명으로 설정된 상품 정보를 조회합니다.")
    @GetMapping("/brandName/{brandName}")
    public ResponseEntity<List<ItemInfoDto>> getItemInfoByBrandName(
            @PathVariable("brandName") @NotBlank(message = "브랜드 이름은 필수 입력 항목입니다.") String brandName)
    {
        List<ItemInfoDto> itemList = itemService.getItemInfoByBrandName(brandName);
        return ResponseEntity.ok(itemList);
    }

    @Operation(summary = "상품 조회", description = "카테고리 코드(categoryCode)를 입력받아 해당 카테고리로 설정된 상품 정보를 조회합니다.")
    @GetMapping("/categoryCode/{categoryCode}")
    public ResponseEntity<List<ItemInfoDto>> getItemInfoByCategoryCode(@PathVariable("categoryCode")Integer categoryCode)
    {
        if (categoryCode == null || categoryCode <= 0) {
            throw new CustomException("유효하지 않은 카테고리 코드입니다.", HttpStatus.BAD_REQUEST);
        }

        List<ItemInfoDto> itemList = itemService.getItemInfoByCategoryCode(categoryCode);
        return ResponseEntity.ok(itemList);
    }

    @Operation(summary = "상품 조회", description = "카테고리명(categoryName)를 입력받아 해당 카테고리로 설정된 상품 정보를 조회합니다.")
    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<List<ItemInfoDto>> getItemInfoByCategoryName(
            @PathVariable("categoryName") @NotBlank(message = "브랜드 이름은 필수 입력 항목입니다.") String categoryName)
    {
        List<ItemInfoDto> itemList = itemService.getItemInfoByCategoryName(categoryName);
        return ResponseEntity.ok(itemList);
    }

    @Operation(summary = "상품 등록")
    @PostMapping
    public ResponseEntity<List<ItemResDto>> addItem(@Valid @RequestBody List<ItemReqDto> req)
    {
        List<ItemResDto> itemNoList = itemService.addItem(req);
        return ResponseEntity.ok(itemNoList);
    }

    @Operation(summary = "상품 수정")
    @PutMapping
    public ResponseEntity<String> setItem(@Valid @RequestBody ItemReqDto req)
    {
        if (req.getItemNo() == null || req.getItemNo().compareTo(BigInteger.ZERO) <= 0) {
            throw new CustomException("유효하지 않은 상품 번호입니다.", HttpStatus.BAD_REQUEST);
        }

        itemService.setItem(req);
        return ResponseEntity.ok("상품이 성공적으로 수정되었습니다.");
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/{itemNo}")
    public ResponseEntity<String> deleteItem(@PathVariable("itemNo")BigInteger itemNo)
    {
        if (itemNo == null || itemNo.compareTo(BigInteger.ZERO) <= 0) {
            throw new CustomException("유효하지 않은 상품 번호입니다.", HttpStatus.BAD_REQUEST);
        }
        
        itemService.deleteItem(itemNo);
        return ResponseEntity.ok("상품이 성공적으로 삭제되었습니다.");
    }
}
