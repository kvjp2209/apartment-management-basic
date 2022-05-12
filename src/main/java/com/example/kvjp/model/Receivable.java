package com.example.kvjp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "receivable")
public class Receivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private int payment;

    @CreationTimestamp
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    private int status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "receivable_service",
            joinColumns = @JoinColumn(name = "receivable_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> service = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "electric_bill_id")
    private ElectricBill electricBill;

    @OneToOne
    @JoinColumn(name = "water_bill_id")
    private WaterBill waterBill;

    @ManyToOne
    @JoinColumn(name = "leases_id")
    private Leases leases;

    public Receivable() {
    }
}
