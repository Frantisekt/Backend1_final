package com.dh.clinica.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponseDecorator;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> manejarExcepcionesRecursoNoEncontrado(ResourceNotFoundException e, HttpServletRequest request){
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> manejarValidaciones (MethodArgumentNotValidException e, HttpServletRequest request){
        List<String> errores = new ArrayList<>();
        for(Object error: e.getBindingResult().getAllErrors()){
            if(error instanceof FieldError){
                FieldError fieldError = (FieldError) error;
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errores.add(fieldName+": " +message);
            }
        }
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                "validation field",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errores
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarTodasLasExcepciones(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
