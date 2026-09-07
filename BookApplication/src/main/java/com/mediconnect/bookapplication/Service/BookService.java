package com.mediconnect.bookapplication.Service;


import com.mediconnect.bookapplication.Model.Book;
import com.mediconnect.bookapplication.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(String isbn) {
        bookRepository.delete(isbn);
    }

    public Book getBook(String isbn) {
        return bookRepository.find(isbn);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}