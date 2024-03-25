package com.test.bookstore.services;

import com.test.bookstore.Book;
import com.test.bookstore.ReactorBookServiceGrpc;
import com.test.bookstore.dtos.BookMapper;
import com.test.bookstore.repositories.BookRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Mono;

import java.io.IOException;

@SpringBootTest
@Testcontainers
class BookServiceTest  {

    private Server server;
    @Autowired
    private BookRepository repository;
    @Autowired
    private BookMapper bookMapper;

    private ManagedChannel channel;
    @Autowired
    private BookService service;

    @BeforeEach
    public void startServerAndClient() throws IOException {
        // Initialize and start your server
        server = ServerBuilder.forPort(8080)
                .addService(new BookService(repository, bookMapper))
                .build()
                .start();

        // Initialize your channel
        channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();
    }

    @AfterEach
    public void stopServerAndClient() {
        // Shutdown your server and channel
        server.shutdown();
        channel.shutdown();
    }

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.1"));

    //TODO EXTRACT INTO APPLICATION YAML
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://"
                + postgreSQLContainer.getHost() + ":"
                + postgreSQLContainer.getMappedPort(5432) + "/"
                + postgreSQLContainer.getDatabaseName());
        registry.add("spring.r2dbc.username",postgreSQLContainer::getUsername);
        registry.add("spring.r2dbc.password",postgreSQLContainer::getPassword);
        registry.add("spring.r2dbc.initialization-mode",()->"always");
    }

    @Test
    public void testBookCreation(){
        ReactorBookServiceGrpc.ReactorBookServiceStub stub =
                ReactorBookServiceGrpc.newReactorStub(channel);

        Book request = Book.newBuilder()
                .setTitle("The Hitchhiker's Guide to the Galaxy")
                .setAuthor("Douglas Adams")
                .setIsbn("1234567890")
                .setQuantity(10)
                .build();

        // Call your BookService using the test client
        Mono<Book> responseMono = service.createBook(request);
        Book response = responseMono.block();
        assert response != null;
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(request.getTitle(), response.getTitle());
        Assertions.assertEquals(request.getAuthor(), response.getAuthor());
        Assertions.assertEquals(request.getIsbn(), response.getIsbn());
        Assertions.assertEquals(request.getQuantity(), response.getQuantity());
    }


}

