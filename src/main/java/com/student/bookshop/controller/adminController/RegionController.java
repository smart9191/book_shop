package com.student.bookshop.controller.adminController;

import com.student.bookshop.model.Region;
import com.student.bookshop.model.Shipper;
import com.student.bookshop.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/admin/region")
    public String getRegions(Model model){
        model.addAttribute("region",regionService.findAllRegion());
        return "region";
    }

    @GetMapping("/admin/region/add")

    public String getRegionAdd(Model model){
        model.addAttribute("region",new Region());
        return "regionAdd";
    }
    @PostMapping("/admin/region/add")
    public String postRegionAdd(@ModelAttribute("region") Region region){
        regionService.saveRegion(region);
        return "redirect:/admin/region";
    }

    @GetMapping("/admin/region/delete/{id}")
    public String deleteRegionId(@PathVariable Long id){
        regionService.deleteRegion(id);
        return "redirect:/admin/region";
    }
    @GetMapping("/admin/region/update/{id}")
    public String updateAthorsId(@PathVariable Long id, Model model){
        Optional<Region> region = regionService.findByIdUpdateRegion(id);
        if (region.isPresent()){
            model.addAttribute("region",region.get());
            return "regionAdd";
        }else {
            return "404";
        }
    }

}
