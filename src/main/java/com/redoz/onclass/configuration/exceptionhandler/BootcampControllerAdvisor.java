package com.redoz.onclass.configuration.exceptionhandler;

import com.redoz.onclass.configuration.Constants;
import com.redoz.onclass.domain.exception.DuplicateCapacitiesException;
import com.redoz.onclass.domain.exception.ExcessiveCapacitiesException;
import com.redoz.onclass.domain.exception.InsufficientCapacitiesException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class BootcampControllerAdvisor {
    @ExceptionHandler(InsufficientCapacitiesException.class)
    public ResponseEntity<ExceptionResponse> handleInsufficientCapacitiesException(InsufficientCapacitiesException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.BOOTCAMP_INSUFFICIENT_CAPACITIES_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ExcessiveCapacitiesException.class)
    public ResponseEntity<ExceptionResponse> handleExcessiveCapacitiesException(ExcessiveCapacitiesException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.BOOTCAMP_EXCESSIVE_CAPACITIES_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateCapacitiesException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateCapacitiesException(DuplicateCapacitiesException e) {
        ExceptionResponse response = new ExceptionResponse(String.format(Constants.BOOTCAMP_DUPLICATE_CAPACITIES_EXCEPTION_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
