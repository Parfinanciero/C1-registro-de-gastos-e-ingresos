package com.msvc.ai_scanner.dto.request;

import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;


public class BillDtoWoCreatedAt {
    //AÑADIR ANOTACIONES DE VALIDATION Y PONER EL @Valid en el controller de crear
    String company;
    private Category category;
    private Type type;
    private Long amount;
    private Long userId;
}

