package com.student.bookshop.service;

import com.student.bookshop.model.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Book> AddToCart(Long id, HttpServletRequest request);

}
