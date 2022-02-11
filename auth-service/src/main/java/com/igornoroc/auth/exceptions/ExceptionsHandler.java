package com.igornoroc.auth.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandler {
    private ObjectMapper convertToJson;

    @ExceptionHandler({UserAlreadyExistsExceptions.class})
    public ResponseEntity<?> response() {
        return ResponseEntity.badRequest().body("user already exists!");
    }
}
