package com.example.kvjp.model;

import com.example.kvjp.dto.request.TenantRequestDto;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@Table(name = "tenant")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    private Timestamp dob;

    private String phone;

    private int gender;

    private String idCard;

    private int status;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Tenant() {
    }



    public void update(TenantRequestDto tenantRequestDto, Apartment apartment) {
        this.name = tenantRequestDto.getName();
        this.email = tenantRequestDto.getEmail();
        this.age = tenantRequestDto.getAge();
        this.dob = tenantRequestDto.getDob();
        this.phone = tenantRequestDto.getPhone();
        this.gender = tenantRequestDto.getGender();
        this.idCard = tenantRequestDto.getIdCard();
        this.status = tenantRequestDto.getStatus();
        this.apartment = apartment;
    }

    public Tenant(String name, String email, int age, Timestamp dob, String phone, int gender, String idCard, int status, Apartment apartment) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
        this.phone = phone;
        this.gender = gender;
        this.idCard = idCard;
        this.status = status;
        this.apartment = apartment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
