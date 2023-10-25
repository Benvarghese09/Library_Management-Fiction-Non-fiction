package com.arabbank.librarymanagement.nonfiction.service.service;

import com.arabbank.librarymanagement.nonfiction.service.exception.ElementNotFoundException;
import com.arabbank.librarymanagement.nonfiction.service.exception.InvalidRatingException;
import com.arabbank.librarymanagement.nonfiction.service.model.dto.NonFictionBookResponseDto;
import com.arabbank.librarymanagement.nonfiction.service.model.entity.NonFictionBooksEntity;
import com.arabbank.librarymanagement.nonfiction.service.repository.NonFictionBooksRepo;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NonFictionBookService {
    @Autowired
    private NonFictionBooksRepo nonFictionBooksRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public String addNewBooks(NonFictionBooksEntity nonFictionBooksEntity) {
        NonFictionBooksEntity nonfictionBooksEntity1=new NonFictionBooksEntity();
        if (nonFictionBooksEntity.getBookRating()<1 || nonFictionBooksEntity.getBookRating()>5){
            throw new InvalidRatingException("This rating is not allowed.");
        }
        nonfictionBooksEntity1.setBookName(nonFictionBooksEntity.getBookName());
        nonfictionBooksEntity1.setBookDescription(nonFictionBooksEntity.getBookDescription());
        nonfictionBooksEntity1.setBookPrice(nonFictionBooksEntity.getBookPrice());
        nonfictionBooksEntity1.setBookRating(nonFictionBooksEntity.getBookRating());
        nonfictionBooksEntity1.setAuthorName(nonFictionBooksEntity.getAuthorName());
        nonfictionBooksEntity1.setPublicationYear(nonFictionBooksEntity.getPublicationYear());
        nonfictionBooksEntity1.setPublisherName(nonFictionBooksEntity.getPublisherName());
        nonFictionBooksRepo.save(nonFictionBooksEntity);

        return "New book added!";
    }

    public List<NonFictionBookResponseDto> getTheBooks(String bookName, String authorName, String publisherName) {
        Query query=new Query();
        if(bookName !=null){
            query.addCriteria(Criteria.where("bookName").is(bookName));
        }
        if(authorName!=null){
            query.addCriteria(Criteria.where("authorName").is(authorName));
        }
        if (publisherName!=null){
            query.addCriteria(Criteria.where("publisherName").is(publisherName));
        }




        List<NonFictionBooksEntity> nonfictionBooksEntities=mongoTemplate.find(query,NonFictionBooksEntity.class,"nonFictionBooks");
        List<NonFictionBookResponseDto> fictionBookResponseDtos=new ArrayList<>();
        for (NonFictionBooksEntity nonfictionBooksEntity1:nonfictionBooksEntities) {
            NonFictionBookResponseDto nonfictionBookResponseDto=new NonFictionBookResponseDto();
            nonfictionBookResponseDto.setBookName(nonfictionBooksEntity1.getBookName());
            nonfictionBookResponseDto.setAuthorName(nonfictionBooksEntity1.getAuthorName());
            nonfictionBookResponseDto.setBookPrice(nonfictionBooksEntity1.getBookPrice());
            nonfictionBookResponseDto.setBookDescription(nonfictionBooksEntity1.getBookDescription());
            nonfictionBookResponseDto.setPublisherName(nonfictionBooksEntity1.getPublisherName());
            nonfictionBookResponseDto.setBookRating(nonfictionBooksEntity1.getBookRating());
            nonfictionBookResponseDto.setPublicationYear(nonfictionBooksEntity1.getPublicationYear());
            fictionBookResponseDtos.add(nonfictionBookResponseDto);

        }
        return fictionBookResponseDtos;
    }

//    public List<NonFictionBookResponseDto> getAllTheBooks(String bookName, String authorName, String publisherName) {
//        Query query=new Query();
//        if(bookName !=null){
//            query.addCriteria(Criteria.where("bookName").is(bookName));
//        }
//        if(authorName!=null){
//            query.addCriteria(Criteria.where("authorName").is(authorName));
//        }
//        if (publisherName!=null){
//            query.addCriteria(Criteria.where("publisherName").is(publisherName));
//        }
//
//
//
//
//        List<NonFictionBooksEntity> fictionBooksEntities=mongoTemplate.find(query,NonFictionBooksEntity.class,"fictionBooks");
//        List<NonFictionBookResponseDto> fictionBookResponseDtos=new ArrayList<>();
//        for (NonFictionBooksEntity nonfictionBooksEntity1:fictionBooksEntities) {
//            NonFictionBookResponseDto nonfictionBookResponseDto=new NonFictionBookResponseDto();
//            nonfictionBookResponseDto.setBookName(nonfictionBooksEntity1.getBookName());
//            nonfictionBookResponseDto.setAuthorName(nonfictionBooksEntity1.getAuthorName());
//            nonfictionBookResponseDto.setBookPrice(nonfictionBooksEntity1.getBookPrice());
//            nonfictionBookResponseDto.setBookDescription(nonfictionBooksEntity1.getBookDescription());
//            nonfictionBookResponseDto.setPublisherName(nonfictionBooksEntity1.getPublisherName());
//            nonfictionBookResponseDto.setBookRating(nonfictionBooksEntity1.getBookRating());
//            nonfictionBookResponseDto.setPublicationYear(nonfictionBooksEntity1.getPublicationYear());
//            fictionBookResponseDtos.add(nonfictionBookResponseDto);
//
//        }
//        return fictionBookResponseDtos;
//
//    }

    public String changeBooks(int bookPrice, String publisherName,String bookName) {
        Query query=new Query();
        query.addCriteria(Criteria.where("bookName").is(bookName));

        Update update=new Update();
        update.set("bookPrice",bookPrice);
        update.set("publisherName",publisherName);
        NonFictionBooksEntity nonfictionBooksEntity=mongoTemplate.findAndModify(query,update,NonFictionBooksEntity.class);
        if(nonfictionBooksEntity==null){
            throw new ElementNotFoundException("The book is not existing");
        }
        return "Success";


    }

    public DeleteResult deleteBook(String bookName) {
        Query query=new Query();
        query.addCriteria(Criteria.where("bookName").is(bookName));
        DeleteResult nonfictionBooksEntity=mongoTemplate.remove(query,NonFictionBooksEntity.class);
        if(nonfictionBooksEntity.getDeletedCount()==0){
            throw new ElementNotFoundException("The book is not found");

        }
        return nonfictionBooksEntity;
    }
}
