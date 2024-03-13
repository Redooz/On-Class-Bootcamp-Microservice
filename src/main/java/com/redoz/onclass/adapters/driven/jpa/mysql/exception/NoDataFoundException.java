package com.redoz.onclass.adapters.driven.jpa.mysql.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
