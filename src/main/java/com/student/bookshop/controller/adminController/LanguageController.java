package com.student.bookshop.controller.adminController;

import com.student.bookshop.model.Language;
import com.student.bookshop.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LanguageController{

    @Autowired
    private LanguageService languageService;
    @GetMapping("/admin/language")
    public String getLanguages(Model model){
        model.addAttribute("language",languageService.findAllLanguage());
        return "language";
    }

    @GetMapping("/admin/language/add")

    public String getLanguageAdd(Model model){
        model.addAttribute("language",new Language());
        return "languageAdd";
    }
    @PostMapping("/admin/language/add")
    public String postLanguageAdd(@ModelAttribute("language") Language language){
        languageService.saveLanguage(language);
        return "redirect:/admin/language";
    }

    @GetMapping("/admin/language/delete/{id}")
    public String deleteLanguageId(@PathVariable Long id){
       languageService.deleteLanguage(id);
        return "redirect:/admin/language";
    }
    @GetMapping("/admin/language/update/{id}")
    public String updateLanguageId(@PathVariable Long id, Model model){
        Optional<Language> language = languageService.findByIdUpdateLanguage(id);
        if (language.isPresent()){
            model.addAttribute("language",language.get());
            return "languageAdd";
        }else {
            return "404";
        }
    }


}
