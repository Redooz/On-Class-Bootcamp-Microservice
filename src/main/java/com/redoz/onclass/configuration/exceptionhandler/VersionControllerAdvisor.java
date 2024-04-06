package com.redoz.onclass.configuration.exceptionhandler;

import com.redoz.onclass.domain.exception.InvalidDateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class VersionControllerAdvisor {
    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDateException(InvalidDateException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
