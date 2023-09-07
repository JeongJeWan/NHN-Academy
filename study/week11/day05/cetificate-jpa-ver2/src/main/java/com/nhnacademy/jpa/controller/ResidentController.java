package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.service.resident.ResidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/resident")
    public String getResidents(Pageable pageable, Model model) {
        List<Resident> residents = residentService.gerResidents(pageable);
        int totalPage = residentService.getResidentTotalPages(pageable);
        List<Integer> pages = new ArrayList<>();
        for (int i=0; i < totalPage; i++) {
            pages.add(i);
        }

        model.addAttribute("residents", residents);
        model.addAttribute("pages", pages);

        return "residentList";
    }
}
