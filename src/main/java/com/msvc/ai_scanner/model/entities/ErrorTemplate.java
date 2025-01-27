package com.msvc.ai_scanner.model.entities;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ErrorTemplate {
    private String message;
    private Integer code;
    private String reason;
    private LocalDateTime timestamp;
}
