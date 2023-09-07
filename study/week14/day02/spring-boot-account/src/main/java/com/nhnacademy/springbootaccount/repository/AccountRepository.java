package com.nhnacademy.springbootaccount.repository;

import com.nhnacademy.springbootaccount.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

}
