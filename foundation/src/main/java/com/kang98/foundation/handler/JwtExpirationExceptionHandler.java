package com.kang98.foundation.handler;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JwtExpirationExceptionHandler {

    // this does not work for now
    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleJwtExpirationException(ExpiredJwtException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN.value())
                .body(HttpEntity.EMPTY);
    }
}