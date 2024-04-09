package com.redoz.onclassbootcamp.domain.util;

public enum CapacityOrderByOption {
    NAME("name"),
    TECHNOLOGY_COUNT("technologies.size"),;


    private final String value;

    CapacityOrderByOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}