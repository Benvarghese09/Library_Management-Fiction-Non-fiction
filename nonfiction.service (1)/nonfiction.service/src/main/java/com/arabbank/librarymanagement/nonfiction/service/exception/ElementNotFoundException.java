package com.arabbank.librarymanagement.nonfiction.service.exception;

public class ElementNotFoundException extends BusinessException{
    public ElementNotFoundException(String message){
        super(message,"EN01");
    }

}
