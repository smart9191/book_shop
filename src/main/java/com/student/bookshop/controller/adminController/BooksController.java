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
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private BookCategoryService categoryService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private AuthorsService authorsService;
    @Autowired
    private BookService bookService;

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
                          @RequestParam ("imgName") String imgName) throws IOException {
            bookService.saveBooks(bookDto,file,imgName);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/books/update/{id}")
    public String updateBooksId(@PathVariable Long id, Model model){

        BookDto bookDto = bookService.updateBooks(id);

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
