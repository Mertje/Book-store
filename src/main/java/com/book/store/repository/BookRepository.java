package com.book.store.repository;

import com.book.store.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByTitleContaining(String title);
}
