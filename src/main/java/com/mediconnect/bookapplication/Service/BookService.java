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
        if (bookRepository.existsById(book.getIsbn())) {
            return null;
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        if (!bookRepository.existsById(book.getIsbn())) {
            return null;
        }
        return bookRepository.save(book);
    }

    public boolean deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            return false;
        }
        bookRepository.deleteById(isbn);
        return true;
    }

    public Book getBook(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}