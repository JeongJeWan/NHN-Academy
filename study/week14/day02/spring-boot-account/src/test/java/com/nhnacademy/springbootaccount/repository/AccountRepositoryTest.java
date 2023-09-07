package com.nhnacademy.springbootaccount.repository;

import com.nhnacademy.springbootaccount.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findAll() {

    }

    @Test
    void findById() {

        Account account = new Account(1L, "1", 100);
        accountRepository.save(account);

        Optional<Account> actual = accountRepository.findById(1L);

        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(account);
    }
}