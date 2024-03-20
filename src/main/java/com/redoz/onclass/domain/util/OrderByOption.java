package com.redoz.onclass.domain.util;

public enum OrderByOption {
    NAME("name"),
    TECHNOLOGY_COUNT("technologies");

    private final String value;

    OrderByOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}