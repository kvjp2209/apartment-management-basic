package com.example.kvjp.repository;

import com.example.kvjp.model.Apartment;
import com.example.kvjp.model.Leases;
import com.example.kvjp.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeasesRepository extends JpaRepository<Leases, Integer> {
    Leases findByApartmentAndTenant(Apartment apartment, Tenant tenant);
    List<Leases> findAllByApartmentAndStatus(Apartment apartment, int status);
}
