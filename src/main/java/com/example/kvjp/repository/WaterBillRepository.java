package com.example.kvjp.repository;

import com.example.kvjp.model.WaterBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterBillRepository extends JpaRepository<WaterBill, Integer> {
    WaterBill findByName(String name);

    WaterBill findByNameAndStatus(String name, int status);

    WaterBill findByIdAndStatus(int id, int status);
}
