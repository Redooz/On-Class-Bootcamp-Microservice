package com.redoz.onclassbootcamp.domain.util;

public enum BootcampOrderByOption {
    NAME("name"),
    CAPACITY_COUNT("capacities.size"),;

    private final String value;

    BootcampOrderByOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
