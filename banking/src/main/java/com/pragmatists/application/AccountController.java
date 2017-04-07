package com.pragmatists.application;

import com.pragmatists.domain.Account;
import com.pragmatists.domain.BankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Controller
@RequestMapping("/account")
class AccountController {


    private final BankingService bankingService;

    AccountController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping
    ResponseEntity<?> createAccount(@RequestParam String owner) {
        String id = UUID.randomUUID().toString();
        bankingService.createAccount(id, owner);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return created(location).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResource> getAccount(@PathVariable String id) {
        try {
            final Account account = bankingService.loadAccount(id);
            return ok(new AccountResource(account.getId().getValue(), account.getNumber(), account.getOwner(), account.getBalance()));
        } catch (RuntimeException ex) {
            return notFound().build();
        }
    }

    @PutMapping(path = "/{id}/withdraw")
    void withdraw(@PathVariable String id, @RequestParam String amount) {
        bankingService.withdraw(id, amount);
    }

    @PutMapping(path = "/{id}/deposit")
    void deposit(@PathVariable String id, @RequestParam String amount) {
        bankingService.deposit(id, amount);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> closeAccount(@PathVariable String id) {
        bankingService.closeAccount(id);

        return noContent().build();
    }


}
