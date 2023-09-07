package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.service.AdminOnlyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-only")
public class AdminOnlyController {

    private final AdminOnlyService adminOnlyService;

    public AdminOnlyController(AdminOnlyService adminOnlyService) {
        this.adminOnlyService = adminOnlyService;
    }

    @GetMapping
    public String adminOnly(Model model) {
        model.addAttribute("data", adminOnlyService.getDataOnlyAdminConAccess());

        return "admin-only";
    }
}
