package com.example.kvjp.repository;

import com.example.kvjp.model.Receivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivableRepository extends JpaRepository<Receivable, Long> {
}
