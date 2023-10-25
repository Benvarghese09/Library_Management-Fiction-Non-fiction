package com.arabbank.librarymanagement.availablebooks.service.controller;

import com.arabbank.librarymanagement.availablebooks.service.service.AvailableBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvailableBooksController {
    @Autowired
    private AvailableBooksService availableBooksService;
    @GetMapping("/book")
    public List getAvailableBooks(@RequestParam(required = false) String bookName,@RequestParam(required = false) String authorName,@RequestParam(required = false)  String publisherName){
        return availableBooksService.getBooks(bookName,authorName,publisherName);
    }

}
