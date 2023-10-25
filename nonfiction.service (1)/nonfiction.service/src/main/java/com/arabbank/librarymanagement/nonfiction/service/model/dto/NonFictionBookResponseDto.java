package com.arabbank.librarymanagement.nonfiction.service.model.dto;

import lombok.Data;

@Data
public class NonFictionBookResponseDto {
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String publisherName;
    private int publicationYear;
    private int bookPrice;
    private int bookRating;
}
