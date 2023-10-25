package com.arabbank.librarymanagement.fiction.service.repository;

import com.arabbank.librarymanagement.fiction.service.model.entity.FictionBooksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FictionBooksRepo extends MongoRepository<FictionBooksEntity,String> {
}
