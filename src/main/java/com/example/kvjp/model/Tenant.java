package com.example.kvjp.model;

import javax.persistence.*;

@Entity
@Table(name = "tenant")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    private String dob;

    private Long phone;

    private int gender;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Tenant() {
    }

    public Tenant(Long id, String name, String email, int age, String dob, Long phone, int gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
