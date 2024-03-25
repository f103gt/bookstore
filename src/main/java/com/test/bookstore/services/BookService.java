package com.test.bookstore.services;

import com.test.bookstore.Book;
import com.test.bookstore.ReactorBookServiceGrpc;
import com.test.bookstore.dtos.BookMapper;
import com.test.bookstore.entities.BookEntity;
import com.test.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class BookService extends ReactorBookServiceGrpc.BookServiceImplBase {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    //TODO ERROR HANDLING
    @Override
    public Mono<Book> createBook(Book request) {
        BookEntity entity = bookMapper.bookToBookEntity(request);
        return repository.save(entity)
                .map(bookMapper::bookEntityToBook);
    }

}
