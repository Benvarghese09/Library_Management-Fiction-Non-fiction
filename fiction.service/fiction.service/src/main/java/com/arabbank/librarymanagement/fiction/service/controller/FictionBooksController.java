package com.arabbank.librarymanagement.fiction.service.controller;

import com.arabbank.librarymanagement.fiction.service.model.dto.FictionBookResponseDto;
import com.arabbank.librarymanagement.fiction.service.model.entity.FictionBooksEntity;
import com.arabbank.librarymanagement.fiction.service.service.FictionBooksService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FictionBooksController {
    @Autowired
    private FictionBooksService fictionBooksService;

    @PostMapping("/new-book")
    public String addNewBook(@RequestBody FictionBooksEntity fictionBooksEntity){
        return fictionBooksService.addNewBooks(fictionBooksEntity);
    }

    @GetMapping("/book")
    public List<FictionBookResponseDto> getTheBooks(@RequestParam(required = false) String bookName,
                                                     @RequestParam(required = false)  String authorName,
                                                     @RequestParam(required = false)  String publisherName){
        return fictionBooksService.getTheBooks(bookName,authorName,publisherName);
    }

//    @GetMapping("/all-book")
//    public List<FictionBookResponseDto> getAllTheBooks(String bookName,String authorName,String publisherName){
//        return fictionBooksService.getAllTheBooks(bookName,authorName,publisherName);
//    }

    @PutMapping("/change-book")
    public String editAllTheRecords(@RequestParam int bookPrice,@RequestParam String publisherName,@RequestParam String bookName){
        return fictionBooksService.changeBooks(bookPrice,publisherName,bookName);
    }

    @DeleteMapping("/remove-book")
    public DeleteResult removeBook(@RequestParam String bookName){
        return fictionBooksService.deleteBook(bookName);
    }


}
