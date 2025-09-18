package com.baggio.dscommerce.controllers.hendlers;

import com.baggio.dscommerce.dto.CustomError;
import com.baggio.dscommerce.services.exceptions.DatabaseException;
import com.baggio.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return getCustomErrorResponseEntity(HttpStatus.NOT_FOUND, e.getMessage(), request);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> resourceNotFound(DatabaseException e, HttpServletRequest request) {
        return getCustomErrorResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    private static ResponseEntity<CustomError> getCustomErrorResponseEntity(HttpStatus notFound, String e, HttpServletRequest request) {
        CustomError err = new CustomError(Instant.now(), notFound.value(), e, request.getRequestURI());
        return ResponseEntity.status(notFound).body(err);
    }
}
