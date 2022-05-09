package com.example.kvjp.utils;

import com.example.kvjp.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    public ResponseEntity<ResponseDto> getNotFoundResponse(String object) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.NOT_FOUND.value(),
                String.format(object)), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseDto> getBadRequestResponse() {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        ), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseDto> getBadRequestResponse(Object message) {
        return new ResponseEntity<>(new ResponseDto(
                HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ResponseDto> getInternalServerErrorResponse() {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ResponseDto> getSuccessResponse(Object responseData) {
        return new ResponseEntity<>(new ResponseDto(
                HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), responseData), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getSuccessResponse() {
        return new ResponseEntity<>(new ResponseDto(
                HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getConflictResponse() {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase()),
                HttpStatus.CONFLICT);
    }

    public ResponseEntity<ResponseDto> getConflictResponse(Object message) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CONFLICT.value(), message), HttpStatus.CONFLICT);
    }

    public ResponseEntity<ResponseDto> getUnauthorizedResponse() {
        return new ResponseEntity<>(new ResponseDto(
                HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<ResponseDto> getUnauthorizedResponse(String message) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.UNAUTHORIZED.value(), message), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<ResponseDto> getForbiddenResponse() {
        return new ResponseEntity<>(new ResponseDto(
                HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<ResponseDto> getForbiddenResponse(String message) {
        return new ResponseEntity<>(new ResponseDto(HttpStatus.FORBIDDEN.value(), message), HttpStatus.FORBIDDEN);
    }
}
