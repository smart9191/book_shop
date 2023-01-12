package com.student.bookshop.service;

import com.student.bookshop.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService {

    public void saveBookAuthors(Author author);

    List<Author> findAllBookAuthors();

    public void deleteBookAuthorsId(Long id);

    Optional<Author> findByIdUpdate(Long id);

}
