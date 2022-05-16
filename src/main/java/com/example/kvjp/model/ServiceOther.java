package com.example.kvjp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "service")
public class ServiceOther {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int price;

    public ServiceOther(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ServiceOther" +
                id +
                ": name='" + name + '\'' +
                "- price=" + price +
                "VND";
    }
}
