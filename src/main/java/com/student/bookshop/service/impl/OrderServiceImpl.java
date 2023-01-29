package com.student.bookshop.service.impl;

import com.student.bookshop.model.Orders;
import com.student.bookshop.repository.OrdersRepository;
import com.student.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public Orders save(Orders order) {
        return ordersRepository.save(order);
    }
}
