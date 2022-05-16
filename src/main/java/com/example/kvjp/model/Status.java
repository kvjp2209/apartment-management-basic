package com.example.kvjp.model;


import com.example.kvjp.constant.EStatus;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EStatus eStatus;

    public Status() {
    }

    public Status(Integer id, EStatus eStatus) {
        this.id = id;
        this.eStatus = eStatus;
    }

    public Status(EStatus eStatus) {
        this.eStatus = eStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }
}
