package com.example.kvjp.controller;

import com.example.kvjp.dto.request.ApartmentRequestDto;
import com.example.kvjp.dto.response.ApartmentResponseDto;
import com.example.kvjp.dto.response.ResponseDto;
import com.example.kvjp.model.Apartment;
import com.example.kvjp.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/apartments")
public class ApartmentController extends ResponseController {
    @Autowired
    ApartmentService apartmentService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> createApartment(@Valid @RequestBody ApartmentRequestDto apartmentRequestDto) {
        try {
            if (apartmentService.getByName(apartmentRequestDto.getName()) != null) {
                return responseUtil.getBadRequestResponse("duplicate name!!!");
            }
            Apartment apartment = apartmentService.create(apartmentRequestDto);
            return responseUtil.getSuccessResponse(apartment);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto> getAllApartment() {
        try {
            List<Apartment> apartments = apartmentService.getAll();
            return responseUtil.getSuccessResponse(apartments);
        }catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getApartment(@PathVariable("id") Long id) {
        try {
            Apartment apartment = apartmentService.getById(id);
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("not found by this id");
            }
            return responseUtil.getSuccessResponse(apartment);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteApartment(@PathVariable("id") Long id) {
        try {
            Apartment apartment = apartmentService.getById(id);
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("not found by this id!");
            }
            apartmentService.deleteApartment(apartment);
            return responseUtil.getSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateApartment(@PathVariable("id") Long id, @RequestBody ApartmentRequestDto apartmentRequestDto) {
        try {
            Apartment apartment = apartmentService.getById(id);
            if (apartment == null) {
                return responseUtil.getNotFoundResponse("not found apartment!");
            }

            Apartment result = apartmentService.updateApartment(apartmentRequestDto, apartment);
            if (result == null) {
                return responseUtil.getBadRequestResponse("name existed!!!");
            }
            return responseUtil.getSuccessResponse(result);

        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }

    @GetMapping("/all-detail/{id}")
    public ResponseEntity<ResponseDto> getAllDetailApartment(@PathVariable Long id) {
        try {
            Apartment apartment = apartmentService.getById(id);
            if(apartment == null) {
                return  responseUtil.getNotFoundResponse("not found apartment!!!");
            }
            ApartmentResponseDto apartmentResponseDto = apartmentService.getApartmentDetailsById(id, apartment);
            return responseUtil.getSuccessResponse(apartmentResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return responseUtil.getInternalServerErrorResponse();
        }
    }
}
