package com.example.kvjp.model;

import lombok.Builder;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "leases")
@Builder
public class Leases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private int status;

    private int price;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public Leases() {
    }

    public Leases(Integer id, Date date, int status, int price, Apartment apartment, Tenant tenant) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.price = price;
        this.apartment = apartment;
        this.tenant = tenant;
    }

    public Leases(Date date, int status, int price, Apartment apartment, Tenant tenant) {
        this.date = date;
        this.status = status;
        this.price = price;
        this.apartment = apartment;
        this.tenant = tenant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}