package com.redoz.onclassbootcamp.domain.exception;

public class ExcessiveCapacitiesException extends RuntimeException {
    public ExcessiveCapacitiesException(String bootcampName) {
        super(bootcampName);
    }
}
