package com.redoz.onclassbootcamp.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Technology %s already exists";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data found for %s";
    public static final String CAPACITY_INSUFFICIENT_TECHNOLOGIES_EXCEPTION_MESSAGE = "%s capacity must have at least 3 technologies";
    public static final String CAPACITY_EXCESSIVE_TECHNOLOGIES_EXCEPTION_MESSAGE = "%s capacity must have at most 20 technologies";
    public static final String CAPACITY_DUPLICATE_TECHNOLOGIES_EXCEPTION_MESSAGE = "%s capacity must have unique technologies";
    public static final String BOOTCAMP_INSUFFICIENT_CAPACITIES_EXCEPTION_MESSAGE = "%s bootcamp must have at least 1 capacity";
    public static final String BOOTCAMP_EXCESSIVE_CAPACITIES_EXCEPTION_MESSAGE = "%s bootcamp must have at most 4 capacities";
    public static final String BOOTCAMP_DUPLICATE_CAPACITIES_EXCEPTION_MESSAGE = "%s bootcamp must have unique capacities";

}
