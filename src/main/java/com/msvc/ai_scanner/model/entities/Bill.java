package com.msvc.ai_scanner.model.entities;

import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "bills")
public class Bill {
    @Id
    private Long id;
    private String company;
    @Field(name = "created_at")
    private LocalDateTime createdAt;
    @Field(name = "bill_date")
    private LocalDateTime billDate;
    private Category category;
    private Type type;
    private Long amount;
    @Field(name = "user_id")
    private Long userId;
}
