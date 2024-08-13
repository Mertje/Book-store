package com.book.store.repository;

import com.book.store.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByPseudonym(String pseudonym);

    List<Author> findAllByPseudonymIn(List<String> pseudonym);
}
