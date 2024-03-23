package com.test.bookstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "book")
public class BookModel {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
}
