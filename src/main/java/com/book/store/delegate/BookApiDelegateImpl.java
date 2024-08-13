package com.book.store.delegate;

import com.book.store.api.BookApiDelegate;

import com.book.store.model.BookDTO;
import com.book.store.model.NewBookDTO;
import com.book.store.service.book.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookApiDelegateImpl implements BookApiDelegate {

    private final BookService bookService;

    public BookApiDelegateImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Void> bookDelete(Long id)  {
        bookService.deleteBook(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<BookDTO> bookGet(Long id)  {
        BookDTO bookById = bookService.getBookById(id);
        return ResponseEntity.ok(bookById);
    }

    @Override
    public ResponseEntity<BookDTO> bookPost(NewBookDTO newBookDTO){
        BookDTO newBook = bookService.createBookEntry(newBookDTO);
        return ResponseEntity.ok(newBook);
    }
}
