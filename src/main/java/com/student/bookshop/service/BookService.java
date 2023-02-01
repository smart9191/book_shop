package com.student.bookshop.service;

import com.student.bookshop.dto.BookDto;
import com.student.bookshop.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    void saveBooks(BookDto bookDto, MultipartFile file, String imageName) throws IOException;

    BookDto updateBooks(Long id);
    List<Book> findAllBooks();

    public void deleteBooksId(Long id);

    Optional<Book> findByIdUpdate(Long id);

    List<Book> findAllBookCategoryId(Long id);
    List<Book> findAllBookByPublisherId(Long id);
    List<Book> findAllBookByLanguageId(Long id);
}
