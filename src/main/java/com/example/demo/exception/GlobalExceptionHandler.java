package com.example.demo.exception;

import com.example.demo.controller.BrandController;
import com.example.demo.controller.CategoryController;
import com.example.demo.controller.ItemController;
import com.example.demo.controller.RecommendController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.example.demo.dto.ErrorResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private boolean isSwaggerRequest(WebRequest req) {
        String requestUri = req.getDescription(false);
        return requestUri.contains("/v3/api-docs") || requestUri.contains("/swagger-ui");
    }

    // Validation 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest req)
    {
        // Swagger 요청이면 예외 처리하지 않음
        /*if (isSwaggerRequest(req)) {
            return ResponseEntity.ok().build();
        }*/

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Fail",
            errors.toString(),
            req.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 비즈니스 로직 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex, WebRequest req)
    {
        // Swagger 요청이면 예외 처리하지 않음
        /*if (isSwaggerRequest(req)) {
            return ResponseEntity.ok().build();
        }*/

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatus().value(),
                "Business Logic Error",
                ex.getMessage(),
                req.getDescription(false)
        );

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

    // ETC 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest req)
    {
        // Swagger 요청이면 예외 처리하지 않음
        /*if (isSwaggerRequest(req)) {
            return ResponseEntity.ok().build();
        }*/

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                req.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
