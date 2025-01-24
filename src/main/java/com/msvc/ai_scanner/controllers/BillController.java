package com.msvc.ai_scanner.controllers;

import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.services.BillService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping //cambiar la entidad original por el DTO y castearlo en el servicio //MAPPER
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        return new ResponseEntity<>(billService.save(bill), HttpStatus.CREATED);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<List<Bill>> findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(@PathVariable Long id,
                                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDate,
                                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDate,
                                                                                               @RequestParam Type type
                                                                                               ) {
        return ResponseEntity.ok().body(billService.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(id, startDate, endDate, type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
}
