package com.student.bookshop.service;


import com.student.bookshop.model.OrderDetails;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderDetailsService {
    OrderDetails save(OrderDetails orderDetails);
}