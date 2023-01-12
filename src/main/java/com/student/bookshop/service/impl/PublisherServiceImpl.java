package com.student.bookshop.service.impl;

import com.student.bookshop.model.Publisher;
import com.student.bookshop.repository.PublisherRepository;
import com.student.bookshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void saveBookPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public List<Publisher> findAllBookPublisher() {
        return publisherRepository.findAll();
    }

    @Override
    public void deleteBookPublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Optional<Publisher> findByIdUpdate(Long id) {
        return publisherRepository.findById(id);
    }
}
