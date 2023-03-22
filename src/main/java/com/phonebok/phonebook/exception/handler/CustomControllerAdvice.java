package com.phonebok.phonebook.exception.handler;

import com.phonebok.phonebook.exception.BadRequestException;
import com.phonebok.phonebook.exception.NotFoundException;
import com.phonebok.phonebook.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = String.format("%s not found",e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.name());
        errorResponse.setMessage(message);

        return new ResponseEntity<>(errorResponse,status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequestException(Exception e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = String.format("invalid request, %s",e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.name());
        errorResponse.setMessage(message);

        return new ResponseEntity<>(errorResponse,status);
    }
}
