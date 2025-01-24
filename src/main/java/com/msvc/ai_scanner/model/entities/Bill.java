package com.msvc.ai_scanner.model.entities;

import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "bills")
public class Bill {
    @Id
    private Long id;
    private String company;
    private LocalDateTime createdAt;
    private LocalDateTime billDate;
    private Category category;
    private Type type;
    private Long amount;
    private Long userId;
}
