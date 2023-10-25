package com.arabbank.librarymanagement.fiction.service.model.dto;

import lombok.Data;

@Data
public class FictionBookRequestDto {
    private  String bookName;
    private String authorName;

    private String publisherName;
}
