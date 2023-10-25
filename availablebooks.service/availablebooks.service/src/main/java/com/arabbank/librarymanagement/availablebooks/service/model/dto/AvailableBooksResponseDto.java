package com.arabbank.librarymanagement.availablebooks.service.model.dto;

import com.arabbank.librarymanagement.availablebooks.service.enums.Provider;
import lombok.Data;

@Data
public class AvailableBooksResponseDto {
    private Provider provider;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String publisherName;
    private int publicationYear;
    private int bookPrice;
    private int bookRating;

}
