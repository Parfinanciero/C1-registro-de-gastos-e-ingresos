package com.msvc.ai_scanner.controllers;

import com.msvc.ai_scanner.dto.request.BillDtoWoCreatedAt;
import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.services.BillService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bill> createBill(@RequestBody @Valid BillDtoWoCreatedAt bill) {
        return new ResponseEntity<>(billService.save(bill), HttpStatus.CREATED);
    }

    @GetMapping("find/{id}")localhost:8039/bills
    public ResponseEntity<List<Bill>> findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(@PathVariable Long id,
                                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDate,
                                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDate,
                                                                                               @RequestParam Type type
                                                                                               ) {
        return ResponseEntity.ok().body(billService.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(id, startDate, endDate, type));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bill>> findAll() {
        return ResponseEntity.ok(billService.readAll());
    }

    @GetMapping
    public ResponseEntity<?> pruebaDeploy(){
        return new ResponseEntity<>(Collections.singletonMap("respuesta", "hola santi feo"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        billService.delete(id);
        return ResponseEntity.ok().build();
    }
}
