package com.nhnacademy.springbootaccount.service;

import com.nhnacademy.springbootaccount.entity.Account;
import com.nhnacademy.springbootaccount.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BackEndAccountService implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("계좌가 존재하지 않습니다."));
    }

    @Override
    public Account createAccount(Account account) {
        accountRepository.findById(account.getId()).ifPresent(a -> new RuntimeException("계좌가 존재하지 않습니다."));
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
