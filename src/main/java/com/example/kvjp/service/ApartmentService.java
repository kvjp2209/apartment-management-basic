package com.example.kvjp.service;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.constant.EStatus;
import com.example.kvjp.dto.request.ApartmentRequestDto;
import com.example.kvjp.dto.response.ApartmentResponseDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.model.Leases;
import com.example.kvjp.model.Tenant;
import com.example.kvjp.repository.ApartmentRepository;
import com.example.kvjp.repository.LeasesRepository;
import com.example.kvjp.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    LeasesRepository leasesRepository;

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
        return apartmentRepository.findAll();
    }

    public Apartment getApartmentByName(String name) {
        return apartmentRepository.findByName(name);
    }

    public ApartmentResponseDto getApartmentDetails(Apartment apartment) {
        Leases leases = leasesRepository.findAllByApartmentAndStatus(apartment, EProcess.PROCESSING.getId()).get(0);

        ApartmentResponseDto apartmentResponseDto = new ApartmentResponseDto(
                apartment.getId(),
                apartment.getName(),
                apartment.getStatus(),
                apartment.getArea(),
                apartment.getBedroom(),
                apartment.getBathroom(),
                apartment.getImage(),
                apartment.getPrice(),
                leases.getTenant()
        );
        return apartmentResponseDto;
    }

    public List<Apartment> getApartmentByNameContaining(String name) {
        return apartmentRepository.findByNameContaining(name);
    }

    @Transactional
    public void deleteApartment(Apartment apartment) {
        apartment.setStatus(EStatus.DISABLE.getId());
        apartmentRepository.save(apartment);
    }

    @Transactional
    public Apartment updateApartment(ApartmentRequestDto apartmentRequestDto, Apartment apartment) {

        if (!apartment.getName().equalsIgnoreCase(apartmentRequestDto.getName())) {
            if (checkDuplicateName(apartmentRequestDto.getName()) == true) {
                return null;
            }
            apartment.update(apartmentRequestDto);
            apartmentRepository.save(apartment);

            return apartment;
        }
        apartment.update(apartmentRequestDto);
        apartmentRepository.save(apartment);

        return apartment;
    }

    public boolean checkDuplicateName(String name) {
        return apartmentRepository.findByName(name) != null;
    }

    public Apartment getById(int id) {
        Apartment apartment = apartmentRepository.findById(id);
        return apartment;
    }

    public Apartment getByIdApartment(Integer id) {
        if (apartmentRepository.findById(id).isPresent()) {
            return apartmentRepository.findById(id).get();
        }
        return null;
    }
}