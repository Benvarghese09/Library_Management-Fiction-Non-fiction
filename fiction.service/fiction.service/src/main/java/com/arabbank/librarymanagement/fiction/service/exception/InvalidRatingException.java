package com.arabbank.librarymanagement.fiction.service.exception;

public class InvalidRatingException extends BusinessException{
    public InvalidRatingException(String message){
        super(message,"IR01");
    }
}
