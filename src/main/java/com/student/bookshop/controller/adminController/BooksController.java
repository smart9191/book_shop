package com.student.bookshop.controller.adminController;

import com.student.bookshop.dto.BookDto;
import com.student.bookshop.model.Book;
import com.student.bookshop.model.Category;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.repository.RegionRepo;
import com.student.bookshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
public class BooksController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/bookImages";

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private BookCategoryService categoryService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private AuthorsService authorsService;

    @Autowired
    private BookCategoryService bookCategoryService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private RegionRepo regionRepo;


    @GetMapping("/admin/books")
    public String getBooks(Model model){
        model.addAttribute("books",bookService.findAllBooks());
        return "books";
    }
    @GetMapping("/admin/books/add")
    public String booksAdd(Model model){
        model.addAttribute("bookDto",new BookDto());
        model.addAttribute("publisher",publisherService.findAllBookPublisher());
        model.addAttribute("language",languageService.findAllLanguage());
        model.addAttribute("categories",categoryService.findAllBookCategories());
        model.addAttribute("author",authorsService.findAllBookAuthors());
        return "bookAdd";
    }


    @PostMapping("/admin/books/add")
    public String bookAdd(@ModelAttribute("bookDto") BookDto bookDto,
                          @RequestParam("bookImages") MultipartFile file,
                          @RequestParam("imgName") String imgName) throws IOException {

        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(authorsService.findByIdUpdate(bookDto.getAuthorId()).get());
        book.setCategories(bookCategoryService.findByIdUpdate(bookDto.getCategoryId()).get());
        book.setPublisher(publisherService.findByIdUpdate(bookDto.getPublisherId()).get());
        book.setLanguage(languageService.findByIdUpdateLanguage(bookDto.getLanguageId()).get());
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
        bookService.saveBooks(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/books/update/{id}")
    public String updateBooksId(@PathVariable Long id, Model model){

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

        model.addAttribute("bookDto",bookDto);
        model.addAttribute("author",authorsService.findAllBookAuthors());
        model.addAttribute("categories",categoryService.findAllBookCategories());
        model.addAttribute("publisher",publisherService.findAllBookPublisher());
        model.addAttribute("language",languageService.findAllLanguage());

        return "bookAdd";
    }
    @GetMapping("/admin/books/delete/{id}")
    public String deleteBooksId(@PathVariable Long id){
        bookService.deleteBooksId(id);
        return "redirect:/admin/books";
    }

}
