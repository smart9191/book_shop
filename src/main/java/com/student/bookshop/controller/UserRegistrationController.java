package com.student.bookshop.controller;

import com.student.bookshop.dto.CustomerRegisDto;
import com.student.bookshop.model.Region;
import com.student.bookshop.model.User;
import com.student.bookshop.service.RegionService;
import com.student.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserRegistrationController {

    @Autowired
    private RegionService regionService;

    private final UserService userService;
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registr";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("registr")
    public String registerUserAccount(@ModelAttribute("user") User user)  {
        userService.saveUser(user);
        return "redirect:/registr?success";
    }

    @GetMapping("registr")
    public String findByAllRegion(Model model){
        List<Region> region = regionService.findAllRegion();
        model.addAttribute("reg",region);

        return "registr";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
