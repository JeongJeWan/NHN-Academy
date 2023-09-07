package com.nhnacademy.security.controller;

import com.nhnacademy.security.dto.resident.ResidentDto;
import com.nhnacademy.security.entity.HouseholdCompositionResident;
import com.nhnacademy.security.entity.Resident;
import com.nhnacademy.security.service.resident.ResidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/resident")
@Controller
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
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

    @GetMapping("/{serialNumber}")
    public String getResident(@PathVariable("serialNumber") Long serialNumber, Model model) {
        ResidentDto resident = residentService.getResident(serialNumber);

        model.addAttribute("residentSerialNumber", serialNumber);
        model.addAttribute("resident", resident);

        return "residentView";
    }

    @GetMapping("/{serialNumber}/delete")
    public RedirectView deleteResident(@PathVariable("serialNumber") Long serialNumber) {
        residentService.deleteResident(serialNumber);

        return new RedirectView("/resident?page=0&size=5");
    }

    @GetMapping("/household/{residentId}")
    public String household(@PathVariable("residentId") Long residentId, Model model) {
        Long householdSerialNumber = residentService.getHouseholdSerialNumber(residentId);
        List<HouseholdCompositionResident> families = residentService.getFamilies(householdSerialNumber);
        model.addAttribute("families", families);

        return "userFamily";
    }

}
