package com.msvc.ai_scanner.repositories;

import com.msvc.ai_scanner.model.entities.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill, Long> {
}
