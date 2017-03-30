package com.pragmatists.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void create_account() {
        ResponseEntity<String> response = createAccount(request(param("owner", "john.doe@example.com")));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isNotNull();
    }

    @Test
    public void get_account() {
        ResponseEntity<String> response = createAccount(request(param("owner", "john.doe@example.com")));

        AccountResource accountResource = getAccount(response.getHeaders().getLocation());

        assertThat(accountResource.getAccountId()).isNotNull();
        assertThat(accountResource.getNumber()).isNotNull();
        assertThat(accountResource.getOwner()).isEqualTo("john.doe@example.com");
        assertThat(accountResource.getBalance()).isEqualTo(0);
    }

    @Test
    public void deposit_account() {
        String accountId = createAccount();

        restTemplate.put("/account/" + accountId + "/deposit", request(param("amount", "10")));

        assertThat(getAccount(accountId).getBalance()).isEqualTo("10");
    }

    @Test
    public void withdraw_account() {
        String accountId = createAccount();

        restTemplate.put("/account/" + accountId + "/withdraw", request(param("amount", "10"),param("accountId", "123")));

        assertThat(getAccount(accountId).getBalance()).isEqualTo("-10");
    }


    private HttpEntity<MultiValueMap<String, Object>> request(RequestParameter ...requestParameter) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        for (RequestParameter parameter : requestParameter) {
            map.add(parameter.name, parameter.value);
        }
        return new HttpEntity<>(map, headers);
    }


    private AccountResource getAccount(URI uri) {
        return restTemplate.getForObject(uri, AccountResource.class);
    }

    private RequestParameter param(String name, String value) {
        return new RequestParameter(name, value);
    }

    private AccountResource getAccount(String id) {
        return restTemplate.getForObject("/account/" + id, AccountResource.class);
    }


    private ResponseEntity<String> createAccount(HttpEntity<MultiValueMap<String, Object>> request) {
        return restTemplate.postForEntity("/account", request, String.class);
    }

    private String createAccount() {
        return restTemplate.postForEntity("/account", request(param("owner", "john.doe@example.com")), String.class).getHeaders().getLocation().getQuery();
    }

    private class RequestParameter {
        final String name;
        final String value;

        private RequestParameter(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}