package com.arabbank.librarymanagement.availablebooks.service.model.dto;

import lombok.Data;

@Data
public class AvailableBooksRequestDto {
    private String bookName;
    private String authorName;
    private String publisherName;
}
