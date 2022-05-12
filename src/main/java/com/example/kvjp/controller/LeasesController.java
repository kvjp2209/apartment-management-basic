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
import java.util.List;

@RestController
@RequestMapping(value = "/api/leases")
public class LeasesController extends ResponseController {
    @Autowired
    TenantService tenantService;
    @Autowired
    LeasesService leasesService;
    @Autowired
    ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<ResponseDto> createLeases(@Valid @RequestBody LeasesRequestDto leasesRequestDto) {
        try {
            Apartment apartment = apartmentService.getByIdApartment(leasesRequestDto.getApartmentId());
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("Not found Apartment");
            }
            Tenant tenant = tenantService.getByIdTenant(leasesRequestDto.getTenantId());
            if (tenant == null) {
                return responseUtil.getNotFoundResponse("Not found Tenent");
            }

            Leases leases = leasesService.createLeases(leasesRequestDto, apartment, tenant);

            return responseUtil.getSuccessResponse(leases);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateLeases(@PathVariable("id") Long id, @RequestBody LeasesRequestDto leasesRequestDto) {
        try {
            Leases leases = leasesService.getByIdLeases(id);
            if (leases == null) {
                return responseUtil.getNotFoundResponse("not found leases");
            }
            Apartment apartment = apartmentService.getByIdApartment(leasesRequestDto.getApartmentId());
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("Not found Apartment");
            }
            Tenant tenant = tenantService.getByIdTenant(leasesRequestDto.getTenantId());
            if (tenant == null) {
                return responseUtil.getNotFoundResponse("Not found Tenant");
            }

            leasesService.updateLeases(leasesRequestDto, leases, apartment, tenant);
            return responseUtil.getSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteLease(@PathVariable("id") Long id) {
        try {
            Leases leases = leasesService.getByIdLeases(id);
            if (leases == null) {
                return responseUtil.getNotFoundResponse("not found by this id!");
            }
            leasesService.deleteLeases(leases);
            return responseUtil.getSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getByIdLeases(@PathVariable("id") Long id) {
        try {
            Leases leases = leasesService.getByIdLeases(id);
            if (leases == null) {
                return responseUtil.getNotFoundResponse("not found by this id!");
            }
            return responseUtil.getSuccessResponse(leases);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllLeases() {
        try {
            List<Leases> leases = leasesService.getAllLeases();
            return responseUtil.getSuccessResponse(leases);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }
}
