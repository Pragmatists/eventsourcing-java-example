package com.pragmatists.application;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Controller
@RequestMapping("/account")
public class AccountEndpoint {


    @PostMapping
    HttpEntity<?> createAccount(@RequestParam String owner) {
        String id = UUID.randomUUID().toString();
        //TODO add saving Account

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return created(location).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    HttpEntity<Resource<AccountResource>> getAccount(@PathVariable String id) {
        AccountResource accountResource = new AccountResource(id, "", "john.doe@example.com", 0);
        //TODO add fetching Account

        return ok(new Resource<>(accountResource));
    }

    @PutMapping(path = "/{id}/withdraw", produces = APPLICATION_JSON_VALUE)
    HttpEntity<?> withdraw(@PathVariable String id, @RequestParam String amount) {
        //TODO add withdraw functionality
        return noContent().build();
    }

    @PutMapping(path = "/{id}/deposit", produces = APPLICATION_JSON_VALUE)
    HttpEntity<?> deposit(@PathVariable String id, @RequestParam String amount, @RequestParam String accountId) {
        //TODO add deposit functionality
        return noContent().build();
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    HttpEntity<?> closeAccount(@PathVariable String id) {
        //TODO add closing Account

        return noContent().build();
    }


}
