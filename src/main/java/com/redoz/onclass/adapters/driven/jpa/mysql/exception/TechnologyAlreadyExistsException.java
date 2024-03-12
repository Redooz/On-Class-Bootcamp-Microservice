package com.redoz.onclass.adapters.driven.jpa.mysql.exception;

public class TechnologyAlreadyExistsException extends RuntimeException {
    public TechnologyAlreadyExistsException(String technologyName) {
        super(technologyName);
    }
}
