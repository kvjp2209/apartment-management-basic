package com.example.kvjp.constant;

public enum EGender {
    MALE(1),
    FEMALE(2);

    private int id;

    EGender(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
