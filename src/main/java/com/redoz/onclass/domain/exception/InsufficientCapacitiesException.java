package com.redoz.onclass.domain.exception;

public class InsufficientCapacitiesException extends RuntimeException {
    public InsufficientCapacitiesException(String bootcampName) {
        super(bootcampName);
    }
}
