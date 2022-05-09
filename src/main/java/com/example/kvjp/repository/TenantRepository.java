package com.example.kvjp.repository;

import com.example.kvjp.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    @Query(value = "SELECT te FROM Tenant te " +
            "JOIN FETCH te.apartment a " +
            "WHERE te.apartment.id = :id")
    List<Tenant> findAllByApartmentId(Long id);

    Tenant findByIdCard(String idCard);

}
