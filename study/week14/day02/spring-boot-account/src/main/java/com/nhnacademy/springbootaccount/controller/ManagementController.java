package com.nhnacademy.springbootaccount.controller;

import com.nhnacademy.springbootaccount.actuator.MyHealthIndicator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagementController {

    private final MyHealthIndicator myHealthIndicator;

    @PostMapping("/management/fail")
    public String healthFail() {
        myHealthIndicator.downHealth();

        return "health fail";
    }

    @PostMapping("/management/recover")
    public String healthRecover() {
        myHealthIndicator.recoverHealth();

        return "health recover!!";
    }
}
