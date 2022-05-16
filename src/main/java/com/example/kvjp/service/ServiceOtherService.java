package com.example.kvjp.service;

import com.example.kvjp.model.ServiceOther;
import com.example.kvjp.repository.ServiceOtherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ServiceOtherService {
    @Autowired
    ServiceOtherRepository serviceOtherRepository;

    public Set<ServiceOther> getServiceListById(Set<Integer> ids) {
        Set<ServiceOther> serviceOthers = serviceOtherRepository.findAllByIdIn(ids);
        return serviceOthers;
    }

    public int getTotalServicePriceIn(Set<ServiceOther> serviceOthers) {
        int sum = 0;

        for (ServiceOther serviceOther : serviceOthers) {
            sum += serviceOther.getPrice();
        }
        return sum;
    }
}
