package com.example.kvjp.repository;

import com.example.kvjp.model.WaterBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterBillRepository extends JpaRepository<WaterBill, Long> {
    WaterBill findByName(String name);
}
