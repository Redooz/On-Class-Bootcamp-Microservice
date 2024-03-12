package com.redoz.onclass.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s cannot be empty";
    public static final String FIELD_LENGTH_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field %s length is not allowed";
    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Technology %s already exists";
}
