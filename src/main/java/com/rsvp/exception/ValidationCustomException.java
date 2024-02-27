package com.rsvp.exception;

import com.rsvp.dto.ErrorDto;

import java.util.Arrays;
import java.util.List;

public class ValidationCustomException extends RuntimeException {
    private final List<ErrorDto> errorDtos;

    public ValidationCustomException(List<ErrorDto> objectErrorDtos) {
        this.errorDtos = objectErrorDtos;
    }

    public ValidationCustomException(ErrorDto objectErrorDto) {
        this.errorDtos = Arrays.asList(objectErrorDto);
    }

    public List<ErrorDto> getErrorDtos() {
        return errorDtos;
    }

    public ValidationCustomException(String message, List<ErrorDto> errorDtos) {
        super(message);
        this.errorDtos = errorDtos;
    }
}
