package com.student.bookshop.service.impl;

import com.student.bookshop.model.Author;
import com.student.bookshop.repository.AuthorRepository;
import com.student.bookshop.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void saveBookAuthors(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> findAllBookAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteBookAuthorsId(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> findByIdUpdate(Long id) {
        return authorRepository.findById(id);
    }
}
