package com.book.store.service.author.impl;

import com.book.store.entity.Author;
import com.book.store.repository.AuthorRepository;
import com.book.store.service.author.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> createAuthors(final List<String> authorPseudonym) {
        authorPseudonym.forEach(this::saveAuthorIfNotExists);
        return getAuthors(authorPseudonym);
    }

    private void saveAuthorIfNotExists(String pseudonym) {
        authorRepository.findByPseudonym(pseudonym).orElseGet(() ->
            authorRepository.save(Author.builder().pseudonym(pseudonym).build())
        );
    }

    private List<Author> getAuthors(final List<String> authorPseudonym) {
        return authorRepository.findAllByPseudonymIn(authorPseudonym);
    }
}
