package com.pragmatists.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void create_account() {
        ResponseEntity<String> response = createAccount(request(param("owner", "john.doe@example.com")));

        assertThat(response.getStatusCode()).isEqualTo(CREATED);
        assertThat(response.getHeaders().getLocation()).isNotNull();
    }

    @Test
    public void get_account() {
        ResponseEntity<String> response = createAccount(request(param("owner", "john.doe@example.com")));

        ResponseEntity<AccountResource> accountResponse = getAccount(response.getHeaders().getLocation());

        assertThat(accountResponse.getStatusCode()).isEqualTo(OK);
        assertThat(accountResponse.getBody().getAccountId()).isNotNull();
        assertThat(accountResponse.getBody().getNumber()).isNotNull();
        assertThat(accountResponse.getBody().getOwner()).isEqualTo("john.doe@example.com");
        assertThat(accountResponse.getBody().getBalance()).isEqualTo(0);
    }

    @Test
    public void delete_account() {
        String accountId = createAccount();

        ResponseEntity<?> response = restTemplate.exchange("/account/" + accountId, HttpMethod.DELETE, request(), ResponseEntity.class);

        assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);

        ResponseEntity<AccountResource> account = getAccount(URI.create("/account/" + accountId));
        assertThat(account.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void deposit_account() {
        String accountId = createAccount();

        restTemplate.put("/account/" + accountId + "/deposit", request(param("amount", "10")));
        restTemplate.put("/account/" + accountId + "/deposit", request(param("amount", "5")));

        assertThat(getAccount(accountId).getBalance()).isEqualTo("15");
    }

    @Test
    public void withdraw_account() {
        String accountId = createAccount();

        restTemplate.put("/account/" + accountId + "/deposit", request(param("amount", "10")));
        restTemplate.put("/account/" + accountId + "/withdraw", request(param("amount", "5"), param("accountId", "123")));

        assertThat(getAccount(accountId).getBalance()).isEqualTo("5");
    }


    private HttpEntity<MultiValueMap<String, Object>> request(RequestParameter... requestParameter) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        for (RequestParameter parameter : requestParameter) {
            map.add(parameter.name, parameter.value);
        }
        return new HttpEntity<>(map, headers);
    }


    private ResponseEntity<AccountResource> getAccount(URI uri) {
        return restTemplate.getForEntity(uri, AccountResource.class);
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
        return restTemplate.postForEntity("/account", request(param("owner", "john.doe@example.com")), String.class).getHeaders().getLocation().getPath().split("/")[2];
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