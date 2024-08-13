package com.book.store.delegate;


import com.book.store.api.BooksListApiDelegate;
import com.book.store.model.BookDTO;
import com.book.store.service.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksListApiDelegateImpl implements BooksListApiDelegate {

    private final BookService bookService;

    public BooksListApiDelegateImpl(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public ResponseEntity<List<BookDTO>> booksListGet(String title)  {
        List<BookDTO> books = bookService.getBooks(title);
        return ResponseEntity.ok(books);
    }
}
