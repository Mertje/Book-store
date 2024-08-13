package com.book.store.service.book.Impl;

import com.book.store.constant.ExceptionConstant;
import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.exception.impl.NotFoundException;
import com.book.store.model.BookDTO;
import com.book.store.model.NewBookDTO;
import com.book.store.repository.BookRepository;
import com.book.store.service.author.AuthorService;
import com.book.store.service.book.BookService;
import com.book.store.service.book.converter.BookBookDTOConverter;
import com.book.store.service.book.converter.NewBookDTOBookConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final NewBookDTOBookConverter newBookDTOBookConverter;
    private final BookBookDTOConverter bookBookDTOConverter;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, NewBookDTOBookConverter newBookDTOBookConverter, BookBookDTOConverter bookBookDTOConverter) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.newBookDTOBookConverter = newBookDTOBookConverter;
        this.bookBookDTOConverter = bookBookDTOConverter;
    }

    @Override
    public BookDTO getBookById(final Long id) {
        Book book = getBook(id);
        return bookBookDTOConverter.convert(book);
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
            () -> new NotFoundException(HttpStatus.NOT_FOUND, ExceptionConstant.BOOK_NOT_FOUND)
        );
    }

    @Override
    public BookDTO createBookEntry(NewBookDTO newBookDTO) {
        List<Author> authors = handleAuthorCreation(newBookDTO);
        Book newBook = newBookDTOBookConverter.convert(newBookDTO);
        saveBookAndAuthor(newBook, authors);
        return bookBookDTOConverter.convert(Objects.requireNonNull(newBook));

    }

    @Override
    public void deleteBook(Long id) {
        getBook(id);
        deleteBookByID(id);
    }

    @Override
    public List<BookDTO> getBooks(String title) {
        // Todo set proper error message when its empty
        List<Book> books = bookRepository.findAllByTitleContaining(title);
        return books.stream()
            .map(bookBookDTOConverter::convert)
            .collect(Collectors.toList());
    }

    private void deleteBookByID(Long id) {
        try{
            bookRepository.deleteById(id);
        } catch (IllegalArgumentException e){
            throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, ExceptionConstant.WRONG_ID);
        }
    }

    private void saveBookAndAuthor(Book newBook, List<Author> authors) {
        // TODO handle exception for saving and null error
        // If more time add this to another helper class
        if (newBook == null) {
           return;
        }

        newBook.setAuthorList(authors);
        bookRepository.save(newBook);
    }

    private List<Author> handleAuthorCreation(NewBookDTO newBookDTO) {
        List<String> authorPseudonym = newBookDTO.getAuthorsPseudonym();
        return authorService.createAuthors(authorPseudonym);
    }

}
