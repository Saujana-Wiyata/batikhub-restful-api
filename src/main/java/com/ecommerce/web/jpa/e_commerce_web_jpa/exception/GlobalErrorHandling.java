package com.ecommerce.web.jpa.e_commerce_web_jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;

import jakarta.validation.ConstraintViolationException;

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

        @ExceptionHandler(exception = ResponseStatusException.class)
        public ResponseEntity<WebResponse<String>> exceptionHandler(
                        ResponseStatusException exception) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(WebResponse.<String>builder()
                                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                .error(exception.getMessage())
                                                .build());
        }

}
