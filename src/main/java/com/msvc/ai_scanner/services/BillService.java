package com.msvc.ai_scanner.services;

import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> readAll(){
        return billRepository.findAll();
    }

    public Bill save(Bill bill){
        return billRepository.save(bill);
    }
}
