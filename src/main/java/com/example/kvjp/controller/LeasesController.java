package com.example.kvjp.controller;

import com.example.kvjp.dto.request.LeasesRequestDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.model.Leases;
import com.example.kvjp.model.Tenant;
import com.example.kvjp.service.ApartmentService;
import com.example.kvjp.service.LeasesService;
import com.example.kvjp.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/leases")
public class LeasesController extends ResponseController {
    @Autowired
    TenantService tenantService;
    @Autowired
    LeasesService leasesService;
    @Autowired
    ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody LeasesRequestDto leasesRequestDto) {
        Apartment apartment = apartmentService.getById(leasesRequestDto.getApartmentId());
        if(apartment == null) {
            return responseUtil.getNotFoundResponse("Not found Apartment");
        }
        Tenant tenant = tenantService.getById(leasesRequestDto.getTenantId());
        if(tenant == null) {
            return responseUtil.getNotFoundResponse("Not found Tenent");
        }

        Leases leases = leasesService.create(leasesRequestDto, apartment, tenant);

        return responseUtil.getSuccessResponse(leases);
    }
}
