package com.example.kvjp.service;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.dto.request.ElectricBillRequestDto;
import com.example.kvjp.dto.request.WaterBillRequestDto;
import com.example.kvjp.model.ElectricBill;
import com.example.kvjp.model.WaterBill;
import com.example.kvjp.repository.WaterBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WaterBillService {
    @Autowired
    WaterBillRepository waterBillRepository;

    public boolean checkDuplicateName(String name) {
        if (waterBillRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public WaterBill createWaterBill(WaterBillRequestDto waterBillRequestDto, String nameReceivable) {
        WaterBill waterBill = new WaterBill().builder()
                .name(nameReceivable)
                .waterNumberNew(waterBillRequestDto.getWaterNumberNew())
                .unit(waterBillRequestDto.getUnit())
                .status(EProcess.PROCESSING.getId())
                .build();
        waterBillRepository.save(waterBill);
        return waterBill;
    }

    @Transactional
    public WaterBill createWaterBill(WaterBillRequestDto waterBillRequestDto) {
        WaterBill waterBill = new WaterBill().builder()
                .name(waterBillRequestDto.getName())
                .waterNumberNew(waterBillRequestDto.getWaterNumberNew())
                .unit(waterBillRequestDto.getUnit())
                .status(EProcess.PROCESSING.getId())
                .build();
        waterBillRepository.save(waterBill);
        return waterBill;
    }

    @Transactional
    public WaterBill updateWaterBill(WaterBillRequestDto waterBillRequestDto, WaterBill waterBill) {
        if (!waterBill.getName().equalsIgnoreCase(waterBillRequestDto.getName()) && waterBill.getStatus() == EProcess.PROCESSING.getId()) {
            if (checkDuplicateName(waterBill.getName()) == true) {
                return null;
            }
            waterBill.update(waterBillRequestDto, waterBill);
            waterBillRepository.save(waterBill);
        }
        waterBill.update(waterBillRequestDto, waterBill);
        waterBillRepository.save(waterBill);

        return waterBill;
    }

    @Transactional
    public void disableWaterBill(WaterBill waterBill) {
        waterBill.setStatus(EProcess.DONE.getId());
        waterBillRepository.save(waterBill);
    }

    public WaterBill getById(Long id) {
        WaterBill waterBill = waterBillRepository.getById(id);
        return waterBill;
    }

    public WaterBill getByName(String name) {
        WaterBill waterBill = waterBillRepository.findByName(name);
        return waterBill;
    }

    public List<WaterBill> getAllWaterBill() {
        return waterBillRepository.findAll();
    }
}
