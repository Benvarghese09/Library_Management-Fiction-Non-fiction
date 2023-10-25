package com.arabbank.librarymanagement.fiction.service.service;

import com.arabbank.librarymanagement.fiction.service.exception.ElementNotFoundException;
import com.arabbank.librarymanagement.fiction.service.exception.InvalidRatingException;
import com.arabbank.librarymanagement.fiction.service.model.dto.FictionBookResponseDto;
import com.arabbank.librarymanagement.fiction.service.model.entity.FictionBooksEntity;
import com.arabbank.librarymanagement.fiction.service.repository.FictionBooksRepo;
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
public class FictionBooksService {
    @Autowired
    private FictionBooksRepo fictionBooksRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public String addNewBooks(FictionBooksEntity fictionBooksEntity) {
        FictionBooksEntity fictionBooksEntity1=new FictionBooksEntity();
        if (fictionBooksEntity.getBookRating()<1 || fictionBooksEntity.getBookRating()>5){
            throw new InvalidRatingException("This rating is not allowed.");
        }
        fictionBooksEntity1.setBookName(fictionBooksEntity.getBookName());
        fictionBooksEntity1.setBookDescription(fictionBooksEntity.getBookDescription());
        fictionBooksEntity1.setBookPrice(fictionBooksEntity.getBookPrice());
        fictionBooksEntity1.setBookRating(fictionBooksEntity.getBookRating());
        fictionBooksEntity1.setAuthorName(fictionBooksEntity.getAuthorName());
        fictionBooksEntity1.setPublicationYear(fictionBooksEntity.getPublicationYear());
        fictionBooksEntity1.setPublisherName(fictionBooksEntity.getPublisherName());
        fictionBooksRepo.save(fictionBooksEntity);

        return "New book added!";
    }

    public List<FictionBookResponseDto> getTheBooks(String bookName, String authorName,String publisherName) {
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




        List<FictionBooksEntity> fictionBooksEntities=mongoTemplate.find(query,FictionBooksEntity.class,"fictionBooks");
        List<FictionBookResponseDto> fictionBookResponseDtos=new ArrayList<>();
        for (FictionBooksEntity fictionBooksEntity1:fictionBooksEntities) {
            FictionBookResponseDto fictionBookResponseDto=new FictionBookResponseDto();
            fictionBookResponseDto.setBookName(fictionBooksEntity1.getBookName());
            fictionBookResponseDto.setAuthorName(fictionBooksEntity1.getAuthorName());
            fictionBookResponseDto.setBookPrice(fictionBooksEntity1.getBookPrice());
            fictionBookResponseDto.setBookDescription(fictionBooksEntity1.getBookDescription());
            fictionBookResponseDto.setPublisherName(fictionBooksEntity1.getPublisherName());
            fictionBookResponseDto.setBookRating(fictionBooksEntity1.getBookRating());
            fictionBookResponseDto.setPublicationYear(fictionBooksEntity1.getPublicationYear());
            fictionBookResponseDtos.add(fictionBookResponseDto);

        }
        return fictionBookResponseDtos;
    }

//    public List<FictionBookResponseDto> getAllTheBooks(String bookName, String authorName, String publisherName) {
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
//        List<FictionBooksEntity> fictionBooksEntities=mongoTemplate.find(query,FictionBooksEntity.class,"fictionBooks");
//        List<FictionBookResponseDto> fictionBookResponseDtos=new ArrayList<>();
//        for (FictionBooksEntity fictionBooksEntity1:fictionBooksEntities) {
//            FictionBookResponseDto fictionBookResponseDto=new FictionBookResponseDto();
//            fictionBookResponseDto.setBookName(fictionBooksEntity1.getBookName());
//            fictionBookResponseDto.setAuthorName(fictionBooksEntity1.getAuthorName());
//            fictionBookResponseDto.setBookPrice(fictionBooksEntity1.getBookPrice());
//            fictionBookResponseDto.setBookDescription(fictionBooksEntity1.getBookDescription());
//            fictionBookResponseDto.setPublisherName(fictionBooksEntity1.getPublisherName());
//            fictionBookResponseDto.setBookRating(fictionBooksEntity1.getBookRating());
//            fictionBookResponseDtos.add(fictionBookResponseDto);
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
        FictionBooksEntity fictionBooksEntity=mongoTemplate.findAndModify(query,update,FictionBooksEntity.class);
        if(fictionBooksEntity==null){
            throw new ElementNotFoundException("The book is not existing");
        }
        return "Success";


    }

    public DeleteResult deleteBook(String bookName) {
        Query query=new Query();
        query.addCriteria(Criteria.where("bookName").is(bookName));
        DeleteResult fictionBooksEntity=mongoTemplate.remove(query,FictionBooksEntity.class);
        if(fictionBooksEntity.getDeletedCount()==0){
            throw new ElementNotFoundException("The book is not found");

        }
        return fictionBooksEntity;
    }
}
