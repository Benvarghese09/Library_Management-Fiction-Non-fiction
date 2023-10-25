package com.arabbank.librarymanagement.nonfiction.service.model.dto;

import lombok.Data;

@Data
public class NonFictionBookRequestDto {
    private  String bookName;
    private String authorName;

    private String publisherName;
}
