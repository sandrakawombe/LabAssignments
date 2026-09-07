package com.mediconnect.bookapplication.Repository;

import com.mediconnect.bookapplication.Model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    public Book save(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    public void delete(String isbn) {
        books.remove(isbn);
    }

    public Book find(String isbn) {
        return books.get(isbn);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}