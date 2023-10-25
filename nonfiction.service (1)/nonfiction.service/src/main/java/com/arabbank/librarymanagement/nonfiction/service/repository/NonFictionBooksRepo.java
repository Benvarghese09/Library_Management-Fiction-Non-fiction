package com.arabbank.librarymanagement.nonfiction.service.repository;

import com.arabbank.librarymanagement.nonfiction.service.model.entity.NonFictionBooksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonFictionBooksRepo extends MongoRepository<NonFictionBooksEntity,String> {

}
