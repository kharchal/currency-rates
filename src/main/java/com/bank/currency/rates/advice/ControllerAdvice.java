package com.bank.currency.rates.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity handleConflict(RuntimeException ex, WebRequest request) {
        log.error("ERROR MESSAGE: {}", ex.getMessage());
        for (StackTraceElement traceElement : ex.getStackTrace()) {
            log.error("STACK TRACE: {}", traceElement);

        }
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatusCode.valueOf(500), ex.getMessage());
        return ResponseEntity.ok(errorResponse);
    }
}