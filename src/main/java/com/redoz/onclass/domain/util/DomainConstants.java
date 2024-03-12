package com.redoz.onclass.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DIRECTION
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' can't be empty";
    public static final String FIELD_DIRECTION_NULL_MESSAGE = "Field 'direction' can't be empty";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' can't be empty";
    public static final String FIELD_NAME_LENGTH_MESSAGE = "Field 'name' can't be longer than 50 characters";
    public static final String FIELD_DESCRIPTION_LENGTH_MESSAGE = "Field 'description' can't be longer than 90 characters";
}
