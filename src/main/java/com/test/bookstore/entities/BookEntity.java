package com.test.bookstore.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "book")
public class BookEntity {
    @Id
    private UUID id;
    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
}
