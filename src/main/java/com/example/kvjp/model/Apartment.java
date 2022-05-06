package com.example.kvjp.model;

import com.example.kvjp.dto.request.ApartmentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int status;

    private int area;

    private int bedroom;

    private int bathroom;

    private String image;

    private int price;

    public Apartment() {
    }

    public Apartment(String name, int status, int area, int bedroom, int bathroom, String image, int price) {
        this.name = name;
        this.status = status;
        this.area = area;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.image = image;
        this.price = price;
    }

    public void update(ApartmentRequestDto apartmentRequestDto) {
        this.name = apartmentRequestDto.getName();
        this.status = apartmentRequestDto.getStatus();
        this.area = apartmentRequestDto.getArea();
        this.bedroom = apartmentRequestDto.getBedroom();
        this.bathroom = apartmentRequestDto.getBathroom();
        this.image = apartmentRequestDto.getImage();
        this.price = apartmentRequestDto.getPrice();
    }
}
