package com.test.bookstore.services;

import com.google.protobuf.Empty;
import com.test.bookstore.Book;
import com.test.bookstore.BookAuthorRequest;
import com.test.bookstore.BookIDRequest;
import com.test.bookstore.NewBookRequest;
import com.test.bookstore.configurations.BookServiceIntegrationTestConfig;
import com.test.bookstore.entities.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceIntegrationTest extends BookServiceIntegrationTestConfig {

    @Test
    public void testCreateBook_Success() {
        NewBookRequest request = NewBookRequest.newBuilder()
                .setTitle("The Hitchhiker's Guide to the Galaxy")
                .setAuthor("Douglas Adams")
                .setIsbn("1234567890")
                .setQuantity(10)
                .build();

        Mono<Book> responseMono = stub.createBook(request);
        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    Assertions.assertNotNull(response.getId());
                    Assertions.assertNotEquals("", response.getId());
                    assertEquals(request.getTitle(), response.getTitle());
                    assertEquals(request.getAuthor(), response.getAuthor());
                    assertEquals(request.getIsbn(), response.getIsbn());
                    assertEquals(request.getQuantity(), response.getQuantity());

                })
                .verifyComplete();
    }

    @Test
    public void testCreateBook_ISBNAlreadyExists_ALREADY_EXISTS_EXCEPTION() {
        BookEntity entity = BookEntity.builder()
                .title("Mock Title")
                .author("Mock author")
                .isbn("Mock ISBN")
                .quantity(10)
                .build();

        NewBookRequest request = NewBookRequest.newBuilder()
                .setTitle("The Hitchhiker's Guide to the Galaxy")
                .setAuthor("Douglas Adams")
                .setIsbn("Mock ISBN")
                .setQuantity(10)
                .build();

        Mono<Book> responseMono = repository.save(entity)
                .then(stub.createBook(request));

        StepVerifier.create(responseMono)
                .expectErrorMessage("ALREADY_EXISTS: Failed to create book: A book with this ISBN already exists.")
                .verify();
    }

    @Test
    public void testBookUpdate_Success() {
        Mono<BookEntity> entityMono = repository.save(BookEntity.builder()
                .title("Mock Title")
                .author("Mock author")
                .isbn("Mock ISBN")
                .quantity(10)
                .build());

        Mono<Book> requestMono = entityMono.flatMap(entity -> {
            Book request = Book.newBuilder()
                    .setId(entity.getId().toString())
                    .setTitle("Title")
                    .setAuthor("Author")
                    .setIsbn("ISBN")
                    .setQuantity(10)
                    .build();
            return Mono.just(request);
        });

        Mono<Book> responseMono = stub.updateBook(requestMono);
        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertEquals("Title", response.getTitle());
                    assertEquals("Author", response.getAuthor());
                    assertEquals("ISBN", response.getIsbn());
                    assertEquals(10, response.getQuantity());

                })
                .verifyComplete();
    }

    @Test
    public void testUpdateBook_Throws_ALEREADY_EXISTS() {
        List<BookEntity> savedBooks = Flux.just(
                        BookEntity.builder().title("Book Title 1").author("Author").isbn("ISBN 1").quantity(10).build(),
                        BookEntity.builder().title("Book Title 2").author("Author").isbn("ISBN 2").quantity(20).build())
                .flatMap(repository::save)
                .collectList()
                .block();

        BookEntity firstSavedBook = savedBooks.get(0);

        BookEntity bookBeforeUpdate = repository.findBookEntityById(firstSavedBook.getId()).block();

        Book request = Book.newBuilder()
                .setId(firstSavedBook.getId().toString())
                .setTitle("Title")
                .setAuthor("Author")
                .setIsbn("ISBN 2")
                .setQuantity(10)
                .build();

        Mono<Book> responseMono = stub.updateBook(Mono.just(request));

        StepVerifier.create(responseMono)
                .expectErrorMessage("ALREADY_EXISTS: Failed to update book: A book with this ISBN already exists.")
                .verify();

        BookEntity bookAfterUpdate = repository.findBookEntityById(firstSavedBook.getId()).block();

        assertEquals(bookBeforeUpdate.getIsbn(), bookAfterUpdate.getIsbn());
    }



    @Test
    public void testBookUpdate_Throws_NOT_FOUDN_EXCETPION() {
        Book request = Book.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setTitle("Title")
                .setAuthor("Author")
                .setIsbn("ISBN")
                .setQuantity(10)
                .build();

        Mono<Book> responseMono = stub.updateBook(request);
        StepVerifier.create(responseMono)
                .expectErrorMessage("NOT_FOUND: Book with provided id not found")
                .verify();
    }

    @Test
    public void testGetBooksByAuthor_ReturnsBooks() {

        Mono<Void> saveBooksMono = Flux.just(
                        BookEntity.builder().title("Book Title 1").author("Author").isbn("ISBN 1").quantity(10).build(),
                        BookEntity.builder().title("Book Title 2").author("Author").isbn("ISBN 2").quantity(20).build())
                .flatMap(repository::save)
                .then();
        saveBooksMono.block();

        Flux<Book> booksResponse = stub.getBooksByAuthor(BookAuthorRequest.newBuilder()
                .setAuthor("Author")
                .build());
        StepVerifier.create(booksResponse)
                .assertNext(book -> {
                    assertEquals("Book Title 1", book.getTitle());
                    assertEquals("Author", book.getAuthor());
                    assertEquals("ISBN 1", book.getIsbn());
                    assertEquals(10, book.getQuantity());

                })
                .assertNext(book -> {
                    assertEquals("Book Title 2", book.getTitle());
                    assertEquals("Author", book.getAuthor());
                    assertEquals("ISBN 2", book.getIsbn());
                    assertEquals(20, book.getQuantity());

                })
                .verifyComplete();
    }

    @Test
    public void testGetBooksByAuthor_Throws_NOT_FOUND_EXCETION() {
        Flux<Book> booksResponse = stub.getBooksByAuthor(BookAuthorRequest.newBuilder()
                .setAuthor("Author")
                .build());
        StepVerifier.create(booksResponse)
                .expectErrorMessage("NOT_FOUND: Failed to fetch books by author: No books found for the author")
                .verify();
    }

    @Test
    public void testDeleteBookByID_DeletesBook() {

        Mono<BookEntity> entityMono = repository.save(BookEntity.builder()
                        .title("Mock Title")
                        .author("Mock author")
                        .isbn("Mock ISBN")
                        .quantity(10)
                        .build())
                .share();
        entityMono.flatMap(entity -> {
                    return stub.deleteBookById(BookIDRequest.newBuilder()
                            .setId(entity.getId().toString())
                            .build());
                }).as(StepVerifier::create)
                .expectNextMatches(deletedBook -> deletedBook instanceof Empty)
                .verifyComplete();

        entityMono
                .flatMap(entity ->
                        repository.findById(entity.getId()))
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }


    @Test
    public void testDeleteBookByID_Throws_NOT_FOUND_EXCEPTION() {
        Mono<Empty> response = stub.deleteBookById(BookIDRequest.newBuilder()
                .setId(UUID.randomUUID().toString())
                .build());
        StepVerifier.create(response)
                .expectErrorMessage("NOT_FOUND: Failed to delete book by id: Book with provided id does not exist")
                .verify();
    }


}
