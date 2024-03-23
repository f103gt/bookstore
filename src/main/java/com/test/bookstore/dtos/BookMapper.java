package com.test.bookstore.dtos;

import com.test.bookstore.Book;
import com.test.bookstore.models.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    BookModel bookToBookModel(Book book);

    Book bookModelToBook(BookModel bookModel);
}
