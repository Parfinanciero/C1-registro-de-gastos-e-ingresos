package com.msvc.ai_scanner.dto.request;

import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDtoWoCreatedAt {
    @NotBlank(message = "company is required")
    private String company;
    @NotBlank(message = "category is required")
    private Category category;
    @NotBlank(message = "type is required")
    private Type type;
    @NotBlank(message = "amount is required")
    private Long amount;
    @NotBlank(message = "user id is required")
    private Long userId;
}

