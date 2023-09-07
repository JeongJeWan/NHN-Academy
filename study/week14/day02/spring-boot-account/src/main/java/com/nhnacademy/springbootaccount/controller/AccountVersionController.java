package com.nhnacademy.springbootaccount.controller;

import com.nhnacademy.springbootaccount.configuration.AccountProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountVersionController {

    private final AccountProperties accountProperties;

    public AccountVersionController(AccountProperties accountProperties) {
        this.accountProperties = accountProperties;
    }

    @GetMapping("/system/version")
    public String getAccountVersion() {
        return "{\"version\":\"" + accountProperties.getVersion() +"\"}";
    }
}
