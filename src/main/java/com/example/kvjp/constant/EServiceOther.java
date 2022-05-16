package com.example.kvjp.constant;

public enum EServiceOther {
    INTERNET(1),
    CLEANING(2),
    ;

    private int id;

    EServiceOther(int id) {
        this.id = id;
    }

    EServiceOther() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
