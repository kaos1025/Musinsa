package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timeStamp; // 오류 발생 시간
    private int status; // HTTP 상태 코드
    private String error; // 오류 유형
    private String message; // 오류 메세지
    private String path; // 요청 경로
}
