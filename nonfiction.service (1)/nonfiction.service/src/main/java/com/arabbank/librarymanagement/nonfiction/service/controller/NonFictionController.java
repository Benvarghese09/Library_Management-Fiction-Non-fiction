package com.arabbank.librarymanagement.nonfiction.service.controller;

import com.arabbank.librarymanagement.nonfiction.service.model.dto.NonFictionBookResponseDto;
import com.arabbank.librarymanagement.nonfiction.service.model.entity.NonFictionBooksEntity;
import com.arabbank.librarymanagement.nonfiction.service.service.NonFictionBookService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NonFictionController {
    @Autowired
    private NonFictionBookService nonFictionBookService;
    @PostMapping("/new-book")
    public String addNewBook(@RequestBody NonFictionBooksEntity fictionBooksEntity){
        return nonFictionBookService.addNewBooks(fictionBooksEntity);
    }

    @GetMapping("/book")
    public List<NonFictionBookResponseDto> getTheBooks(@RequestParam(required = false) String bookName,
                                                       @RequestParam(required = false)  String authorName,
                                                       @RequestParam(required = false)  String publisherName){
        return nonFictionBookService.getTheBooks(bookName,authorName,publisherName);
    }

//    @GetMapping("/all-book")
//    public List<NonFictionBookResponseDto> getAllTheBooks(String bookName,String authorName,String publisherName){
//        return nonFictionBookService.getAllTheBooks(bookName,authorName,publisherName);
//    }

    @PutMapping("/change-book")
    public String editAllTheRecords(@RequestParam int bookPrice,@RequestParam String publisherName,@RequestParam String bookName){
        return nonFictionBookService.changeBooks(bookPrice,publisherName,bookName);
    }

    @DeleteMapping("/remove-book")
    public DeleteResult removeBook(@RequestParam String bookName){
        return nonFictionBookService.deleteBook(bookName);
    }

}
