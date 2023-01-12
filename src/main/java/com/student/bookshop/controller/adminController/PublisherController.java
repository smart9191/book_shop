package com.student.bookshop.controller.adminController;

import com.student.bookshop.model.Publisher;
import com.student.bookshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/admin/publisher")
    public String getPublishers(Model model){
        model.addAttribute("publisher",publisherService.findAllBookPublisher());
        return "publisher";
    }

    @GetMapping("/admin/publisher/add")

    public String getPublisherAdd(Model model){
        model.addAttribute("publisher",new Publisher());
        return "publisherAdd";
    }
    @PostMapping("/admin/publisher/add")
    public String postPublisherAdd(@ModelAttribute("publisher") Publisher publisher){
        publisherService.saveBookPublisher(publisher);
        return "redirect:/admin/publisher";
    }

    @GetMapping("/admin/publisher/delete/{id}")
    public String deletePublisherId(@PathVariable Long id){
        publisherService.deleteBookPublisher(id);
        return "redirect:/admin/publisher";
    }
    @GetMapping("/admin/publisher/update/{id}")
    public String updatePublishersId(@PathVariable Long id, Model model){

        Optional<Publisher> publisher = publisherService.findByIdUpdate(id);
        if (publisher.isPresent()){
            model.addAttribute("publisher",publisher.get());
            return "publisherAdd";
        }else {
            return "404";
        }
    }

}
