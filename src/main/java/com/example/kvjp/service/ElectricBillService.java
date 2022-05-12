package com.example.kvjp.service;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.dto.request.ElectricBillRequestDto;
import com.example.kvjp.model.ElectricBill;
import com.example.kvjp.repository.ElectricBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ElectricBillService {
    @Autowired
    ElectricBillRepository electricBillRepository;


    public boolean checkDuplicateName(String name) {
        if (electricBillRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }


    public ElectricBill createElectricBill(ElectricBillRequestDto electricBillRequestDto, String nameReceivable) {
        ElectricBill electricBill =  new ElectricBill().builder()
                .name(nameReceivable)
                .electricNumberNew(electricBillRequestDto.getElectricNumberNew())
                .unit(electricBillRequestDto.getUnit())
                .status(EProcess.PROCESSING.getId())
                .build();
        electricBillRepository.save(electricBill);
        return electricBill;
    }

    public ElectricBill createElectricBill(ElectricBillRequestDto electricBillRequestDto) {
        ElectricBill electricBill =  new ElectricBill().builder()
                .name(electricBillRequestDto.getName())
                .electricNumberNew(electricBillRequestDto.getElectricNumberNew())
                .unit(electricBillRequestDto.getUnit())
                .status(EProcess.PROCESSING.getId())
                .build();
        electricBillRepository.save(electricBill);
        return electricBill;
    }

    @Transactional
    public ElectricBill updateElectricBill(ElectricBillRequestDto electricBillRequestDto, ElectricBill electricBill) {
        if (!electricBill.getName().equalsIgnoreCase(electricBillRequestDto.getName()) && electricBill.getStatus() == EProcess.PROCESSING.getId()) {
            if (checkDuplicateName(electricBill.getName()) == true) {
                return null;
            }
            electricBill.update(electricBillRequestDto, electricBill);
            electricBillRepository.save(electricBill);
        }
        electricBill.update(electricBillRequestDto, electricBill);
        electricBillRepository.save(electricBill);

        return electricBill;
    }

    @Transactional
    public void disableElectricBill(ElectricBill electricBill) {
        electricBill.setStatus(EProcess.DONE.getId());
        electricBillRepository.save(electricBill);
    }

    public ElectricBill getById(Long id) {
        ElectricBill electricBill = electricBillRepository.findById(id).get();
        return electricBill;
    }

    public ElectricBill getByName(String name) {
        ElectricBill electricBill = electricBillRepository.findByName(name);
        return electricBill;
    }

    public List<ElectricBill> getAllElectricBill() {
        return electricBillRepository.findAll();
    }
}
