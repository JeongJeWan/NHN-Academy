package com.nhnacademy.springbootaccount.service;

import com.nhnacademy.springbootaccount.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();
    Account getAccount(Long id);
    Account createAccount(Account account);
    void deleteAccount(Long id);
}
