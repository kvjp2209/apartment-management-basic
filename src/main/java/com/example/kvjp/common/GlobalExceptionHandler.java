package com.example.kvjp.common;


import com.example.kvjp.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity handleException(Exception e) {
        ResponseDto apiResponse = new ResponseDto();
        apiResponse.setError(e.getLocalizedMessage());
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
    }

}
