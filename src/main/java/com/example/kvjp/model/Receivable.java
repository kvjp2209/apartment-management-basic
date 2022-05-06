package com.example.kvjp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@Table(name = "receivable")
public class Receivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int payment;

    private Timestamp dateFrom;

    private Timestamp dateTo;

    private int electricNumberOld;

    private int electricNumberNew;

    private int unitElectric;

    private int waterNumberOld;

    private int waterNumberNew;

    private int unitWater;

    private int status;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "leases_id")
    private Leases leases;

    public Receivable() {
    }
}
