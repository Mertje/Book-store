package com.book.store.service.author;

import com.book.store.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> createAuthors(List<String> authorPseudonym);


}
