package com.redoz.onclass.domain.util;

public enum VersionOrderByOption {
    BOOTCAMP_NAME("bootcamp.name"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    MAX_NUM_OF_STUDENTS("maxNumOfStudents"),;

    private final String value;

    VersionOrderByOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
