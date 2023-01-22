package com.student.bookshop.controller;

import com.student.bookshop.global.GlobalData;
import com.student.bookshop.model.Book;
import com.student.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private BookService bookService;

    @GetMapping("/addToCart/{id}")
    public String assToCart(@PathVariable Long id, HttpServletRequest request){
        List<Book> bookList = new ArrayList<>();
        bookList= (List<Book>) request.getSession().getAttribute("NOTES_SESSION");
        if(bookList == null){
            bookList = new ArrayList<>();
            request.getSession().setAttribute("NOTES_SESSION", bookList);
        }
        bookList.add(bookService.findByIdUpdate(id).get());
        request.getSession().setAttribute("NOTES_SESSION", bookList);
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String carGet(Model model, HttpSession session){
        List<Book> bookList = new ArrayList<>();
        bookList = (List<Book>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("notesSession", bookList!=null? bookList:new ArrayList<>());
        model.addAttribute("cartCount",bookList.size());
        model.addAttribute("total",bookList.stream().mapToDouble(Book::getPrice).sum());
        model.addAttribute("cart",bookList);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable Integer index,HttpSession session){
        List<Book> bookList = (List<Book>) session.getAttribute("NOTES_SESSION");
        bookList.remove(index);
        return "redirect:/cart";
    }


    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Book::getPrice).sum());
        return "checkout";

    }

}
