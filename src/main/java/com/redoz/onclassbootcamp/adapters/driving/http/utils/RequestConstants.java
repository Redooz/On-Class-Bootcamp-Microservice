package com.redoz.onclassbootcamp.adapters.driving.http.utils;

public class RequestConstants {
    private RequestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final int NAME_MAX_LENGTH_VALUE = 50;
    public static final int DESCRIPTION_MAX_LENGTH_VALUE = 90;

    public static final String ID_IS_MANDATORY = "Id is mandatory";
    public static final String NAME_IS_MANDATORY = "Name is mandatory";
    public static final String DESCRIPTION_IS_MANDATORY = "Description is mandatory";
    public static final String TECHNOLOGIES_IS_MANDATORY = "Technologies is mandatory";
    public static final String CAPACITIES_IS_MANDATORY = "Capacities is mandatory";

    public static final String NAME_MAX_LENGTH = "Name max length is " + NAME_MAX_LENGTH_VALUE;
    public static final String DESCRIPTION_MAX_LENGTH = "Description max length is " + DESCRIPTION_MAX_LENGTH_VALUE;
}
