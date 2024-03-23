package com.redoz.onclass.domain.exception;

public class ExcessiveCapacitiesException extends RuntimeException {
    public ExcessiveCapacitiesException(String bootcampName) {
        super(bootcampName);
    }
}
