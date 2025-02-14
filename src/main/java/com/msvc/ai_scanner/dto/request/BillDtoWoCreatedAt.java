package com.msvc.ai_scanner.dto.request;

import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class BillDtoWoCreatedAt {
    @NotBlank(message = "company is required")
    private String company;

    @NotNull(message = "category is required")
    private Category category;

    @NotNull(message = "Bill date is required")
    private LocalDateTime billDate;

    @NotNull(message = "type is required")
    private Type type;

    @NotNull(message = "amount is required")
    private Long amount;

    @NotNull(message = "user id is required")
    private Long userId;
}

