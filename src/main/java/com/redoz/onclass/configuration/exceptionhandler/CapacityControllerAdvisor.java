package com.redoz.onclass.configuration.exceptionhandler;

import com.redoz.onclass.configuration.Constants;
import com.redoz.onclass.domain.exception.DuplicateTechnologiesException;
import com.redoz.onclass.domain.exception.ExcessiveTechnologiesException;
import com.redoz.onclass.domain.exception.InsufficientTechnologiesException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class CapacityControllerAdvisor {
    @ExceptionHandler(InsufficientTechnologiesException.class)
    public ResponseEntity<ExceptionResponse> handleInsufficientTechnologiesException(InsufficientTechnologiesException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.CAPACITY_INSUFFICIENT_TECHNOLOGIES_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ExcessiveTechnologiesException.class)
    public ResponseEntity<ExceptionResponse> handleExcessiveTechnologiesException(ExcessiveTechnologiesException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.CAPACITY_EXCESSIVE_TECHNOLOGIES_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateTechnologiesException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateTechnologiesException(DuplicateTechnologiesException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.CAPACITY_DUPLICATE_TECHNOLOGIES_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
