package ru.raynur.myhomeserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.raynur.myhomeserver.model.response.exception.ExceptionResponse;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionController {

    /*@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RuntimeException.class)
    private ExceptionResponse unauthorized(RuntimeException exception) {
        return new ExceptionResponse(exception.getMessage());
    }*/

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    private ExceptionResponse notFound(EntityNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    private ExceptionResponse error(RuntimeException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
