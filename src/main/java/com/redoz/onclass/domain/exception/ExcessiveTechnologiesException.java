package com.redoz.onclass.domain.exception;

public class ExcessiveTechnologiesException extends RuntimeException {
    public ExcessiveTechnologiesException(String capacityName) {
        super(capacityName);
    }
}
