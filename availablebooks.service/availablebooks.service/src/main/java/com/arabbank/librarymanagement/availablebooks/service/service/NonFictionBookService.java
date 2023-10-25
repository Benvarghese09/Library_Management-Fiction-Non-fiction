package com.arabbank.librarymanagement.availablebooks.service.service;

import com.arabbank.librarymanagement.availablebooks.service.enums.Provider;
import com.arabbank.librarymanagement.availablebooks.service.exception.NotFoundException;
import com.arabbank.librarymanagement.availablebooks.service.feignclient.NonFictionBookClient;
import com.arabbank.librarymanagement.availablebooks.service.model.dto.AvailableBooksResponseDto;
import com.arabbank.librarymanagement.availablebooks.service.model.dto.NonFictionBookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NonFictionBookService {
    @Autowired
    private NonFictionBookClient nonFictionBookClient;
    List responses=new ArrayList<>();


    public List NonFictionBooks(String bookName, String authorName, String publisherName) {
        List<NonFictionBookResponseDto> nonFictionBookResponseDtos=nonFictionBookClient.getBooks(bookName,authorName,publisherName);
        if(nonFictionBookResponseDtos.isEmpty()){
            throw new NotFoundException("Non-Fiction book not found");
        }
        for(NonFictionBookResponseDto nonavail:nonFictionBookResponseDtos){
            AvailableBooksResponseDto availableBooksResponseDto=new AvailableBooksResponseDto();
            availableBooksResponseDto.setBookName(nonavail.getBookName());
            availableBooksResponseDto.setPublisherName(nonavail.getPublisherName());
            availableBooksResponseDto.setBookPrice(nonavail.getBookPrice());
            availableBooksResponseDto.setBookRating(nonavail.getBookRating());
            availableBooksResponseDto.setProvider(Provider.NONFICTION_BOOK);
            availableBooksResponseDto.setPublicationYear(nonavail.getPublicationYear());
            availableBooksResponseDto.setBookDescription(nonavail.getBookDescription());
            availableBooksResponseDto.setAuthorName(nonavail.getAuthorName());
            responses.add(availableBooksResponseDto);

        }
        return responses;
    }
}
