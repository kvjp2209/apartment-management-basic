package com.example.kvjp.repository;

import com.example.kvjp.model.Leases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasesRepository extends JpaRepository<Leases, Integer> {
}
