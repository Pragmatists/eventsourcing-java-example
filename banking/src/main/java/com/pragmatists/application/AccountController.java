package com.pragmatists.application;

import com.pragmatists.domain.Account;
import com.pragmatists.domain.AccountId;
import com.pragmatists.domain.BankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
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
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(accountId.getValue()).toUri();
        return created(location).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountResource> getAccount(@PathVariable String id) {
        Account account = bankingService.getAccountBy(new AccountId(id));
        AccountResource accountResource = new AccountResource(account.accountId.toString(),
                account.number,
                account.owner,
                account.balance);

        return ok(accountResource);
    }

    @PutMapping(path = "/{ied}/withdraw")
    void withdraw(@PathVariable String id, @RequestParam String amount) {
        //TODO add withdraw functionality
    }

    @PutMapping(path = "/{id}/deposit")
    @ResponseStatus(HttpStatus.OK)
    void deposit(@PathVariable String id, @RequestParam String amount) {
        bankingService.deposit(new AccountId(id), Integer.parseInt(amount));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> closeAccount(@PathVariable String id) {
        //TODO add closing Account

        return noContent().build();
    }


}
