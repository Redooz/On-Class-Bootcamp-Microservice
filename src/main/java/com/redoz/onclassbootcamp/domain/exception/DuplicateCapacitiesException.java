package com.redoz.onclassbootcamp.domain.exception;

public class DuplicateCapacitiesException extends RuntimeException {
    public DuplicateCapacitiesException(String capacityName) {
        super(capacityName);
    }
}
