package com.example.kvjp.repository;

import com.example.kvjp.model.Receivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivableRepository extends JpaRepository<Receivable, Integer> {
    Receivable findByName(String name);

    List<Receivable> findAllByName(String name);

    Receivable findByNameAndStatus(String name, int status);

    List<Receivable> findAllByStatus(int statusId);
}