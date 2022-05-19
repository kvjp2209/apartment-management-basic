package com.example.kvjp.service;


import static org.junit.Assert.assertEquals;


import com.example.kvjp.constant.EProcess;
import com.example.kvjp.model.ElectricBill;
import com.example.kvjp.repository.ElectricBillRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ElectricBillServiceTest {
    @InjectMocks
    ElectricBillService electricBillService;

    @Mock
    ElectricBillRepository electricBillRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllElectricBillTest() {
        List<ElectricBill> electricBills = new ArrayList<ElectricBill>();
        ElectricBill electricBill = new ElectricBill("101", 99, 99, 2, EProcess.PROCESSING.getId());
        ElectricBill electricBill1 = new ElectricBill("102", 0, 0, 3, EProcess.PROCESSING.getId());
        electricBills.add(electricBill);
        electricBills.add(electricBill1);


        when(electricBillRepository.findAll()).thenReturn(electricBills);

        List<ElectricBill> list = electricBillService.getAllElectricBill();

        assertEquals(2, electricBills.size());
        verify(electricBillRepository, times(1)).findAll();
    }
}
