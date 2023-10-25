package com.arabbank.librarymanagement.availablebooks.service.feignclient;

import com.arabbank.librarymanagement.availablebooks.service.model.dto.FictionBookResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value ="fictionfeign",url = "${feign.client.fiction-book-api.url}")
public interface FictionBookClient {
    @GetMapping("/book")
    List<FictionBookResponseDto> getBooks(@RequestParam(required = false) String bookName,@RequestParam(required = false) String authorName,@RequestParam(required = false) String publisherName);

}
