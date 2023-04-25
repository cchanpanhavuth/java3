package com.example.entity.enums;

public enum GenderEnum {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String code;

    GenderEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
