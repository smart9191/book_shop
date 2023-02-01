package com.student.bookshop.controller;

import com.student.bookshop.model.*;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.service.CustomerService;
import com.student.bookshop.service.OrderDetailsService;
import com.student.bookshop.service.OrderService;
import com.student.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping("/payNow")
    public String addOrder(@ModelAttribute ("order") Customer customer,
                           Principal principal,
                           HttpSession session) {
        orderService.saveOrder(customer, principal, session);
        return "redirect:/";
    }
}
