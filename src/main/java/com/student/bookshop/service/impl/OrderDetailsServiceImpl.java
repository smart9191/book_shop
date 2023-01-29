package com.student.bookshop.service.impl;

import com.student.bookshop.model.OrderDetails;
import com.student.bookshop.repository.OrderDetailsRepo;
import com.student.bookshop.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;
    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepo.save(orderDetails);
    }
}
