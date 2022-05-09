package com.example.kvjp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


public class TenantRequestDto {
    private String name;

    private String email;

    private int age;

    private Timestamp dob;

    private String phone;

    private int gender;

    @JsonProperty("id_card")
    private String idCard;

    private int status;

    @JsonProperty("apartment_name")
    private String apartmentName;

    public TenantRequestDto() {
    }

    public TenantRequestDto(String name, String email, int age, Timestamp dob, String phone, int gender, String idCard, int status, String apartmentName) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
        this.phone = phone;
        this.gender = gender;
        this.idCard = idCard;
        this.status = status;
        this.apartmentName = apartmentName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }
}
