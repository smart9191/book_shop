package com.student.bookshop.service.impl;

import com.student.bookshop.model.Book;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;


    @Override
    public void saveBooks(Book book) {
        bookRepo.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
         return bookRepo.findAll();
    }

    @Override
    public void deleteBooksId(Long id) {
          bookRepo.deleteById(id);
    }

    @Override
    public Optional<Book> findByIdUpdate(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public List<Book> findAllBookCategoryId(Long id) {
        return bookRepo.findAllByCategories_Id(id);
    }

    @Override
    public List<Book> findAllBookByPublisherId(Long id) {
        return bookRepo.findAllByPublisher_Id(id);
    }

    @Override
    public List<Book> findAllBookByLanguageId(Long id) {
        return bookRepo.findAllByLanguage_Id(id);
    }

}
