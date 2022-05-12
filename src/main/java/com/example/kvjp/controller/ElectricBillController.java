package com.example.kvjp.controller;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.dto.request.ElectricBillRequestDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.model.ElectricBill;
import com.example.kvjp.model.Leases;
import com.example.kvjp.service.ElectricBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/electrics")
public class ElectricBillController extends ResponseController{
    @Autowired
    ElectricBillService electricBillService;

    @PostMapping
    public ResponseEntity<ResponseDto> createElectricBill(@Valid @RequestBody ElectricBillRequestDto electricBillRequestDto) {
        try {
            ElectricBill electricBill = electricBillService.getByName(electricBillRequestDto.getName());
            if (electricBill != null) {
                if (electricBill.getStatus() == EProcess.PROCESSING.getId()) {
                    return responseUtil.getBadRequestResponse("Name existed and processing!!!");
                }
                ElectricBill electricBill1 = electricBillService.createElectricBill(electricBillRequestDto);
                return responseUtil.getSuccessResponse(electricBill1);
            }
            ElectricBill electricBill1 = electricBillService.createElectricBill(electricBillRequestDto);
            return responseUtil.getSuccessResponse(electricBill1);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateElectricBill(@Valid @RequestBody ElectricBillRequestDto electricBillRequestDto, @PathVariable Long id) {
        try {
            ElectricBill electricBill = electricBillService.getById(id);
            if (electricBill == null) {
                return responseUtil.getNotFoundResponse("Not found electricBill");
            }
            ElectricBill result = electricBillService.updateElectricBill(electricBillRequestDto, electricBill);
            if (result == null) {
                return responseUtil.getBadRequestResponse("out of date or this name existed");
            }
            return responseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto> getAllElectricBill() {
        try {
            List<ElectricBill> electricBills = electricBillService.getAllElectricBill();
            return responseUtil.getSuccessResponse(electricBills);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }
}
