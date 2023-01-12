package com.student.bookshop.service;

import com.student.bookshop.model.Author;
import com.student.bookshop.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    public void saveBookPublisher(Publisher publisher);

    List<Publisher> findAllBookPublisher();

    public void deleteBookPublisher(Long id);

    Optional<Publisher> findByIdUpdate(Long id);

}
