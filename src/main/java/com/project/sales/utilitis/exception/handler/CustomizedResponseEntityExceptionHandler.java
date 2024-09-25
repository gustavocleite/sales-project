package com.project.sales.utilitis.exception.handler;

import com.project.sales.utilitis.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ApiErrorDetail> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiErrorDetail(
                    error.getObjectName(),
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            ));
        }

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                "Erro de validação",
                errors
        );

        return new ResponseEntity<>(apiErrorMessage,apiErrorMessage.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                HttpStatus.NOT_FOUND,
                new Date(),
                ex.getMessage()
        );

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST,
                new Date(),
                ex.getMessage()
        );

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest webRequest){

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                HttpStatus.CONFLICT,
                new Date(),
                ex.getMessage()
        );

        return new ResponseEntity<>(apiErrorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest webRequest) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date(),
                ex.getMessage()
        );

        return new ResponseEntity<>(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

