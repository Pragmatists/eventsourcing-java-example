package com.pragmatists.application;

import com.pragmatists.domain.BankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;
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
        //TODO add saving Account
        String id = "xxx";

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return created(location).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResource> getAccount(@PathVariable String id) {
        AccountResource accountResource = new AccountResource(id, "", "", 0);
        //TODO add fetching Account

        return ok(accountResource);
    }

    @PutMapping(path = "/{id}/withdraw")
    ResponseEntity<Void> withdraw(@PathVariable String id, @RequestParam String amount) {
        //TODO add withdraw functionality
        return ok().build();
    }

    @PutMapping(path = "/{id}/deposit")
    ResponseEntity<Void> deposit(@PathVariable String id, @RequestParam String amount) {
        //TODO add deposit functionality
        return ok().build();
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> closeAccount(@PathVariable String id) {
        //TODO add closing Account

        return noContent().build();
    }


    @ResponseStatus(NOT_FOUND)
    class AccountClosedException extends RuntimeException {

        public AccountClosedException(String accountId) {
            super("account closed: '" + accountId + "'.");
        }
    }

    @ResponseStatus(NOT_FOUND)
    class NotEnoughMoneyException extends RuntimeException {

        public NotEnoughMoneyException(String accountId) {
            super("not enough money: '" + accountId + "'.");
        }
    }

}
