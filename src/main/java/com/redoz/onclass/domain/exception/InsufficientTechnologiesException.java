package com.redoz.onclass.domain.exception;

public class InsufficientTechnologiesException extends RuntimeException {
    public InsufficientTechnologiesException(String capacityName) {
        super(capacityName);
    }
}
