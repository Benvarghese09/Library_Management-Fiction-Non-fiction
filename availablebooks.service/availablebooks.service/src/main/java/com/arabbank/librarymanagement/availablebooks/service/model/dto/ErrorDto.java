package com.arabbank.librarymanagement.availablebooks.service.model.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String message;
    private String errorCode;
}
