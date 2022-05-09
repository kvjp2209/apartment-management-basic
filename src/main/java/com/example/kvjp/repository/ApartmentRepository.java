package com.example.kvjp.repository;

import com.example.kvjp.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Apartment findByName(String name);

    @Query(value = "select a from Apartment a " +
            "where a.id = :id")
    Apartment findById(int id);

}
