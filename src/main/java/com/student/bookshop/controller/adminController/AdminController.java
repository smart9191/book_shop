package com.student.bookshop.controller.adminController;

import com.student.bookshop.model.Category;
import com.student.bookshop.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private BookCategoryService categoryService;

    @GetMapping("/admin")
    public String admin(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories",categoryService.findAllBookCategories());
        return "categories";
    }

    @GetMapping("/admin/categories/add")

    public String getAuthorsAdd(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category bookCategory){
        categoryService.saveBookCategory(bookCategory);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String getCatAdd(@PathVariable Long id){
        categoryService.deleteBookCategoryId(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String getCatAdd(@PathVariable Long id, Model model){

        Optional<Category> category = categoryService.findByIdUpdate(id);
        if (category.isPresent()){
            model.addAttribute("category",category.get());
            return "categoriesAdd";
        }else {
            return "404";
        }
    }



}
