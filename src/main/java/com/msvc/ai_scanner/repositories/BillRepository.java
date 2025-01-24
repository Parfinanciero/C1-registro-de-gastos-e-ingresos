package com.msvc.ai_scanner.repositories;

import com.msvc.ai_scanner.model.entities.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BillRepository extends MongoRepository<Bill, Long> {
    List<Bill> findBy
}
