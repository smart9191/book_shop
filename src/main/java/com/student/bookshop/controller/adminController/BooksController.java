package com.student.bookshop.controller.adminController;

import com.student.bookshop.dto.BookDto;
import com.student.bookshop.model.Book;
import com.student.bookshop.model.Category;
import com.student.bookshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        bookService.saveBookAuthors(book);
        return "redirect:/admin/books";
    }


}
