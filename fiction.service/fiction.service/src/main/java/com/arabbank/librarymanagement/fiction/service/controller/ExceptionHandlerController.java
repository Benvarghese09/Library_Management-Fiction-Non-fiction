package com.arabbank.librarymanagement.fiction.service.controller;

import com.arabbank.librarymanagement.fiction.service.exception.BusinessException;
import com.arabbank.librarymanagement.fiction.service.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice

public class ExceptionHandlerController {
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
        public ErrorDto handleBusinessException(BusinessException ex){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        log.error(String.valueOf(errorDto));
        return errorDto;

    }
}
