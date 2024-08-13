package com.book.store.service.book.converter;

import com.book.store.entity.Book;
import com.book.store.model.NewBookDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewBookDTOBookConverter implements Converter<NewBookDTO, Book> {


    @Override
    public Book convert(NewBookDTO dto) {
        return Book.builder()
            .price(dto.getPrice())
            .title(dto.getTitle())
            .description(dto.getDescription())
            .build();
    }
}
