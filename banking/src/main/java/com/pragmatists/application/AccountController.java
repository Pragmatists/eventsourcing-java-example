package com.pragmatists.application;

import com.pragmatists.domain.Account;
import com.pragmatists.domain.AccountId;
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
        AccountId accountId = bankingService.createAccount(owner);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(accountId.toString()).toUri();
        return created(location).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResource> getAccount(@PathVariable String id) {
        Account account = bankingService.loadAccount(AccountId.from(id));
        AccountResource accountResource = new AccountResource(account.id(), account.number(), account.owner(), account.balance());
        return ok(accountResource);
    }

    @PutMapping(path = "/{id}/withdraw")
    ResponseEntity<Void> withdraw(@PathVariable String id, @RequestParam String amount) {
        bankingService.withdraw(AccountId.from(id), Integer.parseInt(amount));
        return ok().build();
    }

    @PutMapping(path = "/{id}/deposit")
    ResponseEntity<Void> deposit(@PathVariable String id, @RequestParam String amount) {
        bankingService.deposit(AccountId.from(id), amount);
        return ok().build();
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> closeAccount(@PathVariable String id) {
        bankingService.closeAccount(AccountId.from(id));
        return noContent().build();
    }


    @ResponseStatus(NOT_FOUND)
    class NotEnoughMoneyException extends RuntimeException {

        public NotEnoughMoneyException(String accountId) {
            super("not enough money: '" + accountId + "'.");
        }
    }

}
