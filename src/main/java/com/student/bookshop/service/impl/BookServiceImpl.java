package com.student.bookshop.service.impl;

import com.student.bookshop.dto.BookDto;
import com.student.bookshop.model.Book;
import com.student.bookshop.repository.*;
import com.student.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/bookImages";


    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepo bookRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void saveBooks(BookDto bookDto, MultipartFile file, String imgName) throws IOException {

        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(authorRepository.findById(bookDto.getAuthorId()).get());
        book.setCategories(categoryRepository.findById(bookDto.getCategoryId()).get());
        book.setPublisher(publisherRepository.findById(bookDto.getPublisherId()).get());
        book.setLanguage(languageRepository.findById(bookDto.getLanguageId()).get());
        book.setPrice(bookDto.getPrice());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setNumberOfPage(bookDto.getNumberOfPage());
        book.setPaperFormat(bookDto.getPaperFormat());
        book.setAvailableQuantity(bookDto.getAvailableQuantity());

        String imageUUID;
        if (!file.isEmpty()){
            imageUUID=file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }else {
            imageUUID = imgName;
        }
        book.setImageName(imageUUID);
        bookRepository.save(book);

    }

    @Override
    public BookDto updateBooks(Long id) {
        Book book = bookRepo.findById(id).get();
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setAvailableQuantity(book.getAvailableQuantity());
        bookDto.setPaperFormat(book.getPaperFormat());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setNumberOfPage(book.getNumberOfPage());
        bookDto.setImageName(book.getImageName());
        bookDto.setAuthorId(book.getAuthor().getId());
        bookDto.setCategoryId(book.getCategories().getId());
        bookDto.setPublisherId(book.getPublisher().getId());
        bookDto.setLanguageId(book.getLanguage().getId());

        return bookDto;
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
