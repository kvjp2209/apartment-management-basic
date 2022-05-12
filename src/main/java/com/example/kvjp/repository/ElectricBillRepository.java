package com.example.kvjp.repository;

import com.example.kvjp.model.ElectricBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricBillRepository extends JpaRepository<ElectricBill, Long> {
    ElectricBill findByName(String name);
}
