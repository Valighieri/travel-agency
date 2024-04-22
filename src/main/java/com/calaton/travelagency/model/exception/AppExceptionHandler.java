package com.calaton.travelagency.model.exception;

import com.calaton.travelagency.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(ResourceNotFoundException e) {
        log.error("Error: {}", e.getMessage(), e);
        ErrorDto errorDto = new ErrorDto(Instant.now().getEpochSecond(), e.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidDataException e) {
        log.error("Error: {}", e.getMessage(), e);
        ErrorDto errorDto = new ErrorDto(Instant.now().getEpochSecond(), e.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        log.error("Error: {}", e.getMessage(), e);
        ErrorDto errorDto = new ErrorDto(Instant.now().getEpochSecond(), e.getMessage());
        return ResponseEntity.internalServerError().body(errorDto);
    }

}
