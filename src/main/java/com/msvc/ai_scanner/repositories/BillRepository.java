package com.msvc.ai_scanner.repositories;

import com.msvc.ai_scanner.model.entities.Bill;
import com.msvc.ai_scanner.model.enums.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {
    List<Bill> findByUserIdAndBillDateBetweenAndTypeOrderByBillDateDesc(Long userId, LocalDateTime begin, LocalDateTime end, Type type);
}
