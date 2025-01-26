package com.msvc.ai_scanner.services;

import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import com.msvc.ai_scanner.repositories.BillRepository;
import com.msvc.ai_scanner.services.exceptions.BillNotFoundException;
import com.msvc.ai_scanner.services.exceptions.CantCreateBillException;
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
        List<Bill> userBills = billRepository.findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(userId, beginDate, endDate, type);

        if (userBills.isEmpty()) {
            throw new
        }
    }

    public List<Bill> readAll(){
        return billRepository.findAll();
    }

    public Bill save(Bill bill){
        try {
            return billRepository.save(bill);
        }catch (Exception e){
            throw new CantCreateBillException("The bill could not be created, try it later :)");
        }

    }

    public void delete(Long id){
        Bill billToDelete = billRepository.findById(id).orElseThrow( () -> new BillNotFoundException("We cant delete the bill because there is no bill with the id: "+ id) );
        billRepository.delete(billToDelete);
    }
}
