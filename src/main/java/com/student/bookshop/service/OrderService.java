package com.student.bookshop.service;

import com.student.bookshop.model.Customer;
import com.student.bookshop.model.Order;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface OrderService {

    public  void saveOrder(Customer customer, Principal principal, HttpSession session);

}
