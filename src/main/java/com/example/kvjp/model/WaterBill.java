package com.example.kvjp.model;

import com.example.kvjp.dto.request.WaterBillRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "water_bill")
@Data
@Builder
public class WaterBill {
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

    @Column(name = "water_number_old")
    private Integer waterNumberOld;

    @Column(name = "water_number_new")
    private Integer waterNumberNew;

    @Column(name = "unit")
    private Integer unit;

    @Column(name = "status")
    private Integer status;

    public WaterBill(String name, Date createAt, Date updateAt, Integer waterNumberOld, Integer waterNumberNew, Integer unit, Integer status) {
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.waterNumberOld = waterNumberOld;
        this.waterNumberNew = waterNumberNew;
        this.unit = unit;
        this.status = status;
    }

    public void update(WaterBillRequestDto waterBillRequestDto, WaterBill waterBill) {
        this.name = waterBillRequestDto.getName();
        this.createAt = waterBill.getCreateAt();
        this.updateAt = waterBillRequestDto.getUpdateAt();
        this.waterNumberOld  = waterBill.getWaterNumberNew();
        this.waterNumberNew = waterBillRequestDto.getWaterNumberNew();
        this.unit = waterBillRequestDto.getUnit();
        this.status = waterBill.getStatus();
    }

}