package com.student.bookshop.controller;

import com.student.bookshop.global.GlobalData;
import com.student.bookshop.model.Book;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.repository.RegionRepo;
import com.student.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private BookService bookService;
    @Autowired
    private RegionRepo regionRepo;
    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/addToCart/{id}")
    public String assToCart(@PathVariable Long id, HttpServletRequest request){

        Book book = bookService.findByIdUpdate(id).get();
        int count = Math.toIntExact(book.getAvailableQuantity());
        if(count >= 1){

            Map<Long, Integer> cart;
            cart = (HashMap<Long, Integer>)request.getSession().getAttribute("CART");
            if (cart == null) {
                cart = new HashMap<>();
            }

            if (cart.containsKey(id)) {
                if (cart.get(id) < count) {
                    cart.put(id, cart.get(id) + 1);
                    count = count - 1;
                }else {
                    return new String("redirect:/shop");
                }
            } else {
                cart.put(id, 1);
            }

            request.getSession().setAttribute("CART", cart);

        }else {
            return new String("redirect:/shop");
        }

        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String carGet(Model model, HttpSession session){

        Map<Long, Integer> cart = (HashMap<Long, Integer>)session.getAttribute("CART");
        if (cart == null) {
            return "redirect:/shop";
        }
        List<Book> bookList = bookRepo.findAllById(cart.keySet());

        model.addAttribute("books", bookList);
        model.addAttribute("carts", cart);
        model.addAttribute("cartCount",bookList == null ? 0 : bookList.size());
        model.addAttribute("total",bookList == null ? 0 : bookList.stream().mapToDouble(
                b -> b.getPrice() * cart.get(b.getId())
        ).sum());
        model.addAttribute("cart",bookList);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{id}")
    public String cartItemRemove(@PathVariable Long id,HttpSession session){
        Map<Long, Integer> cart = (HashMap<Long, Integer>)session.getAttribute("CART");
        cart.remove(id);
        session.setAttribute("CART",cart);
        return "redirect:/cart";
    }


    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session){

        Map<Long, Integer> cart = (HashMap<Long, Integer>)session.getAttribute("CART");
        if (cart == null) {
            return "redirect:/shop";
        }
        List<Book> bookList = bookRepo.findAllById(cart.keySet());
        model.addAttribute("total",bookList == null ? 0 : bookList.stream().mapToDouble(
                b -> b.getPrice() * cart.get(b.getId())).sum());
        return "checkout";
    }

}
