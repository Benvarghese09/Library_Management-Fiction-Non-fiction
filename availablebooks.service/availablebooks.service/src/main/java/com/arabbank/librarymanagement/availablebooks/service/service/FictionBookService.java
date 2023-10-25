package com.arabbank.librarymanagement.availablebooks.service.service;

import com.arabbank.librarymanagement.availablebooks.service.enums.Provider;
import com.arabbank.librarymanagement.availablebooks.service.exception.NotFoundException;
import com.arabbank.librarymanagement.availablebooks.service.feignclient.FictionBookClient;
import com.arabbank.librarymanagement.availablebooks.service.model.dto.AvailableBooksResponseDto;
import com.arabbank.librarymanagement.availablebooks.service.model.dto.FictionBookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FictionBookService {
    @Autowired
    private FictionBookClient fictionBookClient;
    List response=new ArrayList<>();
    public List FictionBooks(String bookName, String authorName, String publisherName) {
        List<FictionBookResponseDto> fictionBookResponseDtos=fictionBookClient.getBooks(bookName,authorName,publisherName);
        if(fictionBookResponseDtos.isEmpty()){
            throw  new NotFoundException("Fiction book is not available");
        }
        for(FictionBookResponseDto avail:fictionBookResponseDtos){
            AvailableBooksResponseDto availableBooksResponseDto=new AvailableBooksResponseDto();
            availableBooksResponseDto.setBookName(avail.getBookName());
            availableBooksResponseDto.setBookDescription(avail.getBookDescription());
            availableBooksResponseDto.setBookRating(avail.getBookRating());
            availableBooksResponseDto.setAuthorName(avail.getAuthorName());
            availableBooksResponseDto.setPublisherName(avail.getPublisherName());
            availableBooksResponseDto.setPublicationYear(avail.getPublicationYear());
            availableBooksResponseDto.setProvider(Provider.FICTION_BOOK);
            availableBooksResponseDto.setBookPrice(avail.getBookPrice());
            response.add(availableBooksResponseDto);

        }
        return response;

    }
}
