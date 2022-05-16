package com.example.kvjp.model;

import com.example.kvjp.dto.request.ElectricBillRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "electric_bill")
@Builder
public class ElectricBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_at")
    @JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at")
    @JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
    @UpdateTimestamp
    private Date updateAt;

    @Column(name = "electric_number_old")
    private int electricNumberOld;

    @Column(name = "electric_number_new")
    private int electricNumberNew;

    @Column(name = "unit")
    private int unit;

    @Column(name = "status")
    private int status;

    public void
    update(ElectricBillRequestDto electricBillRequestDto, ElectricBill electricBill) {
        this.name = electricBillRequestDto.getName();
        this.createAt = electricBill.getCreateAt();
        this.updateAt = electricBillRequestDto.getUpdateAt();
        this.electricNumberOld  = electricBill.getElectricNumberNew();
        this.electricNumberNew = electricBillRequestDto.getElectricNumberNew();
        this.unit = electricBillRequestDto.getUnit();
        this.status = electricBill.getStatus();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getElectricNumberOld() {
        return electricNumberOld;
    }

    public void setElectricNumberOld(int electricNumberOld) {
        this.electricNumberOld = electricNumberOld;
    }

    public int getElectricNumberNew() {
        return electricNumberNew;
    }

    public void setElectricNumberNew(int electricNumberNew) {
        this.electricNumberNew = electricNumberNew;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}