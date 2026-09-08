package com.mediconnect.bookclient.Client;

import com.mediconnect.bookclient.Model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Consumes the REST interface of the BookApplication (/books) via RestTemplate.
 */
@Service
public class BookRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public BookRestClient(RestTemplate restTemplate,
                          @Value("${book.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /** POST /books */
    public Book addBook(Book book) {
        return restTemplate.postForObject(baseUrl, book, Book.class);
    }

    /** PUT /books */
    public Book updateBook(Book book) {
        ResponseEntity<Book> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.PUT,
                new HttpEntity<>(book),
                Book.class);
        return response.getBody();
    }

    /** DELETE /books/{isbn} */
    public void deleteBook(String isbn) {
        restTemplate.delete(baseUrl + "/{isbn}", isbn);
    }

    /** GET /books/{isbn} */
    public Book getBook(String isbn) {
        return restTemplate.getForObject(baseUrl + "/{isbn}", Book.class, isbn);
    }

    /** GET /books */
    public List<Book> getAllBooks() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        List<Book> books = response.getBody();
        return books != null ? books : Collections.emptyList();
    }
}