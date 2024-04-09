package com.redoz.onclassbootcamp.domain.exception;

public class InsufficientTechnologiesException extends RuntimeException {
    public InsufficientTechnologiesException(String capacityName) {
        super(capacityName);
    }
}
