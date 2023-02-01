package com.student.bookshop.service.impl;

import com.student.bookshop.model.Book;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public void AddToCart(Long id, HttpServletRequest request) {

        Book book = bookRepo.findById(id).get();
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
                    return;
                }
            } else {
                cart.put(id, 1);
            }

            request.getSession().setAttribute("CART", cart);

    }else {
            return;
        }
    }

}
