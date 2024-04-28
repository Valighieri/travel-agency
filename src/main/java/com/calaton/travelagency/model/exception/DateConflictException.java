package com.calaton.travelagency.model.exception;

public class DateConflictException extends RuntimeException{
    public DateConflictException(String message) {
        super(message);
    }
}
