package com.arabbank.librarymanagement.nonfiction.service.exception;

public class BusinessException extends RuntimeException{
    public String ERROR_CODE;
    public BusinessException(String message,String errorCode){
        super(message);
        this.ERROR_CODE=errorCode;
    }
}

