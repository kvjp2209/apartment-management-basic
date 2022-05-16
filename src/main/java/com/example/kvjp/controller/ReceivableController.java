package com.example.kvjp.controller;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.dto.request.ReceivableRequestDto;
import com.example.kvjp.dto.response.ReceivableResponseDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.model.Leases;
import com.example.kvjp.model.Receivable;
import com.example.kvjp.service.LeasesService;
import com.example.kvjp.service.ReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/receivable")
public class ReceivableController extends ResponseController {
    @Autowired
    ReceivableService receivableService;
    @Autowired
    LeasesService leasesService;

    @PostMapping
    public ResponseEntity<ResponseDto> createReceivable(@Valid @RequestBody ReceivableRequestDto receivableRequestDto) {
        try {
            Leases leases = leasesService.getByIdLeases(receivableRequestDto.getLeasesId());
            if (leases == null) {
                return responseUtil.getNotFoundResponse("Not found Lease!!!");
            }
            if (receivableService.getByNameReceivableAndStatus(receivableRequestDto.getName(), EProcess.PROCESSING.getId())
                            != null) {
                return responseUtil.getBadRequestResponse("Name existed and processing!!!");
            }
            Receivable receivable = receivableService.createReceivable(receivableRequestDto, leases);
            return responseUtil.getSuccessResponse(receivable);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateReceivable(@Valid @RequestBody ReceivableRequestDto receivableRequestDto, @PathVariable int id) {
        try {
            Receivable receivable = receivableService.getByIdReceivable(id);
            if (receivable == null) {
                return responseUtil.getSuccessResponse("not found receivable");
            }
            if (receivableService.updateReceivable(receivableRequestDto, receivable) == null) {
                return responseUtil.getBadRequestResponse("out of date or this name existed");
            }
            return responseUtil.getSuccessResponse(receivable);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteReceivable(@PathVariable int id) {
        try {
            Receivable receivable = receivableService.getByIdReceivable(id);
            if (receivable == null) {
                return responseUtil.getSuccessResponse("not found receivable");
            }
            receivableService.disableReceivable(receivable);
            return responseUtil.getSuccessResponse(receivable);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto> getAllReceivable() {
        try {
            List<Receivable> receivables = receivableService.findAllReceivables();
            if(receivables.size() == 0) {
                return responseUtil.getSuccessResponse("empty");
            }
            return responseUtil.getSuccessResponse(receivables);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<ResponseDto> getAllByNameReceivable(@RequestParam(value = "name") String name) {
        try {
            List<Receivable> receivables = receivableService.findAllReceivablesByName(name);
            if(receivables.size() == 0) {
                return responseUtil.getSuccessResponse("empty");
            }
            return responseUtil.getSuccessResponse(receivables);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/{id}/detail")
    public ResponseEntity<ResponseDto> getDetailReceivableById(@PathVariable(value = "id") int id) {
        try {
            ReceivableResponseDto receivable = receivableService.getDetailById(id);
            if (receivable == null) {
                return responseUtil.getSuccessResponse("not found receivable");
            }
            return responseUtil.getSuccessResponse(receivable);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/{id}/send-mail")
    public ResponseEntity<ResponseDto> sendReceivabletoBill(@PathVariable int id) {
        try {
            Receivable receivable = receivableService.getByIdReceivable(id);
            if (receivable == null) {
                return responseUtil.getSuccessResponse("not found receivable");
            }
            receivableService.sendMailToUser(receivable);
            return responseUtil.getSuccessResponse(receivableService.formatEmailReceivable(receivable));
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }


}