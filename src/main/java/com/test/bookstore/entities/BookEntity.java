package com.test.bookstore.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Author cannot be empty")
    @NotBlank(message = "Author cannot be empty")
    private String author;

    @NotNull(message = "ISBN cannot be empty")
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;

    @Min(value = 0, message = "Quantity cannot be less than 0")
    private Integer quantity;
}
