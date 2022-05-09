package com.example.kvjp.controller;

import com.example.kvjp.dto.request.TenantRequestDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.model.Tenant;
import com.example.kvjp.service.ApartmentService;
import com.example.kvjp.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tenants")
public class TenantController extends ResponseController {
    @Autowired
    TenantService tenantService;
    @Autowired
    ApartmentService apartmentService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> createTenant(@Valid @RequestBody TenantRequestDto tenantRequestDto) {
        try {
            Apartment apartment = apartmentService.getByName(tenantRequestDto.getApartmentName());
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("Not found apartment");
            }
            Tenant tenant = tenantService.create(tenantRequestDto, apartment);
            return responseUtil.getSuccessResponse(tenant);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllTenant() {
        try {
            List<Tenant> tenants = tenantService.getAll();
            return responseUtil.getSuccessResponse(tenants);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getTenant(@PathVariable("id") Long id) {
        try {
            Tenant tenant = tenantService.getById(id);
            if (tenant == null) {
                return responseUtil.getNotFoundResponse("not found by this id");
            }
            return responseUtil.getSuccessResponse(tenant);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteTenant(@PathVariable("id") Long id) {
        try {
            Tenant tenant = tenantService.getById(id);
            if (tenant == null) {
                return responseUtil.getNotFoundResponse("not found by this id");
            }
            tenantService.delete(tenant);
            return responseUtil.getSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateTenant(@PathVariable("id") Long id, @RequestBody TenantRequestDto tenantRequestDto) {
        try {
            Tenant tenant = tenantService.getById(id);
            if (tenant == null) {
                return responseUtil.getNotFoundResponse("not found by this id");
            }
            Apartment apartment = apartmentService.getByName(tenantRequestDto.getApartmentName());
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("not found by this apartment");
            }
            Tenant result = tenantService.update(tenantRequestDto, tenant, apartment);
            if (result == null) {
                return responseUtil.getBadRequestResponse("Identify card is existed!");
            }
            return responseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }
}
