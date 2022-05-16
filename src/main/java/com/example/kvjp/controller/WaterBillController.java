package com.example.kvjp.controller;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.dto.request.WaterBillRequestDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.model.WaterBill;
import com.example.kvjp.service.WaterBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/water")
public class WaterBillController extends ResponseController {
    @Autowired
    WaterBillService waterBillService;

    @PostMapping
    public ResponseEntity<ResponseDto> createWaterBill(@Valid @RequestBody WaterBillRequestDto waterBillRequestDto) {
        try {
            if (waterBillService.getByNameAndStatus(waterBillRequestDto.getName(), EProcess.PROCESSING.getId()) != null) {
                return responseUtil.getBadRequestResponse("Name existed and processing!!!");
            }
            WaterBill waterBill = waterBillService.createWaterBill(waterBillRequestDto);
            return responseUtil.getSuccessResponse(waterBill);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateWaterBill(@Valid @RequestBody WaterBillRequestDto waterBillRequestDto, @PathVariable Integer id) {
        try {
            WaterBill waterBill = waterBillService.getByIdWaterBill(id);
            if (waterBill == null) {
                return responseUtil.getNotFoundResponse("Not found water bill");
            }
            WaterBill result = waterBillService.updateWaterBill(waterBillRequestDto, waterBill);
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
    public ResponseEntity<ResponseDto> getAllWaterBill() {
        try {
            List<WaterBill> waterBills = waterBillService.getAllWaterBill();
            return responseUtil.getSuccessResponse(waterBills);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteWaterBill(@PathVariable Integer id) {
        try {
            WaterBill waterBill = waterBillService.getByIdWaterBill(id);
            if (waterBill == null) {
                return responseUtil.getNotFoundResponse("Not found this water bill");
            }
            waterBillService.disableWaterBill(waterBill);
            return responseUtil.getSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

}
