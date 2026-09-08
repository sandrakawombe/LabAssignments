package com.mediconnect.bookclient.Runner;

import com.mediconnect.bookclient.Client.BookRestClient;
import com.mediconnect.bookclient.Model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Component
public class BookClientRunner implements CommandLineRunner {

    private final BookRestClient bookRestClient;

    public BookClientRunner(BookRestClient bookRestClient) {
        this.bookRestClient = bookRestClient;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== BookClient started ===");

        try {
            Book created = bookRestClient.addBook(
                    new Book("978-3-16-148410-0", "Craig Walls", "Spring in Action", 44.99));
            System.out.println("Added   : " + created);

            Book second = bookRestClient.addBook(
                    new Book("978-0-13-235088-4", "Robert C. Martin", "Clean Code", 39.50));
            System.out.println("Added   : " + second);

            Book fetched = bookRestClient.getBook("978-3-16-148410-0");
            System.out.println("Fetched : " + fetched);

            fetched.setPrice(49.99);
            Book updated = bookRestClient.updateBook(fetched);
            System.out.println("Updated : " + updated);

            List<Book> all = bookRestClient.getAllBooks();
            System.out.println("All books (" + all.size() + "):");
            all.forEach(book -> System.out.println("  " + book));

            bookRestClient.deleteBook("978-0-13-235088-4");
            System.out.println("Deleted : 978-0-13-235088-4");

            System.out.println("Remaining books: " + bookRestClient.getAllBooks());
            System.out.println("=== All operations completed ===");

        } catch (ResourceAccessException e) {
            System.err.println();
            System.err.println("!!! Could not reach the BookApplication.");
            System.err.println("!!! Start it first and make sure it is listening on port 8080,");
            System.err.println("!!! then run BookClient again.");
            System.err.println();
        }
    }
}