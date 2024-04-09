package com.redoz.onclassbootcamp.domain.util;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "'name'";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "'description'";
    public static final String NO_DATA_FOUND_TECHNOLOGY_EXCEPTION_MESSAGE = "'technology'";
    public static final String NO_DATA_FOUND_CAPACITY_EXCEPTION_MESSAGE = "'capacity'";
    public static final String NO_DATA_FOUND_BOOTCAMP_EXCEPTION_MESSAGE = "'bootcamp'";
    public static final String NO_DATA_FOUND_VERSION_EXCEPTION_MESSAGE = "'version'";
}
