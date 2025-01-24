package com.msvc.ai_scanner.repositories;

import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends MongoRepository<Bill, Long> {
    List<Bill> findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(Long userId, LocalDateTime begin, LocalDateTime end, Type type);
}
