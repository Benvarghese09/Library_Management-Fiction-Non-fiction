package com.arabbank.librarymanagement.nonfiction.service.exception;

public class InvalidRatingException extends BusinessException{
    public InvalidRatingException(String message){
        super(message,"IR01");
    }
}

