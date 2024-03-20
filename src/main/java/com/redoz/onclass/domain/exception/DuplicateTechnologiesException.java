package com.redoz.onclass.domain.exception;

public class DuplicateTechnologiesException extends RuntimeException {
    public DuplicateTechnologiesException(String capacityName) {
        super(capacityName);
    }

}
