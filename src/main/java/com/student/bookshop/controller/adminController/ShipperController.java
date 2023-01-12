package com.student.bookshop.controller.adminController;

import com.student.bookshop.model.Author;
import com.student.bookshop.model.Shipper;
import com.student.bookshop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/admin/shipper")
    public String getShippers(Model model){
        model.addAttribute("shipper",shipperService.findAllShippers());
        return "shipper";
    }

    @GetMapping("/admin/shipper/add")

    public String getShipperAdd(Model model){
        model.addAttribute("shipper",new Shipper());
        return "shipperAdd";
    }
    @PostMapping("/admin/shipper/add")
    public String postShipperAdd(@ModelAttribute("shipper") Shipper shipper){
       shipperService.saveShipper(shipper);
        return "redirect:/admin/shipper";
    }

    @GetMapping("/admin/shipper/delete/{id}")
    public String deleteShipperId(@PathVariable Long id){
        shipperService.deleteShipperId(id);
        return "redirect:/admin/shipper";
    }
    @GetMapping("/admin/shipper/update/{id}")
    public String updateAthorsId(@PathVariable Long id, Model model){
        Optional<Shipper> shipper = shipperService.findByIdUpdate(id);
        if (shipper.isPresent()){
            model.addAttribute("shipper",shipper.get());
            return "shipperAdd";
        }else {
            return "404";
        }
    }

}
