package com.ecommerce.web.jpa.e_commerce_web_jpa.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;

@RestControllerAdvice
public class GlobalErrorHandling {

    @ExceptionHandler(exception = ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationHandler(
            ConstraintViolationException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                WebResponse.<String>builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .error(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<WebResponse<String>> exceptionHandler(
            Exception exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(WebResponse.<String>builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error(exception.getMessage())
                        .build());
    }

}
