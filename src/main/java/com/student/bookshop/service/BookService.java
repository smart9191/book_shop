package com.student.bookshop.service;

import com.student.bookshop.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public void saveBooks(Book book);

    List<Book> findAllBooks();

    public void deleteBooksId(Long id);

    Optional<Book> findByIdUpdate(Long id);

    List<Book> findAllBookCategoryId(Long id);
    List<Book> findAllBookByPublisherId(Long id);
    List<Book> findAllBookByLanguageId(Long id);
}
