package com.redoz.onclassbootcamp.domain.exception;

public class CapacityAlreadyExistsException extends RuntimeException {
    public CapacityAlreadyExistsException(String name) {
        super("Capacity with name " + name + " already exists");
    }
}
