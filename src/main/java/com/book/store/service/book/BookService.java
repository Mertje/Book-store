package com.book.store.service.book;


import com.book.store.model.BookDTO;
import com.book.store.model.NewBookDTO;

import java.util.List;

public interface BookService {
    BookDTO getBookById(Long id);

    BookDTO createBookEntry(NewBookDTO newBookDTO);

    void deleteBook(Long id);

    List<BookDTO> getBooks(String title);
}
