package com.test.bookstore.repositories;

import com.test.bookstore.entities.BookEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BookRepository extends R2dbcRepository<BookEntity, UUID> {
    Mono<BookEntity> findBookEntityById(UUID id);
    Flux<BookEntity> findAllByAuthor(String author);

    Mono<Void> deleteBookEntityById(UUID id);

    Mono<BookEntity> findBookEntityByIsbn(String isbn);
}
