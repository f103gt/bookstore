package com.test.bookstore.repositories;

import com.test.bookstore.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, String> {
}
