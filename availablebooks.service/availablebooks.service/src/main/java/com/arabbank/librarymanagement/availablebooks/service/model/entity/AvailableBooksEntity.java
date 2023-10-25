package com.arabbank.librarymanagement.availablebooks.service.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.stereotype.Indexed;

@NoArgsConstructor
@Data
public class AvailableBooksEntity {


    private String bookName;
    private String authorName;


    private String publisherName;


}
