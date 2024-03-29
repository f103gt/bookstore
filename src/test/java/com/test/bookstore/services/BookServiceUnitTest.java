package com.test.bookstore.services;

import com.google.protobuf.Empty;
import com.test.bookstore.Book;
import com.test.bookstore.BookAuthorRequest;
import com.test.bookstore.BookIDRequest;
import com.test.bookstore.NewBookRequest;
import com.test.bookstore.dtos.BookMapper;
import com.test.bookstore.entities.BookEntity;
import com.test.bookstore.repositories.BookRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {
    @Mock
    private BookRepository repository;

    @Mock
    private BookMapper mapper;
    @InjectMocks
    private BookService service;

    private final UUID id = UUID.randomUUID();
    private final Book book = Book.newBuilder()
            .setId(id.toString())
            .setTitle("Title")
            .setAuthor("Author")
            .setIsbn("ISBN")
            .setQuantity(10)
            .build();

    private final BookEntity entity = new BookEntity(id, "Title", "Author", "ISBN", 10);

    public void verifyErrorMono(Mono<Book> mono, Status.Code expectedStatusCode) {
        StepVerifier.create(mono)
                .expectErrorMatches(throwable -> throwable instanceof StatusRuntimeException
                        && ((StatusRuntimeException) throwable).getStatus().getCode() == expectedStatusCode)
                .verify();
    }

    public void verifyError(Flux<Book> flux, Status.Code expectedStatusCode) {
        StepVerifier.create(flux)
                .expectErrorMatches(throwable -> throwable instanceof StatusRuntimeException
                        && ((StatusRuntimeException) throwable).getStatus().getCode() == expectedStatusCode)
                .verify();
    }

    @Test
    public void testCreateBook_NegativeQuantity_INVALID_AGRUMENT_EXCETPION() {
        NewBookRequest request = NewBookRequest.newBuilder().setQuantity(-1).build();

        verifyErrorMono(service.createBook(Mono.just(request)), Status.INVALID_ARGUMENT.getCode());

    }

    @Test
    public void testCreateBook_EmptyTitle_INVALID_AGRUMENT_EXCETPION() {
        NewBookRequest request = NewBookRequest.newBuilder().setTitle("").build();

        verifyErrorMono(service.createBook(Mono.just(request)), Status.INVALID_ARGUMENT.getCode());
    }


    @Test
    public void testCreateBook_RepositoryException_INTERNAL_ERROR() {
        NewBookRequest request = NewBookRequest.newBuilder().build();
        BookEntity bookEntity = new BookEntity();
        RuntimeException runtimeException = new RuntimeException("Unknown error");

        when(mapper.newBookRequestToBookEntity(any())).thenReturn(bookEntity);
        when(repository.save(any())).thenThrow(runtimeException);

        verifyErrorMono(service.createBook(Mono.just(request)), Status.INTERNAL.getCode());

        verify(mapper).newBookRequestToBookEntity(request);
        verify(repository).save(bookEntity);
    }

    @Test
    public void testCreateBook_NullRequest_INVALID_AGRUMENT_EXCETPION() {
        verifyErrorMono(service.createBook(Mono.justOrEmpty(null)), Status.INVALID_ARGUMENT.getCode());
    }


    @Test
    public void testCreateBook_Success() {
        NewBookRequest request = NewBookRequest.newBuilder()
                .setTitle("Title")
                .setAuthor("Author")
                .setIsbn("ISBN")
                .setQuantity(10)
                .build();
        when(mapper.newBookRequestToBookEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.bookEntityToBook(entity)).thenReturn(book);

        StepVerifier.create(service.createBook(Mono.just(request)))
                .expectNextMatches(createdBook -> createdBook.getId().equals(book.getId()))
                .verifyComplete();
    }

    @Test
    public void testUpdateBook_NullRequest_INVALID_AGRUMENT_EXCETPION() {
        verifyErrorMono(service.updateBook(Mono.justOrEmpty(null)), Status.INVALID_ARGUMENT.getCode());
    }

    @Test
    public void testUpdateBook_NonExistentBook_NOT_FOUND_EXCEPTION() {
        when(mapper.bookToBookEntity(any(Book.class))).thenReturn(entity);
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.empty());

        verifyErrorMono(service.updateBook(Mono.just(book)), Status.NOT_FOUND.getCode());

        verify(repository).findBookEntityById(UUID.fromString(book.getId()));
    }


    @Test
    public void testUpdateBook_RepositoryException_INTERNAL_ERROR() {
        RuntimeException runtimeException = new RuntimeException("Unknown error");

        when(mapper.bookToBookEntity(any(Book.class))).thenReturn(entity);
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.just(new BookEntity()));
        when(repository.save(any())).thenThrow(runtimeException);

        StepVerifier.create(service.updateBook(Mono.just(book)))
                .expectErrorMatches(throwable -> throwable instanceof StatusRuntimeException
                        && ((StatusRuntimeException) throwable).getStatus().getCode() == Status.INTERNAL.getCode()
                        && throwable.getMessage().contains(runtimeException.getMessage()))
                .verify();

        verify(repository).findBookEntityById(UUID.fromString(book.getId()));
        verify(repository).save(any());
    }

    @Test
    public void testUpdateBook_Success() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.just(entity));
        when(repository.save(any(BookEntity.class))).thenReturn(Mono.just(entity));
        when(mapper.bookToBookEntity(any(Book.class))).thenReturn(entity);
        when(mapper.bookEntityToBook(any(BookEntity.class))).thenReturn(book);

        Mono<Book> result = service.updateBook(Mono.just(book));

        StepVerifier.create(result)
                .expectNextMatches(book -> book.getTitle().equals(book.getTitle()))
                .verifyComplete();
    }


    @Test
    public void testGetBooksByAuthor_NotFound_NOT_FOUND_EXCETPION() {
        when(repository.findAllByAuthor(anyString())).thenReturn(Flux.empty());

        verifyError(service.getBooksByAuthor(Mono.just(BookAuthorRequest.newBuilder()
                        .setAuthor("Unknown Author").build())),
                Status.NOT_FOUND.getCode());

        verify(repository).findAllByAuthor("Unknown Author");
    }

    @Test
    public void testGetBooksByAuthor_INTERNAL_ERROR() {
        when(repository.findAllByAuthor(anyString())).thenReturn(Flux.error(new RuntimeException("Database error")));

        verifyError(service.getBooksByAuthor(Mono.just(BookAuthorRequest.newBuilder()
                        .setAuthor("Any Author").build())),
                Status.INTERNAL.getCode());

        verify(repository).findAllByAuthor("Any Author");
    }


    @Test
    public void testGetBookById_BookExists_Success() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.just(entity));
        when(mapper.bookEntityToBook(any(BookEntity.class))).thenReturn(book);

        StepVerifier.create(service.getBookById(Mono.just(BookIDRequest.newBuilder().setId(id.toString()).build())))
                .expectNextMatches(createdBook -> createdBook.getId().equals(book.getId()))
                .verifyComplete();

        verify(repository).findBookEntityById(id);
    }

    @Test
    public void testGetBookById_BookDoesNotExist_NOT_FOUND_EXCEPTION() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.empty());

        verifyErrorMono(service.getBookById(Mono.just(BookIDRequest.newBuilder().setId(id.toString()).build())),
                Status.NOT_FOUND.getCode());

        verify(repository).findBookEntityById(id);
    }

    @Test
    public void testGetBookById_INTERNAL_ERROR() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.error(new RuntimeException("Database error")));

        verifyErrorMono(service.getBookById(Mono.just(BookIDRequest.newBuilder().setId(id.toString()).build())),
                Status.INTERNAL.getCode());

        verify(repository).findBookEntityById(id);
    }

    @Test
    public void testDeleteBookById_BookExists_Success() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.just(entity));
        when(repository.deleteBookEntityById(any(UUID.class))).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteBookById(Mono.just(
                        BookIDRequest.newBuilder()
                                .setId(id.toString())
                                .build())))
                .expectNextMatches(empty -> true)
                .expectComplete()
                .verify();

        verify(repository).deleteBookEntityById(id);
    }


    @Test
    public void testDeleteBookById_BookDoesNotExist_NOT_FOUND_EXCEPTION() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.empty());

        verifyErrorMonoEmpty(service.deleteBookById(Mono.just(BookIDRequest.newBuilder().setId(id.toString()).build())),
                Status.NOT_FOUND.getCode());

        verify(repository).findBookEntityById(id);
    }

    @Test
    public void testDeleteBookById_INTERNAL_ERROR() {
        when(repository.findBookEntityById(any(UUID.class))).thenReturn(Mono.error(new RuntimeException("Database error")));

        verifyErrorMonoEmpty(service.deleteBookById(Mono.just(BookIDRequest.newBuilder().setId(id.toString()).build())),
                Status.INTERNAL.getCode());

        verify(repository).findBookEntityById(id);
    }


    public void verifyErrorMonoEmpty(Mono<Empty> mono, Status.Code expectedStatusCode) {
        StepVerifier.create(mono)
                .expectErrorMatches(throwable -> throwable instanceof StatusRuntimeException
                        && ((StatusRuntimeException) throwable).getStatus().getCode() == expectedStatusCode)
                .verify();
    }

}
