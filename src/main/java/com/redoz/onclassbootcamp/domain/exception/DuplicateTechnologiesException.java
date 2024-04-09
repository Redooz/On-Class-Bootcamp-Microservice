package com.redoz.onclassbootcamp.domain.exception;

public class DuplicateTechnologiesException extends RuntimeException {
    public DuplicateTechnologiesException(String capacityName) {
        super(capacityName);
    }

}
