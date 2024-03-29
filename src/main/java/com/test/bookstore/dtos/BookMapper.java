package com.test.bookstore.dtos;

import com.test.bookstore.Book;
import com.test.bookstore.NewBookRequest;
import com.test.bookstore.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(target = "id",expression = "java(getUUIDFromString(request.getId()))")
    @Mapping(target = "quantity", expression = "java(validateQuantity(request.getQuantity()))")
    @Mapping(target = "title", expression = "java(validateString(request.getTitle(),\"title\"))")
    @Mapping(target = "author", expression = "java(validateString(request.getAuthor(),\"author\"))")
    @Mapping(target = "isbn", expression = "java(validateString(request.getIsbn(),\"isbn\"))")
    BookEntity bookToBookEntity(Book request);

    @Mapping(target = "id",expression = "java(getStringFromUUID(entity.getId()))")
    Book bookEntityToBook(BookEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantity", expression = "java(validateQuantity(request.getQuantity()))")
    @Mapping(target = "title", expression = "java(validateString(request.getTitle(),\"title\"))")
    @Mapping(target = "author", expression = "java(validateString(request.getAuthor(),\"author\"))")
    @Mapping(target = "isbn", expression = "java(validateString(request.getIsbn(),\"isbn\"))")
    BookEntity newBookRequestToBookEntity(NewBookRequest request);
    default UUID getUUIDFromString(String uuid) {
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    default String getStringFromUUID(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    default Integer validateQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        return quantity;
    }

    default String validateString(String field, String fieldName) {
        if (field.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return field;
    }
}
