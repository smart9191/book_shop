package com.student.bookshop.controller;

import com.student.bookshop.global.GlobalData;
import com.student.bookshop.model.Book;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.service.BookCategoryService;
import com.student.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    private BookCategoryService categoryService;

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepo bookRepo;


    @GetMapping("/login")
    public String login(){
        return "logins";
    }


    @GetMapping({"/","/home"})
    public String home(Model model){

        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model, HttpSession session){
        List<Book> bookList = new ArrayList<>();
        bookList = (List<Book>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("categories",categoryService.findAllBookCategories());
        model.addAttribute("books",bookService.findAllBooks());
        model.addAttribute("cartCount", bookList == null ? 0 : bookList.size());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable Long id, Model model,HttpSession session){
        List<Book> bookList = new ArrayList<>();
        bookList = (List<Book>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("categories",categoryService.findAllBookCategories());
        model.addAttribute("books",bookRepo.findAllByCategories_Id(id));
        model.addAttribute("cartCount", bookList == null ? 0 : bookList.size());
        return "shop";
    }

    @GetMapping("/shop/viewbook/{id}")
    public String viewProduct(@PathVariable Long id,  Model model,HttpSession session){
        List<Book> bookList = new ArrayList<>();
        bookList = (List<Book>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("books", bookService.findByIdUpdate(id).get());
        model.addAttribute("cartCount", bookList == null ? 0 : bookList.size());
        return "viewBook";
    }





}
