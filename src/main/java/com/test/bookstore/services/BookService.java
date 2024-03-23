package com.test.bookstore.services;

import com.test.bookstore.Book;
import com.test.bookstore.ReactorBookServiceGrpc;
import com.test.bookstore.dtos.BookMapper;
import com.test.bookstore.models.BookModel;
import com.test.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@GrpcService
@RequiredArgsConstructor
public class BookService extends ReactorBookServiceGrpc.BookServiceImplBase {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    @Override
    public Mono<Book> createBook(Book request) {
        BookModel model = bookMapper.bookToBookModel(request);
        //do I need to check if uuid is not the same
        model.setId(generateUUID());
        return Mono.fromCallable(()->repository.save(model))
                .subscribeOn(Schedulers.boundedElastic())
                .map(bookMapper::bookModelToBook);
    }

    private String generateUUID(){return null;}
}
