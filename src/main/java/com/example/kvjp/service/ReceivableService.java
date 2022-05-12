package com.example.kvjp.service;
import com.example.kvjp.model.Receivable;
import com.example.kvjp.repository.ElectricBillRepository;
import com.example.kvjp.repository.ReceivableRepository;
import com.example.kvjp.repository.WaterBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceivableService {
    @Autowired
    private WaterBillRepository waterBillRepository;

    @Autowired
    private ElectricBillRepository electricBillRepository;

    @Autowired
    private ReceivableRepository receivableRepository;

}
