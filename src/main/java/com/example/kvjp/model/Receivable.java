package com.example.kvjp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "receivable")
@Builder
public class Receivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int payment;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
    private Date createAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
    private Date updateAt;

    private int status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "receivable_service",
            joinColumns = @JoinColumn(name = "receivable_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<ServiceOther> service = new HashSet<>();

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
