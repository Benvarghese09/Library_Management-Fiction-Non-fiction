package com.arabbank.librarymanagement.availablebooks.service.model.dto;

import lombok.Data;

@Data
public class FictionBookResponseDto {
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String publisherName;
    private int publicationYear;
    private int bookPrice;
    private int bookRating;

}
