package com.redoz.onclassbootcamp.domain.exception;

public class ExcessiveTechnologiesException extends RuntimeException {
    public ExcessiveTechnologiesException(String capacityName) {
        super(capacityName);
    }
}
