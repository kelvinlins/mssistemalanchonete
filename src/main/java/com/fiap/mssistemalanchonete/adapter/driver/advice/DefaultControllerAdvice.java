package com.fiap.mssistemalanchonete.adapter.driver.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleApiException(Exception ex){

        String status = ex.getMessage();
        return ResponseEntity.status(Integer.valueOf(status)).body("200");
    }

    /*
    @ExceptionHandler(value = MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeader(MissingRequestHeaderException ex){

        BadRequestException resp = new BadRequestException(ApplicationEnum.CRUD_BAD_REQUEST_ERROR,
                ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getMessage(),
                ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getDescription(), ex);

        return ResponseEntity.status(ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getId()).body(resp.getError());

    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingRequestParam(MissingServletRequestParameterException ex){

        BadRequestException resp = new BadRequestException(ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getId(),
                ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getMessage(),
                ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getDescription(),ex);

        return ResponseEntity.status(ApplicationEnum.CRUD_BAD_REQUEST_ERROR.getId()).body(resp.getError());
    } */

}
