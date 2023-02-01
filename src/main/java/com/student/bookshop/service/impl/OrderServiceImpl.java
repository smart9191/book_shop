package com.student.bookshop.service.impl;

import com.student.bookshop.model.*;
import com.student.bookshop.repository.BookRepo;
import com.student.bookshop.repository.OrderRepository;
import com.student.bookshop.service.CustomerService;
import com.student.bookshop.service.OrderDetailsService;
import com.student.bookshop.service.OrderService;
import com.student.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private OrderDetailsService orderDetailsService;

    @Override
    public void saveOrder(Customer customer, Principal principal, HttpSession session) {

        Customer costumers = new Customer();
        costumers.setFirstName(customer.getFirstName());
        costumers.setLastName(customer.getLastName());
        costumers.setAddress(customer.getAddress());
        costumers.setPhone(customer.getPhone());
        costumers.setEmail(customer.getEmail());
        costumers.setCity(customer.getCity());
        costumers.setCountry(customer.getCountry());
        costumers.setZipCode(customer.getZipCode());
        customerService.save(costumers);
        User user = new User();
        OrderDetails orderDetails = new OrderDetails();
        user.setEmail(principal.getName());
        user.setId(userService.findByEmail(principal.getName()).getId());
        LocalDateTime dateTime = LocalDateTime.now();
        Order orders = new Order();
        orders.setCustomer(costumers);
        orders.setDate(LocalDate.from(dateTime));
        orders.setUserId(user.getId());
        Map<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("CART");
        List<Book> bookList = bookRepo.findAllById(cart.keySet());
        for (Book book : bookList) {
            orderDetails.setBook(book);
            orderDetails.setOrders(orders);
            orderDetails.setAmount(cart.get(book.getId()));
            orderDetailsService.save(orderDetails);
            orderDetails = new OrderDetails();
        }
        orders.setTotalAmount(bookList.stream().mapToDouble(
                b -> b.getPrice() * cart.get(b.getId())).sum());
        orderRepository.save(orders);
        session.removeAttribute("CART");
    }
}
