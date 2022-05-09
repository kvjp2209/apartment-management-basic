package com.example.kvjp.service;

import com.example.kvjp.dto.request.LeasesRequestDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.model.Leases;
import com.example.kvjp.model.Tenant;
import com.example.kvjp.repository.LeasesRepository;
import com.example.kvjp.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeasesService {
    @Autowired
    LeasesRepository leasesRepository;

    @Autowired
    TenantRepository tenantRepository;
    public List<Leases> getAll() {
        List<Leases> leases = leasesRepository.findAll();
        return leases;
    }

    public Leases create(LeasesRequestDto leasesRequestDto, Apartment apartment, Tenant tenant) {

        Leases leases = new Leases(
                leasesRequestDto.getDate(),
                leasesRequestDto.getStatus(),
                leasesRequestDto.getPrice(),
                apartment,
                tenant);

        leasesRepository.save(leases);
        return leases;
    }


}
