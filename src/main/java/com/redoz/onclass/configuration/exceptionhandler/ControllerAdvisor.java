package com.redoz.onclass.configuration.exceptionhandler;

import com.redoz.onclass.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.redoz.onclass.configuration.Constants;
import com.redoz.onclass.domain.exception.EmptyFieldException;
import com.redoz.onclass.domain.exception.FieldLengthNotAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FieldLengthNotAllowed.class)
    public ResponseEntity<ExceptionResponse> handleFieldLengthNotAllowed(FieldLengthNotAllowed e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.FIELD_LENGTH_NOT_ALLOWED_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleTechnologyAlreadyExistsException(TechnologyAlreadyExistsException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
