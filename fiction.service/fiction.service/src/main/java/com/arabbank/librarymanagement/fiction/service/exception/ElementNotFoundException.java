package com.arabbank.librarymanagement.fiction.service.exception;

public class ElementNotFoundException extends BusinessException{
    public ElementNotFoundException(String message){
        super(message,"EN01");
    }

}
