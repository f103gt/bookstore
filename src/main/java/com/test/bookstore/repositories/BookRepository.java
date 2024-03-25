package com.test.bookstore.repositories;

import com.test.bookstore.entities.BookEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends R2dbcRepository<BookEntity, String> {
}
