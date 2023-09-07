package com.nhnacademy.springbootopenapi.service;

import com.nhnacademy.springbootopenapi.config.AccountProperties;
import com.nhnacademy.springbootopenapi.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenApiAccountService implements AccountService{

    private final AccountProperties accountProperties;
    private final RestTemplate restTemplate;
    private static final ParameterizedTypeReference<List<Account>> LIST_PARAM =
            new ParameterizedTypeReference() {};

    @Override
    public List<Account> getAccounts() {
        ResponseEntity<List<Account>> response = restTemplate.exchange(
                accountProperties.getUrl(),
                HttpMethod.GET,
                getHttpEntity(),
                LIST_PARAM
        );

        return response.getBody();
    }

    @Override
    public Account getAccount(Long id) {
        ResponseEntity<Account> response = restTemplate.exchange(
                accountProperties.getUrl() + "/{id}",
                HttpMethod.GET,
                getHttpEntity(),
                Account.class,
                id
        );
        return response.getBody();
    }

    @Override
    public Account createAccount(Account account) {
        ResponseEntity<Account> response = restTemplate.exchange(
                accountProperties.getUrl(),
                HttpMethod.POST,
                getHttpEntityWithAccount(account),
                Account.class
        );
        return response.getBody();
    }

    @Override
    public String deleteAccount(Long id) {

        return restTemplate.exchange(
                accountProperties.getUrl() + "/" + id,
                HttpMethod.DELETE,
                getHttpEntity(),
                String.class
        ).getBody();
    }

    private HttpEntity getHttpEntityWithAccount(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Map<String, Object> param = new HashMap<>();

        param.put("id", account.getId());
        param.put("balance", account.getBalance());
        param.put("number", account.getNumber());

        return new HttpEntity<>(param, headers);
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers =  new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new HttpEntity<>(headers);
    }
}
