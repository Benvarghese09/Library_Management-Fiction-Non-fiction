package com.arabbank.librarymanagement.availablebooks.service.feignclient;

import com.arabbank.librarymanagement.availablebooks.service.model.dto.NonFictionBookResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "nonfictionfeign",url = "${feign.client.non-fiction-book-api.url}")
public interface NonFictionBookClient {
    @GetMapping("/book")
    List<NonFictionBookResponseDto> getBooks(@RequestParam(required = false) String bookName,@RequestParam(required = false) String authorName,@RequestParam(required = false) String publisherName);

}
