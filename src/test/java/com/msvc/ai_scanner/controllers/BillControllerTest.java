package com.msvc.ai_scanner.controllers;

import com.msvc.ai_scanner.dto.request.BillDtoWoCreatedAt;
import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.services.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@WebMvcTest(BillController.class) // Carga solo el controlador
@ExtendWith(MockitoExtension.class)
public class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;  // permite simular peticiones a los edpints

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    private BillDtoWoCreatedAt testBillDto;
    private Bill testBill;

    @BeforeEach
    void setUp() {
        // Inicializa los datos de prueba
        testBillDto = new BillDtoWoCreatedAt("Test Company", Category.ENTERTAINMENT, Type.EXPENSE, 100L, 1L);
        testBill = new Bill(1L, "Test Company",LocalDateTime.now(),LocalDateTime.of(2022,8,10,11,12) ,Category.ENTERTAINMENT, Type.EXPENSE, 100L, 1L, LocalDateTime.now());
    }
}
