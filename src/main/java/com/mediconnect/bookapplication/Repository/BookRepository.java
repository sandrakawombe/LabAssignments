package com.mediconnect.bookapplication.Repository;

import com.mediconnect.bookapplication.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
