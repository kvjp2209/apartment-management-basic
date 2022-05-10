package com.example.kvjp.service;

import com.example.kvjp.constant.EGender;
import com.example.kvjp.constant.EStatus;
import com.example.kvjp.dto.request.TenantRequestDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.model.Tenant;
import com.example.kvjp.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TenantService {
    @Autowired
    TenantRepository tenantRepository;

    public List<Tenant> getAllTenant() {
        return tenantRepository.findAll();
    }

    public Tenant getByIdTenant(Long id) {
        if (tenantRepository.findById(id).isPresent()) {
            return tenantRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public Tenant createTenant(TenantRequestDto tenantRequestDto, Apartment apartment) {
        Tenant tenant = new Tenant(tenantRequestDto.getName(),
                tenantRequestDto.getEmail(),
                tenantRequestDto.getAge(),
                tenantRequestDto.getDob(),
                tenantRequestDto.getPhone(),
                tenantRequestDto.getGender(),
                tenantRequestDto.getIdCard(),
                tenantRequestDto.getStatus());
        tenantRepository.save(tenant);
        return tenant;
    }

    public void deleteTenant(Tenant tenant) {
        tenant.setStatus(EStatus.DISABLE.getId());
        tenantRepository.save(tenant);
    }

    @Transactional
    public Tenant updateTenant(TenantRequestDto tenantRequestDto, Tenant tenant, Apartment apartment) {
        if (!tenant.getIdCard().equalsIgnoreCase(tenantRequestDto.getIdCard()) == true) {
            if (checkDuplicateIdCard(tenantRequestDto.getIdCard()) == false) {
                return null;
            }
            tenant.update(tenantRequestDto, apartment);
            tenantRepository.save(tenant);
            return tenant;
        }
        tenant.update(tenantRequestDto, apartment);
        tenantRepository.save(tenant);
        return tenant;
    }

    public Boolean checkDuplicateIdCard(String idCard) {
        if (tenantRepository.findByIdCard(idCard) != null) {
            return false;
        }
        return true;
    }

//    public Boolean checkDuplicateEmail(String email) {
//
//    }

}
