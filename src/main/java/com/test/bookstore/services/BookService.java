package com.test.bookstore.services;

import com.google.protobuf.Empty;
import com.test.bookstore.Book;
import com.test.bookstore.NewBookRequest;
import com.test.bookstore.BookAuthorRequest;
import com.test.bookstore.BookIDRequest;
import com.test.bookstore.ReactorBookServiceGrpc;
import com.test.bookstore.dtos.BookMapper;
import com.test.bookstore.entities.BookEntity;
import com.test.bookstore.repositories.BookRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@GrpcService
@RequiredArgsConstructor
public class BookService extends ReactorBookServiceGrpc.BookServiceImplBase {

    private final BookRepository repository;
    private final BookMapper bookMapper;

    private <T> Mono<BookEntity> validateAndMap(Mono<T> request, Function<T, BookEntity> mapper) {
        return request
                .map(mapper)
                .onErrorResume(e -> {
                    Status status = Status.INVALID_ARGUMENT.withDescription(e.getMessage());
                    return Mono.error(status.asRuntimeException());
                });
    }

    private <T> Mono<T> validateNotEmpty(Mono<T> request) {
        return request.switchIfEmpty(Mono.defer(() -> {
            Status status = Status.INVALID_ARGUMENT.withDescription("Request cannot be null");
            return Mono.error(status.asRuntimeException());
        }));
    }

    private Mono<Book> handleErrors(Mono<Book> bookMono) {
        return bookMono.onErrorResume(e -> {
            if (e instanceof StatusRuntimeException || e instanceof StatusException) {
                return Mono.error(e);
            }
            Status status = Status.INTERNAL.withDescription("An unexpected error occurred: " + e.getMessage());
            return Mono.error(status.asRuntimeException());
        });
    }

    private Flux<Book> handleErrorsFlux(Flux<Book> bookFlux) {
        return bookFlux.onErrorResume(e -> {
            if (e instanceof StatusRuntimeException || e instanceof StatusException) {
                return Flux.error(e);
            }
            Status status = Status.INTERNAL.withDescription("An unexpected error occurred: " + e.getMessage());
            return Flux.error(status.asRuntimeException());
        });
    }

    private Mono<BookEntity> updateBookEntity( BookEntity existingEntity, BookEntity entity){
        existingEntity.setTitle(entity.getTitle());
        existingEntity.setAuthor(entity.getAuthor());
        existingEntity.setIsbn(entity.getIsbn());
        existingEntity.setQuantity(entity.getQuantity());
        return repository.save(existingEntity);
    }
    @Override
    public Mono<Book> createBook(Mono<NewBookRequest> request) {
        return validateNotEmpty(request)
                .flatMap(req -> validateAndMap(request, bookMapper::newBookRequestToBookEntity))
                .flatMap(entity -> repository.findBookEntityByIsbn(entity.getIsbn())
                        .flatMap(existingBook -> {
                            Status status = Status.ALREADY_EXISTS
                                    .withDescription("Failed to create book: A book with this ISBN already exists.");
                            return Mono.error(status.asRuntimeException());
                        })
                        .switchIfEmpty(repository.save(entity))
                )
                .map(entity -> bookMapper.bookEntityToBook((BookEntity) entity))
                .transform(this::handleErrors);
    }

    @Override
    public Mono<Book> updateBook(Mono<Book> request) {
        return validateNotEmpty(request)
                .flatMap(book -> validateAndMap(request, bookMapper::bookToBookEntity))
                .flatMap(entity -> repository.findBookEntityById(entity.getId())
                        .switchIfEmpty(Mono.error(
                                Status.NOT_FOUND
                                        .withDescription("Book with provided id not found")
                                        .asRuntimeException()))
                        .flatMap(existingEntity -> {
                            return repository.findBookEntityByIsbn(entity.getIsbn())
                                    .filter(bookEntity -> !bookEntity.getId().equals(existingEntity.getId()))
                                    .hasElement()
                                    .flatMap(isbnExists -> {
                                        if (isbnExists) {
                                            return Mono.error(Status.
                                                    ALREADY_EXISTS
                                                    .withDescription("Failed to update book: A book with this ISBN already exists.")
                                                    .asRuntimeException());
                                        } else {
                                            return updateBookEntity(existingEntity, entity);
                                        }
                                    });

                        }))
                .map(bookMapper::bookEntityToBook)
                .transform(this::handleErrors);
    }

    @Override
    public Flux<Book> getBooksByAuthor(Mono<BookAuthorRequest> request) {
        return validateNotEmpty(request)
                .flatMapMany(bookAuthorRequest -> repository.findAllByAuthor(bookAuthorRequest.getAuthor())
                        .map(bookMapper::bookEntityToBook)
                        .switchIfEmpty(Mono.defer(() -> {
                            Status status = Status.NOT_FOUND
                                    .withDescription("Failed to fetch books by author: No books found for the author");
                            return Mono.error(status.asRuntimeException());
                        }))
                        .transform(this::handleErrorsFlux)
                );
    }

    @Override
    public Mono<Book> getBookById(Mono<BookIDRequest> request) {
        return validateNotEmpty(request)
                .map(BookIDRequest::getId)
                .map(UUID::fromString)
                .flatMap(repository::findBookEntityById)
                .switchIfEmpty(Mono.defer(() -> {
                    Status status = Status.NOT_FOUND.withDescription("Failed to fetch book by id: Book with id does not exist");
                    return Mono.error(status.asRuntimeException());

                }))
                .map(bookMapper::bookEntityToBook)
                .transform(this::handleErrors);
    }


    @Override
    public Mono<Empty> deleteBookById(Mono<BookIDRequest> request) {
        return validateNotEmpty(request)
                .map(BookIDRequest::getId)
                .map(UUID::fromString)
                .flatMap(id -> repository.findBookEntityById(id)
                        .switchIfEmpty(Mono.defer(() -> {
                            Status status = Status.NOT_FOUND.withDescription("Failed to delete book by id: Book with provided id does not exist");
                            return Mono.error(status.asRuntimeException());
                        }))
                        .flatMap((book) -> repository.deleteBookEntityById(book.getId()))
                )
                .onErrorResume(e -> {
                    if (e instanceof StatusRuntimeException) {
                        return Mono.error(e);
                    }
                    Status status = Status.INTERNAL.withDescription("An error occurred while deleting the book: " + e.getMessage());
                    return Mono.error(status.asRuntimeException());

                })
                .then(Mono.just(Empty.newBuilder().build()));
    }
}
