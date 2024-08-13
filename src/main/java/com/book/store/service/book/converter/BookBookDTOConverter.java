package com.book.store.service.book.converter;

import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.model.BookDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookBookDTOConverter implements Converter<Book, BookDTO> {

    @Override
    public BookDTO convert(Book book) {
        BookDTO bookDto = new BookDTO();

        // TODO refactor the authString out of here to a more suitable place
        List<String> authorString = book.getAuthorList()
            .stream()
            .map((Author::getPseudonym))
            .collect(Collectors.toList());

        bookDto.setPrice(book.getPrice());
        bookDto.setAuthors(authorString);
        bookDto.id(book.getId());
        bookDto.description(book.getDescription());
        bookDto.title(book.getTitle());

        return bookDto;
    }
}
