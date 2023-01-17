package com.student.bookshop.controller;

import com.student.bookshop.global.GlobalData;
import com.student.bookshop.model.Book;
import com.student.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    private BookService bookService;

    @GetMapping("/addToCart/{id}")
    public String assToCart(@PathVariable Long id){
        GlobalData.cart.add(bookService.findByIdUpdate(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String carGet(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Book::getPrice).sum());
        model.addAttribute("cart",GlobalData.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable Long index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }


    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Book::getPrice).sum());
        return "checkout";

    }

}
