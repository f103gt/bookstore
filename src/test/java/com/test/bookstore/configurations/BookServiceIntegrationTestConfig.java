package com.test.bookstore.configurations;

import com.test.bookstore.ReactorBookServiceGrpc;
import com.test.bookstore.dtos.BookMapper;
import com.test.bookstore.repositories.BookRepository;
import com.test.bookstore.services.BookService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

@SpringBootTest
@Testcontainers
@Configuration
public class BookServiceIntegrationTestConfig {
    private Server server;
    @Autowired
    protected BookRepository repository;

    @Autowired
    protected BookMapper bookMapper;

    @Autowired
    private DatabaseClient databaseClient;

    private ManagedChannel channel;

    protected ReactorBookServiceGrpc.ReactorBookServiceStub stub;

    @BeforeEach
    public void startServerAndClient() throws IOException {
        // Initialize and start your server
        server = ServerBuilder.forPort(8081)
                .addService(new BookService(repository, bookMapper))
                .build()
                .start();

        // Initialize your channel
        channel = ManagedChannelBuilder
                .forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        stub = ReactorBookServiceGrpc.newReactorStub(channel);

    }

    @AfterEach
    public void stopServerAndClient() {
        // Shutdown your server and channel
        server.shutdown();
        channel.shutdown();
    }


    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine"));

    //TODO EXTRACT INTO APPLICATION YAML
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://"
                + postgreSQLContainer.getHost() + ":"
                + postgreSQLContainer.getMappedPort(5432) + "/"
                + postgreSQLContainer.getDatabaseName());
        registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername);
        registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword);
        registry.add("spring.r2dbc.initialization-mode", () -> "always");
    }

    @AfterEach
    public void cleanDatabase() {
        // Clean up data in the table after each test
        databaseClient.sql("TRUNCATE TABLE book").fetch().rowsUpdated().block();
    }
}
