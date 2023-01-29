package com.student.bookshop.service;

import com.student.bookshop.dto.CustomerRegisDto;
import com.student.bookshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends UserDetailsService {

    User saveUser(User user);
    User findByEmail(String email);

}
