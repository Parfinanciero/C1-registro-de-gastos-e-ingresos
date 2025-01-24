package com.msvc.ai_scanner.services;

import com.msvc.ai_scanner.dto.request.BillDtoWoCreatedAt;
import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(Long userId, LocalDateTime beginDate, LocalDateTime endDate, Type type) {
        return billRepository.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(userId, beginDate, endDate, type);
    }

    public List<Bill> readAll(){
        return billRepository.findAll();
    }

    public Bill save(BillDtoWoCreatedAt billDto){
        Bill newBill = Bill.builder()
                .company(billDto.getCompany())
                .category(billDto.getCategory())
                .type(billDto.getType())
                .amount(billDto.getAmount())
                .userId(billDto.getUserId())
                .build();
        return billRepository.save(newBill);
    }
}
