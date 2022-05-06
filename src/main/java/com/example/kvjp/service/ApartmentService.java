package com.example.kvjp.service;

import com.example.kvjp.constant.EStatus;
import com.example.kvjp.dto.request.ApartmentRequestDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Transactional
    public Apartment create(ApartmentRequestDto apartmentRequestDto) {
        Apartment apartment = new Apartment(
                apartmentRequestDto.getName(),
                apartmentRequestDto.getStatus(),
                apartmentRequestDto.getArea(),
                apartmentRequestDto.getBedroom(),
                apartmentRequestDto.getBathroom(),
                apartmentRequestDto.getImage(),
                apartmentRequestDto.getPrice()
        );

        apartmentRepository.save(apartment);
        return apartment;
    }

    public List<Apartment> getAll() {
        List<Apartment> apartments = apartmentRepository.findAll();
        return apartments;
    }

    public Apartment getByName(String name) {
        Apartment apartment = apartmentRepository.findByName(name);
        return apartment;
    }

    @Transactional
    public void deleteApartment(Apartment apartment) {
        apartment.setStatus(EStatus.DISABLE.getId());
        apartmentRepository.save(apartment);
    }

    @Transactional
    public Apartment updateApartment(ApartmentRequestDto apartmentRequestDto, Apartment apartment) {

        if (!apartment.getName().equalsIgnoreCase(apartmentRequestDto.getName())){
            if(checkDuplicateName(apartmentRequestDto.getName()) == true) {
                return null;
            }
            apartment.update(apartmentRequestDto);
        }
        apartment.update(apartmentRequestDto);
        apartmentRepository.save(apartment);

        return apartment;
    }

    public boolean checkDuplicateName(String name) {
        if (apartmentRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }

    public Apartment getById(int id) {
        Apartment apartment = apartmentRepository.findById(id);
        return apartment;
    }

    public Apartment getById(Long id) {
        Apartment apartment = apartmentRepository.findById(id).get();
        return apartment;
    }
}