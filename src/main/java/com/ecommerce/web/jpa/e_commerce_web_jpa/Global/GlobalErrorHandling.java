package com.ecommerce.web.jpa.e_commerce_web_jpa.Global;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandling {

    @ExceptionHandler(exception = ConstraintViolationException.class)
    public ModelAndView constraintViolaation(ConstraintViolationException exception) {

        log.error(exception.getConstraintName() + ": " + exception.getMessage());

        ModelAndView modelAndView = new ModelAndView("error/errorpage");
        modelAndView.addObject("errorStatus", HttpStatus.BAD_GATEWAY);
        modelAndView.addObject("errorMessage", exception.getMessage());

        return modelAndView;
    }

}
