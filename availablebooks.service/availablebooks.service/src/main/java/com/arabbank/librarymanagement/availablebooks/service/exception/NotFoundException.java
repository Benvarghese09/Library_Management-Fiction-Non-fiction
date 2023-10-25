package com.arabbank.librarymanagement.availablebooks.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends BusinessException{
    public NotFoundException(String message){
        super(message,"AC01");
    }
}
