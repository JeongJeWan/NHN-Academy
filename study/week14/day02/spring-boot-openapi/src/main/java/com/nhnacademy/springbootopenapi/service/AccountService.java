package com.nhnacademy.springbootopenapi.service;

import com.nhnacademy.springbootopenapi.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(Account account);

    String deleteAccount(Long id);
}
