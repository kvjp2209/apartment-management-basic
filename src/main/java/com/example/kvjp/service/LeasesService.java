package com.example.kvjp.service;

import com.example.kvjp.constant.EStatus;
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

    public List<Leases> getAllLeases() {
        List<Leases> leases = leasesRepository.findAll();
        return leases;
    }

    public Leases createLeases(LeasesRequestDto leasesRequestDto, Apartment apartment, Tenant tenant) {

        Leases leases = new Leases(
                leasesRequestDto.getDate(),
                leasesRequestDto.getStatus(),
                leasesRequestDto.getPrice(),
                apartment,
                tenant);

        leasesRepository.save(leases);
        return leases;
    }

    public Leases updateLeases(LeasesRequestDto leasesRequestDto, Leases leases, Apartment apartment, Tenant tenant) {
        leases.setDate(leasesRequestDto.getDate());
        leases.setStatus(leasesRequestDto.getStatus());
        leases.setPrice(leasesRequestDto.getPrice());
        leases.setApartment(apartment);
        leases.setTenant(tenant);
        leasesRepository.save(leases);
        return leases;
    }

    public Leases getByIdLeases(Integer id) {
        if (leasesRepository.findById(id).isPresent()) {
            return leasesRepository.findById(id).get();
        }
        return null;
    }

    public void deleteLeases(Leases leases) {
        leases.setStatus(EStatus.DISABLE.getId());
        leasesRepository.save(leases);
    }

}
