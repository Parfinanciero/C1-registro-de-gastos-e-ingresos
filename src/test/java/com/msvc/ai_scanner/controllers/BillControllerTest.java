package com.msvc.ai_scanner.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msvc.ai_scanner.controllers.BillController;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private BillService billService;

    @BeforeEach
    void setup() {
        // Reemplazamos el servicio real con nuestro mock
        ReflectionTestUtils.setField(
                applicationContext.getBean(BillController.class),
                "billService",
                billService
        );

        testBillDto = new BillDtoWoCreatedAt(
                "Test Company",
                Category.ENTERTAINMENT,
                LocalDateTime.now(),
                Type.EXPENSE,
                100L,
                1L
        );

        testBill = new Bill(
                1L,
                "Test Company",
                LocalDateTime.now(),
                LocalDateTime.of(2022,8,10,11,12),
                Category.ENTERTAINMENT,
                Type.EXPENSE,
                100L,
                1L
        );
    }

    @Autowired
    private ApplicationContext applicationContext;

    private BillDtoWoCreatedAt testBillDto;
    private Bill testBill;

    @Test
    void createBill_ShouldReturnCreated() throws Exception {
        when(billService.save(any(BillDtoWoCreatedAt.class))).thenReturn(testBill);

        mockMvc.perform(post("/bills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBillDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company").value(testBill.getCompany()))
                .andExpect(jsonPath("$.amount").value(testBill.getAmount()));

        verify(billService).save(any(BillDtoWoCreatedAt.class));
    }

    @Test
    void findByUserIdAndBillDateBetweenAndType_ShouldReturnBills() throws Exception {
        List<Bill> expectedBills = List.of(testBill);
        when(billService.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                anyLong(), any(), any(), any()))
                .thenReturn(expectedBills);

        mockMvc.perform(get("/bills/find/{id}", 1L)
                        .param("startDate", "2025-01-01T00:00:00")
                        .param("endDate", "2025-01-30T00:00:00")
                        .param("type", Type.EXPENSE.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company").value(testBill.getCompany()))
                .andExpect(jsonPath("$[0].amount").value(testBill.getAmount()));

        verify(billService).findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                anyLong(), any(), any(), any());
    }

    @Test
    void delete_ShouldReturnOk() throws Exception {
        doNothing().when(billService).delete(1L);

        mockMvc.perform(delete("/bills/{id}", 1L))
                .andExpect(status().isOk());

        verify(billService).delete(1L);
    }
}