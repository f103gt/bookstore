package com.test.bookstore.dtos;

import com.test.bookstore.Book;
import com.test.bookstore.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(target = "id",expression = "java(getUUIDFromString(book.getId()))")
    BookEntity bookToBookEntity(Book book);

    @Mapping(target = "id",expression = "java(getStringFromUUID(bookEntity.getId()))")
    Book bookEntityToBook(BookEntity bookEntity);

    default UUID getUUIDFromString(String uuid){
        return uuid != null && !uuid.isEmpty() ? UUID.fromString(uuid) : null;
    }

    default String getStringFromUUID(UUID uuid){
        return uuid != null ? uuid.toString() : null;
    }
}
