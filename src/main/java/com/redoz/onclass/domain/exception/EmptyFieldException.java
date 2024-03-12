package com.redoz.onclass.domain.exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(String message) {
        super(message);
    }
}
