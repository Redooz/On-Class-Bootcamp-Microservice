package com.redoz.onclass.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
        DIRECTION
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "'name'";
    public static final String FIELD_DIRECTION_NULL_MESSAGE = "'direction'";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "'description'";
    public static final String NO_DATA_FOUND_TECHNOLOGY_EXCEPTION_MESSAGE = "'technology'";
}
