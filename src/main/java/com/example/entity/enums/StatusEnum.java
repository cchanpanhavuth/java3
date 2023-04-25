package com.example.entity.enums;

public enum StatusEnum {
    ACT("ACTIVE"),
    INACT("INACTIVE"),
    SUS("SUSPEND");

    private final String code;

    StatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
