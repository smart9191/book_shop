package com.student.bookshop.controller.adminController;

import com.student.bookshop.model.Author;
import com.student.bookshop.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private AuthorsService authorsService;

    @GetMapping("/admin/author")
    public String getAuthors(Model model){
        model.addAttribute("author",authorsService.findAllBookAuthors());
        return "author";
    }

    @GetMapping("/admin/author/add")

    public String getCatAdd(Model model){
        model.addAttribute("author",new Author());
        return "authorAdd";
    }
    @PostMapping("/admin/author/add")
    public String postAuthorAdd(@ModelAttribute("author") Author author){
        authorsService.saveBookAuthors(author);
        return "redirect:/admin/author";
    }

    @GetMapping("/admin/author/delete/{id}")
    public String deleteAuthorId(@PathVariable Long id){
        authorsService.deleteBookAuthorsId(id);
        return "redirect:/admin/author";
    }
    @GetMapping("/admin/author/update/{id}")
    public String updateAthorsId(@PathVariable Long id, Model model){

        Optional<Author> author = authorsService.findByIdUpdate(id);
        if (author.isPresent()){
            model.addAttribute("author",author.get());
            return "authorAdd";
        }else {
            return "404";
        }
    }

}
