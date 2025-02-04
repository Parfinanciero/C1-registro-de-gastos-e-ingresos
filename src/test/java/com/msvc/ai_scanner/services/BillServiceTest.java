package com.msvc.ai_scanner.services;

import com.msvc.ai_scanner.dto.request.BillDtoWoCreatedAt;
import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Category;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.repositories.BillRepository;
import com.msvc.ai_scanner.services.exceptions.BillNotFoundException;
import com.msvc.ai_scanner.services.exceptions.CantCreateBillException;
import com.msvc.ai_scanner.services.exceptions.UserDontHaveRegistersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;
    private Bill testBill;
    private BillDtoWoCreatedAt testBillDto;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @BeforeEach
    void setUp() {
        // Inicializar datos de prueba
        testBill = Bill.builder()
                .id("1")
                .company("Test Company")
                .category(Category.ENTERTAINMENT)
                .type(Type.EXPENSE)
                .amount(100L)
                .userId(1L)
                .build();

        testBillDto = new BillDtoWoCreatedAt(
                "Test Company",
                Category.ENTERTAINMENT,
                LocalDateTime.now(),
                Type.EXPENSE,
                100L,
                1L
        );

        beginDate = LocalDateTime.now().minusDays(30);
        endDate = LocalDateTime.now();
    }

    @Test
    void findByUserIdAndBillDateBetweenAndType_WhenBillsExist_ShouldReturnBills() {
        // Arrange
        List<Bill> expectedBills = List.of(testBill);
        when(billRepository.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                anyLong(), any(LocalDateTime.class), any(LocalDateTime.class), any(Type.class)))
                .thenReturn(expectedBills);

        // Act
        List<Bill> result = billService.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                1L, beginDate, endDate, Type.EXPENSE);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testBill, result.get(0));
        verify(billRepository).findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                1L, beginDate, endDate, Type.EXPENSE);
    }

    @Test
    void findByUserIdAndBillDateBetweenAndType_WhenNoBills_ShouldThrowException() {
        // Arrange
        when(billRepository.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                anyLong(), any(LocalDateTime.class), any(LocalDateTime.class), any(Type.class)))
                .thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(UserDontHaveRegistersException.class, () ->
                billService.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(
                        1L, beginDate, endDate, Type.EXPENSE)
        );
    }

    @Test
    void readAll_ShouldReturnAllBills() {
        // Arrange
        List<Bill> expectedBills = List.of(testBill);
        when(billRepository.findAll()).thenReturn(expectedBills);

        // Act
        List<Bill> result = billService.readAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedBills, result);
        verify(billRepository).findAll();
    }

    @Test
    void save_WhenValidData_ShouldSaveBill() {
        // Arrange
        when(billRepository.save(any(Bill.class))).thenReturn(testBill);

        // Act
        Bill result = billService.save(testBillDto);

        // Assert
        assertNotNull(result);
        assertEquals(testBill.getCompany(), result.getCompany());
        assertEquals(testBill.getAmount(), result.getAmount());
        verify(billRepository).save(any(Bill.class));
    }

    @Test
    void save_WhenRepositoryThrowsException_ShouldThrowCantCreateBillException() {
        // Arrange
        when(billRepository.save(any(Bill.class))).thenThrow(new CantCreateBillException("The bill could not be created, try it later :)"));

        // Act & Assert
        assertThrows(CantCreateBillException.class, () ->
                billService.save(testBillDto)
        );
    }

    @Test
    void delete_WhenBillExists_ShouldDeleteBill() {
        // Arrange
        when(billRepository.findById("1")).thenReturn(Optional.of(testBill));
        doNothing().when(billRepository).delete(any(Bill.class));

        // Act
        billService.delete("1");

        // Assert
        verify(billRepository).findById("1");
        verify(billRepository).delete(testBill);
    }

    @Test
    void delete_WhenBillDoesNotExist_ShouldThrowException() {
        // Arrange
        when(billRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BillNotFoundException.class, () ->
                billService.delete("1")
        );
    }
}
