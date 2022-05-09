package com.example.kvjp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class LeasesRequestDto {
    private Timestamp date;

    private int status;

    private int price;

    @JsonProperty("apartment_id")
    private Long apartmentId;

    @JsonProperty("tenant_id")
    private Long tenantId;

    public LeasesRequestDto() {
    }

    public LeasesRequestDto(Timestamp date, int status, int price, Long apartmentId, Long tenantId) {
        this.date = date;
        this.status = status;
        this.price = price;
        this.apartmentId = apartmentId;
        this.tenantId = tenantId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
