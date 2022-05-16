package com.example.kvjp.repository;

import com.example.kvjp.model.ElectricBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricBillRepository extends JpaRepository<ElectricBill, Integer> {
    ElectricBill findByNameAndAndStatus(String name, int status);

    ElectricBill findByName(String name);

    ElectricBill findByIdAndStatus(int id, int status);
}
