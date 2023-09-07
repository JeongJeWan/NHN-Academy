package com.nhnacademy.springbootopenapi.controller;

import com.nhnacademy.springbootopenapi.entity.Account;
import com.nhnacademy.springbootopenapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    // curl -XGET http://localhost:8080/accounts
    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    // curl -XGET http://localhost:8080/accounts/1
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    // curl –X POST -H"Content-Type: application/json" -d'{"id":4, "number":"9090","balance":100}' http://localhost:8080/accounts
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // curl -XDELETE http://localhost:8080/accounts/4
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        return accountService.deleteAccount(id);
    }
}
