package com.arabbank.librarymanagement.nonfiction.service.model.entity;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "nonFictionBooks")
@NoArgsConstructor
public class NonFictionBooksEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String bookName;
    private String authorName;
    private int publicationYear;
    private int bookRating;
    private String  publisherName;
    private String bookDescription;
    private int bookPrice;
}
