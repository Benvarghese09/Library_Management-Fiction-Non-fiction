package com.arabbank.librarymanagement.availablebooks.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AvailableBooksService {
    @Autowired
    private  FictionBookService fictionBookService;
    @Autowired
    private NonFictionBookService nonFictionBookService;
    public List getBooks(String bookName, String authorName, String publisherName) {
        List finalResponse=fictionBookService.FictionBooks(bookName,authorName,publisherName);
        List finalResponses=nonFictionBookService.NonFictionBooks(bookName,authorName,publisherName);
        finalResponse.addAll(finalResponses);
        return finalResponse;

    }
}
